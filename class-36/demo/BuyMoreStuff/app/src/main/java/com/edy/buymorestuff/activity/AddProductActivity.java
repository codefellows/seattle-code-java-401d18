package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AddProductActivity extends AppCompatActivity
{
  public static final String TAG = "AddProductActivity";
  Spinner productCategorySpinner = null;
  Spinner contactSpinner = null;
  // WARNING: Be careful of using CompletableFuture in runOnUiThread()! Sometimes it seems to break
  // Also, I recommend using a future only in a single Activity, not between activities
  CompletableFuture<List<Contact>> contactsFuture = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    contactsFuture = new CompletableFuture<>();

    setUpSpinners();
    setUpSaveButton();
  }

  private void setUpSpinners()
  {
    contactSpinner = (Spinner) findViewById(R.id.addProductContactSpinner);

    Amplify.API.query(
      ModelQuery.list(Contact.class),
      success ->
      {
        Log.i(TAG, "Read contacts successfully!");
        ArrayList<String> contactNames = new ArrayList<>();
        ArrayList<Contact> contacts = new ArrayList<>();
        for (Contact contact : success.getData())
        {
          contacts.add(contact);
          contactNames.add(contact.getFullName());
        }
        contactsFuture.complete(contacts);

        runOnUiThread(() ->
        {
          contactSpinner.setAdapter(new ArrayAdapter<>(
            this,
            android.R.layout.simple_spinner_item,
            contactNames));
        });
      },
      failure -> {
        contactsFuture.complete(null);
        Log.i(TAG, "Did not read contacts successfully!");
      }
    );

    productCategorySpinner = (Spinner) findViewById(R.id.addProductProductCategorySpinner);
    productCategorySpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()));
  }

  private void setUpSaveButton()
  {
    Button saveButton = (Button) findViewById(R.id.saveProductButton);
    saveButton.setOnClickListener(v ->
    {
      String name = ((EditText)findViewById(R.id.addProductProductNameEditText)).getText().toString();
      String description = ((EditText)findViewById(R.id.addProductProductDescriptionEditText)).getText().toString();
      //String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
      String selectedContactString = contactSpinner.getSelectedItem().toString();

      List<Contact> contacts = null;
      try
      {
        contacts = contactsFuture.get();
      }
      catch (InterruptedException ie)
      {
        Log.e(TAG, "InterruptedException while getting contacts");
        Thread.currentThread().interrupt();
      }
      catch (ExecutionException ee)
      {
        Log.e(TAG, "ExecutionException while getting contacts");
      }
      Contact selectedContact = contacts.stream().filter(c -> c.getFullName().equals(selectedContactString)).findAny().orElseThrow(RuntimeException::new);

      Product newProduct = Product.builder()
        .name(name)
        .description(description)
        //.dateCreated(new Temporal.DateTime(currentDateString))
        .dateCreated(new Temporal.DateTime(new Date(), 0))
        .productCategory((ProductCategoryEnum) productCategorySpinner.getSelectedItem())
        .contactPerson(selectedContact)
        .build();

      Amplify.API.mutate(
        ModelMutation.create(newProduct),  // making a GraphQL request to the cloud
        successResponse -> Log.i(TAG, "AddProductActivity.onCreate(): made a product successfully"),  // success callback
        failureResponse -> Log.i(TAG, "AddProductActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
      );
      //Toast.makeText(getApplicationContext(), "Product saved!", Toast.LENGTH_SHORT).show();
      Snackbar.make(findViewById(R.id.addProductActivity), "Product saved!", Snackbar.LENGTH_SHORT).show();
    });
  }
}
