package com.edy.buymorestuff.activity;

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

import com.edy.buymorestuff.R;

public class ProductListActivity extends AppCompatActivity
{
  public final String TAG = "ProductListActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName";
  SharedPreferences preferences;


  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);

    // Initialization

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    // Get user nickname

    setUpOrderButton();
    setUpSettingsImageView();
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    String userNickname = preferences.getString(UserSettingsActivity.USER_NICKNAME_TAG, "No nickname");
    ((TextView)findViewById(R.id.productListNicknameTextView)).setText(getString(R.string.nickname_with_input, userNickname));
  }

  private void setUpOrderButton()
  {
    Button orderButton = (Button) findViewById(R.id.productListOrderButton);

    orderButton.setOnClickListener(v ->
    {
      String productName = ((EditText) findViewById(R.id.productNameEditText)).getText().toString();

      Intent goToOrderFormIntent = new Intent(ProductListActivity.this, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(PRODUCT_NAME_EXTRA_TAG, productName);
      startActivity(goToOrderFormIntent);
    });
  }

  private void setUpSettingsImageView()
  {
    ImageView userSettingsImageView = (ImageView) findViewById(R.id.userSettingsImageView);

    userSettingsImageView.setOnClickListener(v ->
    {
      Intent goToUserSettingsIntent = new Intent(ProductListActivity.this, UserSettingsActivity.class);
      startActivity(goToUserSettingsIntent);
    });
  }
}
