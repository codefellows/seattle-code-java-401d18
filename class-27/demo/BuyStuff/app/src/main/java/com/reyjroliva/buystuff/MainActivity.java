package com.reyjroliva.buystuff;

import static com.reyjroliva.buystuff.activities.UserProfileActivity.USER_NICKNAME_TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.reyjroliva.buystuff.activities.OrderFormActivity;
import com.reyjroliva.buystuff.activities.UserProfileActivity;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName"; // at top of class for sharing, public to access from other classes
  SharedPreferences preferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    setupSettingsButton();
    setupOrderFormButton();
  }

  @Override
  protected void onResume() {
    super.onResume();

    setupUsernameTextView();
  }

  void setupSettingsButton() {
    ((ImageView)findViewById(R.id.MainActivitySettingsButton)).setOnClickListener(v -> {
      Intent goToUserProfileActivityIntent = new Intent(MainActivity.this, UserProfileActivity.class);
      startActivity(goToUserProfileActivityIntent);
    });
  }

  void setupUsernameTextView() {
    String userNickname = preferences.getString(USER_NICKNAME_TAG, "No Nickname");
    ((TextView)findViewById(R.id.MainActivityUsernameTextView)).setText(userNickname);
  }

  void setupOrderFormButton() {
    Button orderFormButton = findViewById(R.id.MainActivityMoveToOrderFormButton);
    orderFormButton.setOnClickListener(v -> {
      // grabbing the product name from our edit text view
      String productName = ((EditText)findViewById(R.id.MainActivityProductNameInputTextView)).getText().toString();

      // creating an intent and putting an extra value
      Intent goToOrderFormIntent = new Intent(MainActivity.this, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(PRODUCT_NAME_EXTRA_TAG, productName);

      // trigger the intent
      startActivity(goToOrderFormIntent);
    });
  }
}
