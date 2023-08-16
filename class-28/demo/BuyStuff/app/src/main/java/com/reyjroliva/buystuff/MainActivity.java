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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.reyjroliva.buystuff.activities.OrderFormActivity;
import com.reyjroliva.buystuff.activities.UserProfileActivity;
import com.reyjroliva.buystuff.adapters.ProductListRecyclerViewAdapter;
import com.reyjroliva.buystuff.models.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
  private final String TAG = "MainActivity";
  public static final String PRODUCT_NAME_EXTRA_TAG = "productName"; // at top of class for sharing, public to access from other classes
  SharedPreferences preferences;

  // TODO: Step 2-2: Make some data items
  List<Product> products = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    preferences = PreferenceManager.getDefaultSharedPreferences(this);

    setupSettingsButton();
    setupOrderFormButton();
    // NOTE: product instances must be created before we hand the products into the RecyclerView
    createProductInstances();
    setupRecyclerView();
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

  void setupRecyclerView() {
    // TODO: Step 1-2: Grab the RecyclerView
    RecyclerView productListRecyclerView = (RecyclerView) findViewById(R.id.MainActivityProductRecyclerView);

    // TODO: Step 1-3: Set the layout manager for the RecyclerView to a LinearLayoutManager
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

        // 
        if(parent.getChildAdapterPosition(view) == products.size()-1) {
          outRect.bottom = 0;
        }
      }
    });

    //// TODO: Step 1-5: Create and attach RecyclerView.Adapter to RecyclerView
    //ProductListRecyclerViewAdapter adapter = new ProductListRecyclerViewAdapter();
    //// TODO: Step 2-3: Hand data items from main Activity to our RecyclerViewAdapter
    //ProductListRecyclerViewAdapter adapter = new ProductListRecyclerViewAdapter(products);
    // TODO: Step 3-2: Hand in the Activity context to the adapter
    ProductListRecyclerViewAdapter adapter = new ProductListRecyclerViewAdapter(products, this);
    productListRecyclerView.setAdapter(adapter);
  }

  void createProductInstances() {
    // TODO: Step 2-2 cont.: fill list with data
    products.add(new Product("Pencil"));
    products.add(new Product("Ruler"));
    products.add(new Product("Binder"));
    products.add(new Product("Pen"));
    products.add(new Product("Notebook"));
    products.add(new Product("Eraser"));
  }
}
