package com.reyjroliva.buystuff.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.core.Completable;

public class AddProductActivity extends AppCompatActivity {
  private final String TAG = "AddProductActivity";
  private String s3ImageKey = ""; // holds the image S3 key if one currently exists in this activity, or the empty String if there is no image picked in this activity currently

  ActivityResultLauncher<Intent> activityResultLauncher;
  CompletableFuture<List<Contact>> contactsFuture = null;

  Button saveButton;
  EditText productDescriptionEditText;
  EditText productNameEditText;
  ImageView productImageView;
  Spinner productCategorySpinner;
  Spinner productContactSpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    activityResultLauncher = getImagePickingActivityResultLauncher();
    contactsFuture = new CompletableFuture<>();

    productCategorySpinner = findViewById(R.id.AddProductActivityCategorySpinner);
    productContactSpinner = findViewById(R.id.AddProductActivityContactSpinner);
    productDescriptionEditText = findViewById(R.id.AddProductActivityDescriptionEditText);
    productImageView = findViewById(R.id.AddProductActivityProductImageView);
    productNameEditText = findViewById(R.id.AddProductActivityProductNameEditText);
    saveButton = findViewById(R.id.AddProductActivitySaveButton);

    setupProductImageView();
    setupProductCategorySpinner();
    setupProductContactSpinner();
    setupSaveButton();
  }

  void setupProductImageView() {
    productImageView.setOnClickListener(v -> {
       launchImageSelectionIntent();
    });
  }

  void setupProductCategorySpinner() {
    productCategorySpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()
    ));
  }

  void setupSaveButton() {
    saveButton.setOnClickListener(v -> {
      String selectedContactString = productContactSpinner.getSelectedItem().toString();

      List<Contact> contacts = null;
      try {
        contacts = contactsFuture.get();
      } catch (InterruptedException ie) {
        Log.e(TAG, "InterruptedException while getting contacts");
        Thread.currentThread().interrupt();
      } catch (ExecutionException ee) {
        Log.e(TAG, "ExecutionException while getting contacts");
      }
      assert contacts != null;
      Contact selectedContact = contacts.stream().filter(c -> c.getFullName().equals(selectedContactString)).findAny().orElseThrow(RuntimeException::new);

      // Part 4: update/save out product object with the image key
      Product productToSave = Product.builder()
          .name(productNameEditText.getText().toString())
          .description(productDescriptionEditText.getText().toString())
          .dateCreated(new Temporal.DateTime(new Date(), 0))
          .productCategory((ProductCategoryEnum) productCategorySpinner.getSelectedItem())
          .contactPerson(selectedContact)
          .productImageS3Key(s3ImageKey)
          .build();

      Amplify.API.mutate(
        ModelMutation.create(productToSave), // making a GraphQL request to cloud
        successResponse -> Log.i(TAG, "AddProductActivity.setupSaveButton(): made product successfully"), // success callback
        failureResponse -> Log.i(TAG, "AddProductActivity.setupSaveButton(): failed with this response " + failureResponse) // failure callback
      );

      Snackbar.make(findViewById(R.id.AddProductActivityView), "Product saved!", Snackbar.LENGTH_SHORT).show();
    });
  }

  void setupProductContactSpinner() {
    Amplify.API.query(
      ModelQuery.list(Contact.class),
      success -> {
        Log.i(TAG, "Read contacts successfully");
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        for(Contact contact : success.getData()) {
          contacts.add(contact);
          contactNames.add(contact.getFullName());
        }
        contactsFuture.complete(contacts);

        runOnUiThread(() -> {
          productContactSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            contactNames
          ));
        });
      },
      failure -> {
        contactsFuture.complete(null);
        Log.i(TAG, "Did not read contacts successfully!!");
      }
    );
  }

  void launchImageSelectionIntent() {
    // Part 1: Launch Android's activity to pick an image file
    Intent imageFilePickingIntent = new Intent(Intent.ACTION_GET_CONTENT); // one of several file picking activities built into Android
    imageFilePickingIntent.setType("*/*"); // only allow one kind or category of file; if you don't have this, you get a very cryptic error about "No activity found to handle Intent"
    imageFilePickingIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/jpeg", "image/png"}); // only pick JPEG and PNG images

    // Launch Android's built-in file picking activity using our newly created ActivityResultLauncher below
    activityResultLauncher.launch(imageFilePickingIntent);
  }

  ActivityResultLauncher<Intent> getImagePickingActivityResultLauncher() {
    ActivityResultLauncher<Intent> imagePickingActivityResultLauncher =
      registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        new ActivityResultCallback<ActivityResult>() {
          // Part 2: Android calls your callback with the picked image
          @Override
          public void onActivityResult(ActivityResult result) {
            Uri pickedImageFileUri = result.getData().getData();
            try {
              InputStream pickedImageInputStream = getContentResolver().openInputStream(pickedImageFileUri);
              String pickedImageFilename = getFileNameFromUri(pickedImageFileUri); // nicer way of extracing filename from a Uri
              Log.i(TAG, "Succeeded in getting input stream from file on phone! Filename is: " + pickedImageFilename);
              uploadInputStreamToS3(pickedImageInputStream, pickedImageFilename, pickedImageFileUri);
            } catch (FileNotFoundException fnfe) {
              Log.e(TAG, "Could not get file form file picker! " + fnfe.getMessage(), fnfe);
            }
          }
        }
      );

    return imagePickingActivityResultLauncher;
  }

  void uploadInputStreamToS3(InputStream pickedImageInputStream, String pickedImageFilename, Uri pickedImageFileUri) {
    // Part 3: Use our InputStream to upload file to S3

    Amplify.Storage.uploadInputStream(
      pickedImageFilename, // S3 key
      pickedImageInputStream,
      success -> {
        // confirm that we succeeded our upload and grab the key
        Log.i(TAG, "Succeeded in getting file uploaded to S3! Key is: " + success.getKey());
        s3ImageKey = success.getKey(); // non-empty s3ImageKey indicates an image was picked in this activity
        // TODO: update our saveProduct to include the s3 key

        // grabbing a new input stream since we used the original for uploading to S3
        InputStream pickedImageInputStreamCopy = null; // need to make a copy because InputStreams cannot be reused!
        try {
          pickedImageInputStreamCopy = getContentResolver().openInputStream(pickedImageFileUri);
        } catch (FileNotFoundException fnfe) {
          Log.e(TAG, "Could not get file stream from URI! " + fnfe.getMessage(), fnfe);
        }

        // set te imageView with the selected image
        productImageView.setImageBitmap(BitmapFactory.decodeStream(pickedImageInputStreamCopy));
      },
      failure -> {
        Log.e(TAG, "Failure in uploading file to S3 with filename: " + pickedImageFilename + " with error: " + failure.getMessage());
      }
    );
  }


  // Taken from https://stackoverflow.com/a/25005243/16889809
  @SuppressLint("Range")
  public String getFileNameFromUri(Uri uri) {
    String result = null;
    if (uri.getScheme().equals("content")) {
      Cursor cursor = getContentResolver().query(uri, null, null, null, null);
      try {
        if (cursor != null && cursor.moveToFirst()) {
          result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
        }
      } finally {
        cursor.close();
      }
    }
    if (result == null) {
      result = uri.getPath();
      int cut = result.lastIndexOf('/');
      if (cut != -1) {
        result = result.substring(cut + 1);
      }
    }
    return result;
  }


}
