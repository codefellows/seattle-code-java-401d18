package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.edy.buymorestuff.R;
import com.edy.buymorestuff.model.Product;
import com.edy.buymorestuff.model.ProductCategoryEnum;
import com.google.android.material.snackbar.Snackbar;

import java.util.Date;

public class AddProductActivity extends AppCompatActivity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);

    Spinner productCategorySpinner = (Spinner) findViewById(R.id.addProductProductCategorySpinner);
    productCategorySpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()));
    Button saveButton = (Button) findViewById(R.id.saveProductButton);
    saveButton.setOnClickListener(v ->
    {
      Product newProduct = new Product(
        ((EditText)findViewById(R.id.addProductProductNameEditText)).getText().toString(),
        ((EditText)findViewById(R.id.addProductProductDescriptionEditText)).getText().toString(),
        new Date(), // get whatever the date is right now
        ProductCategoryEnum.fromString(productCategorySpinner.getSelectedItem().toString())
      );

      // TODO: Change this to a DynamoDb / GraphQL query
      //buyMoreStuffDatabase.productDao().insertAProduct(newProduct);
      //adapter.notifyDataSetChanged();  This is the better way, but you need the adapter!
      Snackbar.make(findViewById(R.id.userSettingsActivity), "Product saved!", Snackbar.LENGTH_SHORT).show();
    });
  }
}
