package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;

public class OrderFormActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_form);

    setupProductNameTextView();
  }

  void setupProductNameTextView() {
    Intent callingIntent = getIntent();
    String productNameString = null;
    if(callingIntent != null) {
      productNameString = callingIntent.getStringExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG);
    }

    TextView productNameTextView = (TextView) findViewById(R.id.OrderFormProductNameTextView);
    if(productNameString != null && !productNameString.equals("")) {
      productNameTextView.setText(productNameString);
    } else {
      productNameTextView.setText(R.string.no_product_name);
    }
  }
}
