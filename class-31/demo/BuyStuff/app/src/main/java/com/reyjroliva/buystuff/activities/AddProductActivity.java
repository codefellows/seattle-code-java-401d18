package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import io.reactivex.rxjava3.core.Completable;

public class AddProductActivity extends AppCompatActivity {
  private final String TAG = "AddProductActivity";

  CompletableFuture<List<Contact>> contactsFuture = null;

  Button saveButton;
  EditText productDescriptionEditText;
  EditText productNameEditText;
  Spinner productCategorySpinner;
  Spinner productContactSpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    contactsFuture = new CompletableFuture<>();

    productCategorySpinner = findViewById(R.id.AddProductActivityCategorySpinner);
    productContactSpinner = findViewById(R.id.AddProductActivityContactSpinner);
    productDescriptionEditText = findViewById(R.id.AddProductActivityDescriptionEditText);
    productNameEditText = findViewById(R.id.AddProductActivityProductNameEditText);
    saveButton = findViewById(R.id.AddProductActivitySaveButton);

    setupProductCategorySpinner();
    setupProductContactSpinner();
    setupSaveButton();
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
      //for(Contact contact : contacts) {
      //  if(contact.getFullName().equals(selectedContactString))
      //    selectedContact = contact;
      //}

      Product productToSave = Product.builder()
          .name(productNameEditText.getText().toString())
          .description(productDescriptionEditText.getText().toString())
          .dateCreated(new Temporal.DateTime(new Date(), 0))
          .productCategory((ProductCategoryEnum) productCategorySpinner.getSelectedItem())
          .contactPerson(selectedContact)
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
}
