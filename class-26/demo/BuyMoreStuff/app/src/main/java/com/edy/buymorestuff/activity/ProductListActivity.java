package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.edy.buymorestuff.R;

public class ProductListActivity extends AppCompatActivity
{
    public final String TAG = "ProductListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_product_list);

      // Step 1: Get UI component
      Button orderButton = (Button) findViewById(R.id.productListOrderButton);

      // Step 2: Set OnClickListener
      orderButton.setOnClickListener(new View.OnClickListener()
      {
        // Step 3: Define the onClick() callback
        @Override
        public void onClick(View view)
        {
          // Step 4: Log some text
          System.out.println("Hello, we submitted!");
          Log.e(TAG, "Hello, we're logging this time!");

          //((TextView)findViewById(R.id.submittedTextTextView)).setText(R.string.submitted);

          // Intent has two arguments: the context where you're coming from (aka the source Activity), and the place where you're going (the destination Activity)
          Intent goToOrderFormIntent = new Intent(ProductListActivity.this, OrderFormActivity.class);
          startActivity(goToOrderFormIntent);

          // Alternate form if your code is not in ProductListActivity
          //ProductListActivity.this.startActivity(goToOrderFormIntent);
        }
      });
    }
}
