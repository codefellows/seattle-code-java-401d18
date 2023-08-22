package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;

import java.util.Date;

public class AddProductActivity extends AppCompatActivity {
  private final String TAG = "AddProductActivity";

  Button saveButton;
  EditText productDescriptionEditText;
  EditText productNameEditText;
  Spinner productCategorySpinner;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    productCategorySpinner = findViewById(R.id.AddProductActivityCategorySpinner);
    productDescriptionEditText = findViewById(R.id.AddProductActivityDescriptionEditText);
    productNameEditText = findViewById(R.id.AddProductActivityProductNameEditText);
    saveButton = findViewById(R.id.AddProductActivitySaveButton);

    setupProductCategorySpinner();
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
      Product productToSave = Product.builder()
          .name(productNameEditText.getText().toString())
          .description(productDescriptionEditText.getText().toString())
          .dateCreated(new Temporal.DateTime(new Date(), 0))
          .productCategory((ProductCategoryEnum) productCategorySpinner.getSelectedItem())
          .build();

      Amplify.API.mutate(
        ModelMutation.create(productToSave), // making a GraphQL request to cloud
        successResponse -> Log.i(TAG, "AddProductActivity.setupSaveButton(): made product successfully"), // success callback
        failureResponse -> Log.i(TAG, "AddProductActivity.setupSaveButton(): failed with this response " + failureResponse) // failure callback
      );

      Snackbar.make(findViewById(R.id.AddProductActivityView), "Product saved!", Snackbar.LENGTH_SHORT).show();
    });
  }
}
