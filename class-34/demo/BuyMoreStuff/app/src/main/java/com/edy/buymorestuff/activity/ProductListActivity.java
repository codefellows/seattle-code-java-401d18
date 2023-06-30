package com.edy.buymorestuff.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.edy.buymorestuff.adapter.ProductListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class ProductListActivity extends AppCompatActivity
{
  public final String TAG = "ProductListActivity";
  public static final String PRODUCT_ID_TAG = "Product ID Tag";
  SharedPreferences preferences;
  ProductListRecyclerViewAdapter adapter;

  List<Product> products = null;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);

    init();

    // Hardcoding Contacts (like your lab asks you to do)

    /*Contact contact1 =
      Contact.builder()
        .email("contact1@example.com")
        .fullName("Ed Younskevicius")
        .build();
    Amplify.API.mutate(
      ModelMutation.create(contact1),
      successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a contact successfully"),  // success callback
      failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): contact failed with this response: " + failureResponse)  // failure callback
    );

    Contact contact2 =
      Contact.builder()
        .email("contact2@example.com")
        .fullName("Hambalieu Jallow")
        .build();
    Amplify.API.mutate(
      ModelMutation.create(contact2),
      successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a 2nd contact successfully"),  // success callback
      failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): 2nd contact failed with this response: " + failureResponse)  // failure callback
    );

    Contact contact3 =
      Contact.builder()
        .email("contact3@example.com")
        .fullName("Kevin LaMarca")
        .build();
    Amplify.API.mutate(
      ModelMutation.create(contact3),
      successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a 3rd contact successfully"),  // success callback
      failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): 3rd contact failed with this response: " + failureResponse)  // failure callback
    );
    */

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

    Amplify.API.query(
      ModelQuery.list(Product.class),
      success ->
      {
        Log.i(TAG, "Read products successfully!");
        products.clear();

        for (Product databaseProduct : success.getData())
        {
          products.add(databaseProduct);
        }

        runOnUiThread(() ->
        {
          adapter.notifyDataSetChanged();
        });
      },
      failure -> Log.i(TAG, "Did not read products successfully!")
    );
  }

  private void init()
  {
    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    products = new ArrayList<>();
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
    RecyclerView productListRecyclerView = (RecyclerView) findViewById(R.id.productListRecyclerView);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    productListRecyclerView.setLayoutManager(layoutManager);

    adapter = new ProductListRecyclerViewAdapter(products, this);  // NOTE: don't need data items in 1-series of steps

    productListRecyclerView.setAdapter(adapter);
  }
}
