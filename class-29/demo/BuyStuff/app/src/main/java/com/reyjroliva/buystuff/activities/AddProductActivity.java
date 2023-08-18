package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;
import com.reyjroliva.buystuff.database.BuyStuffDatabase;
import com.reyjroliva.buystuff.models.Product;
import com.reyjroliva.buystuff.models.ProductCategoryEnum;

import java.util.Date;

public class AddProductActivity extends AppCompatActivity {
  BuyStuffDatabase buyStuffDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_product);
    buyStuffDatabase = Room.databaseBuilder(
      getApplicationContext(),
      BuyStuffDatabase.class,
      MainActivity.DATABASE_NAME)
      .allowMainThreadQueries() // For dev purposes only
      .build();

    Spinner productCategorySpinner = (Spinner) findViewById(R.id.AddProductActivityCategorySpnner);

    setupProductCategorySpinner(productCategorySpinner);
    setupSaveButton(productCategorySpinner);
  }

  void setupProductCategorySpinner(Spinner productCategorySpinner) {
    productCategorySpinner.setAdapter(new ArrayAdapter<>(
      this,
      android.R.layout.simple_spinner_item,
      ProductCategoryEnum.values()
    ));
  }

  void setupSaveButton(Spinner productCategorySpinner) {
    Button saveButton = (Button) findViewById(R.id.AddProductActivitySaveButton);
    saveButton.setOnClickListener(v -> {
      Product productToSave = new Product(
        ((EditText)findViewById(R.id.AddProductActivityProductNameEditText)).getText().toString(),
        ((EditText)findViewById(R.id.AddProductActivityDescriptionEditText)).getText().toString(),
        new Date(), // gets whatever the current date is right now
        ProductCategoryEnum.fromString(productCategorySpinner.getSelectedItem().toString())
      );

      buyStuffDatabase.productDao().insertAProduct(productToSave);
      Snackbar.make(findViewById(R.id.AddProcutActivityView), "Product saved!", Snackbar.LENGTH_SHORT).show();
    });
  }
}
