package com.reyjroliva.buystuff.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;

import java.io.File;

public class OrderFormActivity extends AppCompatActivity {
  private static String TAG = "OrderFormActivity";

  Intent callingIntent;
  Product currentProduct;
  String s3ImageKey;

  ImageView productImageView;
  TextView productNameTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_order_form);

    callingIntent = getIntent();

    productImageView = findViewById(R.id.OrderFormActivityProductImageView);
    productNameTextView = findViewById(R.id.OrderFormProductNameTextView);

    setupProductImageView();
    setupProductNameTextView();
  }

  void setupProductImageView() {
    String productId = "";
    if(callingIntent != null) {
      productId = callingIntent.getStringExtra(MainActivity.PRODUCT_ID_EXTRA_TAG);
    }

    if(!productId.equals("")) {
      // query for for product from dynamoDB
      Amplify.API.query(
        ModelQuery.get(Product.class, productId),
        success -> {
          Log.i(TAG, "successfully found product with id: " + success.getData().getId());
          currentProduct = success.getData();
          populateImageView();
        },
        failure -> {
          Log.i(TAG,"Failed to query product from DB: " + failure.getMessage());
        }
      );
    }
  }

  void populateImageView() {
    // truncate folder name from product's s3key
    currentProduct.getProductImageS3Key();
    if(currentProduct.getProductImageS3Key() != null) {
      int cut = currentProduct.getProductImageS3Key().lastIndexOf('/');
      if(cut != -1) {
        s3ImageKey = currentProduct.getProductImageS3Key().substring(cut + 1);
      }
    }

    if(s3ImageKey != null && !s3ImageKey.isEmpty()) {
      Amplify.Storage.downloadFile(
        s3ImageKey,
        new File(getApplication().getFilesDir(), s3ImageKey),
        success -> {
          productImageView.setImageBitmap(BitmapFactory.decodeFile(success.getFile().getPath()));
        },
        failure -> {
          Log.e(TAG, "Unable to get image from S3 for the product for S3 key: " + s3ImageKey + " for reason: " + failure.getMessage());
        }
      );
    }
  }

  void setupProductNameTextView() {
    String productNameString = null;
    if(callingIntent != null) {
      productNameString = callingIntent.getStringExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG);
    }

    if(productNameString != null && !productNameString.equals("")) {
      productNameTextView.setText(productNameString);
    } else {
      productNameTextView.setText(R.string.no_product_name);
    }
  }

}
