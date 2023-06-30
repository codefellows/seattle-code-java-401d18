package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class EditProductActivity extends AppCompatActivity
{
  // TODO: Add editing and removing of products here
  // Syntax is:
  // Editing: Amplify.API.mutate(ModelMutation.update())
  // Deleting: Amplify.API.mutate(ModelMutation.delete())

  public static final String TAG = "editProductActivity";
  private Product productToEdit = null;
  private CompletableFuture<Product> productCompletableFuture = null;
  private Spinner productCategorySpinner = null;
  private Spinner contactSpinner = null;
  private CompletableFuture<List<Contact>> contactsFuture = null;
  private EditText nameEditText;
  private EditText descriptionEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_product);
    productCompletableFuture = new CompletableFuture<>();
    contactsFuture = new CompletableFuture<>();

    setUpEditableUiElements();
    setUpSaveButton();
    setUpDeleteButton();
  }

  private void setUpEditableUiElements()
  {
    Intent callingIntent = getIntent();
    String productId = null;
    if (callingIntent != null)
    {
      productId = callingIntent.getStringExtra(ProductListActivity.PRODUCT_ID_TAG);
    }

    String productId2 = productId;  // ugly hack to fix lambda processing
    Amplify.API.query(
      ModelQuery.list(Product.class),
      success ->
      {
        Log.i(TAG, "Read products successfully!");

        for (Product databaseProduct : success.getData())
        {
          if (databaseProduct.getId().equals(productId2))
          {
            productCompletableFuture.complete(databaseProduct);
          }
        }

        runOnUiThread(() ->
        {
          // Update UI elements
        });
      },
      failure -> Log.i(TAG, "Did not read products successfully!")
    );

    try
    {
      productToEdit = productCompletableFuture.get();
    }
    catch (InterruptedException ie)
    {
      Log.e(TAG, "InterruptedException while getting product");
      Thread.currentThread().interrupt();
    }
    catch (ExecutionException ee)
    {
      Log.e(TAG, "ExecutionException while getting product");
    }

    nameEditText = ((EditText)findViewById(R.id.editProductProductNameEditText));
    nameEditText.setText(productToEdit.getName());
    descriptionEditText = ((EditText)findViewById(R.id.editProductDescriptionEditText));
    descriptionEditText.setText(productToEdit.getDescription());
    setUpSpinners();
  }

  private void setUpSpinners()
  {
    contactSpinner = (Spinner) findViewById(R.id.editProductContactSpinner);

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
          contactSpinner.setSelection(getSpinnerIndex(contactSpinner, productToEdit.getContactPerson().getFullName()));
        });
      },
      failure -> {
        contactsFuture.complete(null);
        Log.i(TAG, "Did not read contacts successfully!");
      }
    );

    productCategorySpinner = (Spinner) findViewById(R.id.editProductCategorySpinner);
    productCategorySpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()));
    productCategorySpinner.setSelection(getSpinnerIndex(productCategorySpinner, productToEdit.getProductCategory().toString()));
  }

  private void setUpSaveButton()
  {
    Button saveButton = (Button)findViewById(R.id.editProductSaveButton);
    saveButton.setOnClickListener(v ->
    {
      List<Contact> contacts = null;
      String contactToSaveString = contactSpinner.getSelectedItem().toString();
      try
      {
        contacts = contactsFuture.get();
      }
      catch (InterruptedException ie)
      {
        Log.e(TAG, "InterruptedException while getting product");
        Thread.currentThread().interrupt();
      }
      catch (ExecutionException ee)
      {
        Log.e(TAG, "ExecutionException while getting product");
      }
      Contact contactToSave = contacts.stream().filter(c -> c.getFullName().equals(contactToSaveString)).findAny().orElseThrow(RuntimeException::new);
      Product productToSave = Product.builder()
        .name(nameEditText.getText().toString())
        .id(productToEdit.getId())
        .dateCreated(productToEdit.getDateCreated())
        .description(descriptionEditText.getText().toString())
        .contactPerson(contactToSave)
        .productCategory(productCategoryFromString(productCategorySpinner.getSelectedItem().toString()))
        .build();

      Amplify.API.mutate(
        ModelMutation.update(productToSave),  // making a GraphQL request to the cloud
        successResponse ->
        {
          Log.i(TAG, "EditProductActivity.onCreate(): edited a product successfully");
          // TODO: Display a Snackbar
          Snackbar.make(findViewById(R.id.editProductActivity), "Product saved!", Snackbar.LENGTH_SHORT).show();
        },  // success callback
        failureResponse -> Log.i(TAG, "EditProductActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
      );
    });
  }

  private void setUpDeleteButton()
  {
    Button deleteButton = (Button)findViewById(R.id.editProductDeleteButton);
    deleteButton.setOnClickListener(v ->
    {
      Amplify.API.mutate(
        ModelMutation.delete(productToEdit),
        successResponse ->
        {
          Log.i(TAG, "EditProductActivity.onCreate(): deleted a product successfully");
          // TODO: Go back to the ProductListActivity
          Intent goToProductListActivity = new Intent(EditProductActivity.this, ProductListActivity.class);
          startActivity(goToProductListActivity);
        },  // success callback
        failureResponse -> Log.i(TAG, "EditProductActivity.onCreate(): failed with this response: " + failureResponse)
      );
    });
  }

  private int getSpinnerIndex(Spinner spinner, String stringValueToCheck){
    for (int i = 0;i < spinner.getCount(); i++){
      if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(stringValueToCheck)){
        return i;
      }
    }

    return 0;
  }

  public static ProductCategoryEnum productCategoryFromString(String inputProductCategoryText) {
    for (ProductCategoryEnum productCategory : ProductCategoryEnum.values()) {
      if (productCategory.toString().equals(inputProductCategoryText)) {
        return productCategory;
      }
    }
    return null;
  }
}
