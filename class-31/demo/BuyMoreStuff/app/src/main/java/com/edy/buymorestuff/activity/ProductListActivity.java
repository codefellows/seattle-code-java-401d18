package com.edy.buymorestuff.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edy.buymorestuff.R;
import com.edy.buymorestuff.adapter.ProductListRecyclerViewAdapter;
import com.edy.buymorestuff.model.Product;
import com.edy.buymorestuff.model.ProductCategoryEnum;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity
{
  public final String TAG = "ProductListActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName";
  SharedPreferences preferences;
  // TODO: Step 1-5: Create and attach the RecyclerView.Adapter
  ProductListRecyclerViewAdapter adapter;

  List<Product> products = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);

    // Initialization

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    // TODO: Change this to a Dynamo / GraphQL query
    //products = buyMoreStuffDatabase.productDao().findAll();
    products = new ArrayList<>();
    products.add(new Product("Test Product", "It's the test product", new java.util.Date(), ProductCategoryEnum.CLOTHES));

    // Get user nickname

    setUpAddProductButton();
    setUpSettingsImageView();
    setUpProductListRecyclerView();
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    String userNickname = preferences.getString(UserSettingsActivity.USER_NICKNAME_TAG, "No nickname");
    ((TextView)findViewById(R.id.productListNicknameTextView)).setText(getString(R.string.nickname_with_input, userNickname));

    // Here is an example of removing things in case you want to try it!
    //products.remove(0);
    //adapter.notifyItemRemoved(0);
    //adapter.notifyDataSetChanged();  You can be lazy and do this all the time if you want
  }

  private void setUpAddProductButton()
  {
    Button addProductButton = findViewById(R.id.addProductButton);
    addProductButton.setOnClickListener(v ->
    {
      Intent goToAddProductActivity = new Intent(ProductListActivity.this, AddProductActivity.class);
      startActivity(goToAddProductActivity);
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

  private void setUpProductListRecyclerView()
  {
    // TODO: Step 1-1: Add a RecyclerView to the Activity in the WSYIWYG editor
    // TODO: Step 1-2: Grab the RecyclerView
    RecyclerView productListRecyclerView = (RecyclerView) findViewById(R.id.productListRecyclerView);
    // TODO: Step 1-3: Set the layout manager of the RecyclerView to a LinearLayoutManager
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    // If you want to do a horizontal list:
    //((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
    productListRecyclerView.setLayoutManager(layoutManager);
    // TODO: Step 2-1: Make some data items
    //products.add(new Product("Pens", "Pens are very useful for writing", new Date(), ProductCategoryEnum.OFFICE_SUPPLIES));
    //buyMoreStuffDatabase.productDao().insertAProduct(products.get(0));

    // TODO: Step 1-5: Create and attach the RecyclerView.Adapter
    // TODO: Step 2-2: Hand in data items
    adapter = new ProductListRecyclerViewAdapter(products, this);  // NOTE: don't need data items in 1-series of steps

    productListRecyclerView.setAdapter(adapter);
  }
}
