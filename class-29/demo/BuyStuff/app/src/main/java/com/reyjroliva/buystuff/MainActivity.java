package com.reyjroliva.buystuff;

import static com.reyjroliva.buystuff.activities.UserProfileActivity.USER_NICKNAME_TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.reyjroliva.buystuff.activities.AddProductActivity;
import com.reyjroliva.buystuff.activities.OrderFormActivity;
import com.reyjroliva.buystuff.activities.UserProfileActivity;
import com.reyjroliva.buystuff.adapters.ProductListRecyclerViewAdapter;
import com.reyjroliva.buystuff.database.BuyStuffDatabase;
import com.reyjroliva.buystuff.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName"; // at top of class for sharing, public to access from other classes
  SharedPreferences preferences;
  List<Product> products = new ArrayList<>();

  BuyStuffDatabase buyStuffDatabase; // place at the top of class with other properties
  public static final String DATABASE_NAME = "reyjroliva_buy_stuff"; // also at top of class, needs to be the same value everywhere in the app!
  ProductListRecyclerViewAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    setupDatabase();
    setupSettingsButton();
    setupAddProductButton();
    setupOrderFormButton();
    // NOTE: product instances must be created before we hand the products into the RecyclerView
    setupRecyclerView();
  }

  @Override
  protected void onResume() {
    super.onResume();

    setupUsernameTextView();
    updateProductListFromDatabase();
  }

  void setupDatabase() {
    buyStuffDatabase = Room.databaseBuilder(
      getApplicationContext(),
      BuyStuffDatabase.class,
      DATABASE_NAME)
      .fallbackToDestructiveMigration() // If Room gets confused, it tosses your database; turn this off in production!
      .allowMainThreadQueries()
      .build();

    products = buyStuffDatabase.productDao().findAll(); // to task that the database works, even through we're not returning the value anywhere (yet!)
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

  void setupAddProductButton() {
    Button addProductButton = findViewById(R.id.MainActivityMoveToAddProductButton);
    addProductButton.setOnClickListener(v -> {
      Intent goToAddProductActivityIntent = new Intent(MainActivity.this, AddProductActivity.class);
      startActivity(goToAddProductActivityIntent);
    });
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

  void setupRecyclerView() {
    RecyclerView productListRecyclerView = (RecyclerView) findViewById(R.id.MainActivityProductRecyclerView);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    // If you want to do a horizontal list:
    //((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
    productListRecyclerView.setLayoutManager(layoutManager);

    // Extra RecyclerView Styling: Add itemDecoration with desired spacing
    int spaceInPixels = getResources().getDimensionPixelSize(R.dimen.product_fragment_spacing);
    productListRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
      @Override
      public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = spaceInPixels;

        if(parent.getChildAdapterPosition(view) == products.size()-1) {
          outRect.bottom = 0;
        }
      }
    });

    adapter = new ProductListRecyclerViewAdapter(products, this);
    productListRecyclerView.setAdapter(adapter);
  }

  void updateProductListFromDatabase() {
    products.clear();
    products.addAll(buyStuffDatabase.productDao().findAll());
    adapter.notifyDataSetChanged();
  }
}
