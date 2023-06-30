package com.edy.buymorestuff.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.edy.buymorestuff.adapter.ProductListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductListActivity extends AppCompatActivity
{
  public final String TAG = "ProductListActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName";
  SharedPreferences preferences;
  ProductListRecyclerViewAdapter adapter;

  List<Product> products = null;

  @Override

  // Steps to convert your app from Room to using Amplify and DynamoDb:
  // (DONE) 1. Delete all your database classes (database and DAOs)
  // (DONE) 2. Remove the Room additions to your build.gradle file
  // (DONE) 3. Remove all the Room annotations and imports from your files
  // (DONE) 4. Add AWS Amplify dependencies to your build.gradle files
  // (DONE) 5. Add an TaskStuffThingThatIMadeApplication file which extends Application in your main project area
  // (DONE) 6. Put your Amplify configuration in that application file
  // (DONE) 7. Update your schema.graphql file to model your data model correctly
  // (DONE) 8. Run amplify codegen models to generate those models
  // (DONE) 9a. Delete your old model and convert every usage in your application to use the amplify models
  // (DONE) 9b. Specifically, every DAO usage needs to be converted to Amplify.API usage
  // (Sort of!) 10. Change your RecyclerViewAdapter to have better string output
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_list);

    // Initialization

    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    products = new ArrayList<>();

    // Testing creating Amplify model class
    /*String currentDateString = com.amazonaws.util.DateUtils.formatISO8601Date(new Date());
    com.amplifyframework.datastore.generated.model.Product testProduct =
      com.amplifyframework.datastore.generated.model.Product.builder()
        .name("Product Name Here")  // required section, can't get to non-required properties yet
        .description("It's a cool product")
        .dateCreated(new Temporal.DateTime(currentDateString))
        .productCategory(ProductCategoryEnum.Misc)
        .build();
    Amplify.API.mutate(
      ModelMutation.create(testProduct),  // making a GraphQL request to the cloud
      successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a product successfully"),  // success callback
      failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): failed with this response: " + failureResponse)  // failure callback
    );*/

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

    // TODO: Let's try this the CompletableFuture way next time

    Amplify.API.query(
      ModelQuery.list(Product.class),
      success ->
      {
        Log.i(TAG, "Read products successfully!");
        products.clear();
        //products = new ArrayList<>();
        for (Product databaseProduct : success.getData())
        {
          products.add(databaseProduct);
        }

        runOnUiThread(() ->
        {
          //adapter.products = products;
          adapter.notifyDataSetChanged();
        });
      },
      failure -> Log.i(TAG, "Did not read products successfully!")
    );
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
