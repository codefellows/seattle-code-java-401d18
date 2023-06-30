package com.edy.buymorestuff.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
import com.edy.buymorestuff.adapter.ProductListRecyclerViewAdapter;
import com.edy.buymorestuff.database.BuyMoreStuffDatabase;
import com.edy.buymorestuff.model.Product;
import com.edy.buymorestuff.model.ProductCategoryEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ProductListActivity extends AppCompatActivity
{
  public final String TAG = "ProductListActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName";
  public static final String DATABASE_NAME = "edy_buy_more_stuff";
  SharedPreferences preferences;
  // TODO: Step 1-5: Create and attach the RecyclerView.Adapter
  ProductListRecyclerViewAdapter adapter;

  List<Product> products = null;
  BuyMoreStuffDatabase buyMoreStuffDatabase;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);

    // Initialization

    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    buyMoreStuffDatabase = Room.databaseBuilder(
      getApplicationContext(),
      BuyMoreStuffDatabase.class,
      DATABASE_NAME)
      .allowMainThreadQueries()  // Don't do this in a real app!
      .fallbackToDestructiveMigration()  // If Room gets confused, it tosses your db
      .build();
    products = buyMoreStuffDatabase.productDao().findAll();

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

    products.clear();
    products.addAll(buyMoreStuffDatabase.productDao().findAll());
    adapter.notifyDataSetChanged();

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
