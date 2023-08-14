package com.reyjroliva.buystuff;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.reyjroliva.buystuff.activities.OrderFormActivity;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    setupOrderFormButton();
  }

  void setupOrderFormButton() {
    // 4 Steps for setting on click listeners in JavaScript
    // 1: Get a UI element by ID
    // 2: Add event listener to that element
    // 3: Attach callback function with an onClick() method
    // 4: Do stuff in the callback method

    // Adding a onClickListener in Android
    // Step 1: grab the UI element
    Button orderFormButton = findViewById(R.id.MainActivityMoveToOrderFormButton);
    // Step 2/3: add an onClickListener
    orderFormButton.setOnClickListener(v -> {
      // Step 4: So stuff in the callback method
      System.out.println("order form button was pressed!");

      // A better way to log
      Log.v(TAG, "I' a VERBOSE log!");
      Log.d(TAG, "I'm a DEBUG log");
      Log.i(TAG, "I'm an INFO log");
      Log.w(TAG, "I'm a WARNING log");
      Log.e(TAG, "I'm an ERROR log");
      Log.wtf(TAG, "WHAT A TERRIBLE FAILURE");

      // changes "Product Name" text view to have no text when button is clicked
      //((TextView)findViewById(R.id.MainActivityProductNameTextView)).setText("");

      // Let's create and trigger an Intent
      // 1 - Grab the button that will trigger the intent (done, we're inside of it now!)
      // 2 - Set an onClickListener() (done, were inside of it!)
      // 3 - Create an intent... Tell intent where you came from and where you're headed
      Intent goToOrderFormIntent = new Intent(MainActivity.this, OrderFormActivity.class);
      // 4 - start the intent (or trigger it!)
      startActivity(goToOrderFormIntent);
      // May also be written as MainActivity.this.startActivity(goToOrderFormIntent) <- don't need "where you came from" in the Intent
    });
  }
}
