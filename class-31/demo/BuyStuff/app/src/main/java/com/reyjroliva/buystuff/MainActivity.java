package com.reyjroliva.buystuff;

import static com.reyjroliva.buystuff.activities.UserProfileActivity.USER_NICKNAME_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.buystuff.activities.AddProductActivity;
import com.reyjroliva.buystuff.activities.OrderFormActivity;
import com.reyjroliva.buystuff.activities.UserProfileActivity;
import com.reyjroliva.buystuff.adapters.ProductListRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName"; // at top of class for sharing, public to access from other classes

  List<Product> products;
  ProductListRecyclerViewAdapter adapter;
  SharedPreferences preferences;

  Button addProductButton;
  Button orderFormButton;
  EditText productNameEditText;
  ImageView settingsButton;
  RecyclerView productListRecyclerView;
  TextView usernameTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);
    products = new ArrayList<>();

    addProductButton = findViewById(R.id.MainActivityMoveToAddProductButton);
    orderFormButton = findViewById(R.id.MainActivityMoveToOrderFormButton);
    productListRecyclerView = findViewById(R.id.MainActivityProductRecyclerView);
    productNameEditText = findViewById(R.id.MainActivityProductNameInputTextView);
    settingsButton = findViewById(R.id.MainActivitySettingsButton);
    usernameTextView = findViewById(R.id.MainActivityUsernameTextView);

    setupAddProductButton();
    setupOrderFormButton();
    updateProductListFromDatabase();
    setupRecyclerView();
    setupSettingsButton();
  }

  @Override
  protected void onResume() {
    super.onResume();

    updateProductListFromDatabase();

    setupUsernameTextView();
  }

  void setupAddProductButton() {
    addProductButton.setOnClickListener(v -> {
      Intent goToAddProductActivityIntent = new Intent(MainActivity.this, AddProductActivity.class);
      startActivity(goToAddProductActivityIntent);
    });
  }

  void setupOrderFormButton() {
    orderFormButton.setOnClickListener(v -> {
      // grabbing the product name from our edit text view
      String productName = productNameEditText.getText().toString();

      // creating an intent and putting an extra value
      Intent goToOrderFormIntent = new Intent(MainActivity.this, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(PRODUCT_NAME_EXTRA_TAG, productName);

      // trigger the intent
      startActivity(goToOrderFormIntent);
    });
  }

  void updateProductListFromDatabase() {
    Amplify.API.query(
      ModelQuery.list(Product.class),
      success -> {
        Log.i(TAG, "Read products succcessfully!");
        products.clear();
        for(Product databaseProduct : success.getData()) {
          products.add(databaseProduct);
        }

        runOnUiThread(() -> {
          adapter.notifyDataSetChanged();
        });
      },
      failure -> Log.i(TAG, "Did not read products successfully")
    );
  }

  void setupSettingsButton() {
    settingsButton.setOnClickListener(v -> {
      Intent goToUserProfileActivityIntent = new Intent(MainActivity.this, UserProfileActivity.class);
      startActivity(goToUserProfileActivityIntent);
    });
  }

  void setupRecyclerView() {
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    productListRecyclerView.setLayoutManager(layoutManager);

    // Extra RecyclerView Styling: Add itemDecoration with desired spacing
    int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.product_fragment_spacing);
    productListRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override
      public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = spaceInPixels;

        if (parent.getChildAdapterPosition(view) == products.size() - 1) {
          outRect.bottom = 0;
        }
      }
    });

    adapter = new ProductListRecyclerViewAdapter(products, this);
    productListRecyclerView.setAdapter(adapter);
  }

  void setupUsernameTextView() {
    String userNickname = preferences.getString(USER_NICKNAME_TAG, "No Nickname");
    usernameTextView.setText(userNickname);
  }
}
