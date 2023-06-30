package com.edy.buymorestuff.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.AuthUser;
import com.amplifyframework.auth.AuthUserAttribute;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.temporal.Temporal;
import com.amplifyframework.datastore.generated.model.Contact;
import com.amplifyframework.datastore.generated.model.Product;
import com.amplifyframework.datastore.generated.model.ProductCategoryEnum;
import com.edy.buymorestuff.R;
import com.edy.buymorestuff.adapter.ProductListRecyclerViewAdapter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /*Contact contact1 =
      Contact.builder()
        .email("contact1@example.com")
        .fullName("Ed Younskevicius")
        .build();
    Amplify.API.mutate(
      ModelMutation.create(contact1),
      successResponse -> Log.i(TAG, "ProductListActivity.onCreate(): made a contact successfully"),  // success callback
      failureResponse -> Log.i(TAG, "ProductListActivity.onCreate(): contact failed with this response: " + failureResponse)  // failure callback
    ); */

    // Manually create an S3 file for testing

    /*String emptyFilename = "emptyTestFileName";
    File emptyFile = new File(getApplicationContext().getFilesDir(), emptyFilename);

    try
    {
      BufferedWriter emptyFileBufferedWriter = new BufferedWriter(new FileWriter(emptyFile));
      emptyFileBufferedWriter.append("Some test text here\nAnother line of test text");
      emptyFileBufferedWriter.close();  // Make sure to do this or the text may not be saved!
    } catch (IOException ioe)
    {
      Log.e(TAG, "Could not write file locally with filename: " + emptyFilename);
    }

    String emptyFileS3Key = "someFileOnS3.txt";

    Amplify.Storage.uploadFile(
      emptyFileS3Key,
      emptyFile,
      success ->
      {
        Log.i(TAG, "S3 upload succeeded! Key is: " + success.getKey());
      },
      failure ->
      {
        Log.i(TAG, "S3 upload failed! " + failure.getMessage());
      }
    );*/

    setUpAddProductButton();
    setUpSettingsImageView();
    setUpProductListRecyclerView();
    setUpLoginAndLogoutButton();
  }

  @Override
  protected void onResume()
  {
    super.onResume();

    //String userNickname = preferences.getString(UserSettingsActivity.USER_NICKNAME_TAG, "No nickname");
    AuthUser authUser = Amplify.Auth.getCurrentUser();
    String username = "";
    if (authUser == null)
    {
      Button loginButton = (Button) findViewById(R.id.productListLoginButton);
      loginButton.setVisibility(View.VISIBLE);
      Button logoutButton = (Button) findViewById(R.id.productListLogoutButton);
      logoutButton.setVisibility(View.INVISIBLE);
    }
    else  // authUser is not null
    {
      Log.i(TAG, "Username is: " + username);
      Button loginButton = (Button) findViewById(R.id.productListLoginButton);
      loginButton.setVisibility(View.INVISIBLE);
      Button logoutButton = (Button) findViewById(R.id.productListLogoutButton);
      logoutButton.setVisibility(View.VISIBLE);

      // Not strictly required for your lab, but useful for your project
      Amplify.Auth.fetchUserAttributes(
        success ->
        {
          Log.i(TAG, "Fetch user attributes succeeded for username: " + username);

          for (AuthUserAttribute userAttribute : success)
          {
            if (userAttribute.getKey().getKeyString().equals("nickname"))
            {
              String userNickname = userAttribute.getValue();
              runOnUiThread(() ->
                {
                  ((TextView) findViewById(R.id.productListNicknameTextView)).setText(userNickname);
                }
              );
            }
          }
        },
        failure ->
        {
          Log.i(TAG, "Fetch user attributes failed: " + failure.toString());
        }
      );
    }

    Amplify.API.query(
      ModelQuery.list(Product.class),
      success ->
      {
        Log.i(TAG, "Read products successfully!");
        products.clear();

        if (success.getData() != null)
        {
          for (Product databaseProduct : success.getData())
          {
            products.add(databaseProduct);
          }
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

  private void setUpLoginAndLogoutButton()
  {
    Button loginButton = (Button) findViewById(R.id.productListLoginButton);
    loginButton.setOnClickListener(v ->
    {
      Intent goToLogInIntent = new Intent(ProductListActivity.this, LoginActivity.class);
      startActivity(goToLogInIntent);
    });

    Button logoutButton = (Button) findViewById(R.id.productListLogoutButton);
    logoutButton.setOnClickListener(v ->
    {
      Amplify.Auth.signOut(
        () ->
        {
          Log.i(TAG, "Logout succeeded!");
          runOnUiThread(() ->
            {
              ((TextView) findViewById(R.id.productListNicknameTextView)).setText("");
            }
          );
          Intent goToLogInIntent = new Intent(ProductListActivity.this, LoginActivity.class);
          startActivity(goToLogInIntent);
        },
        failure ->
        {
          Log.i(TAG, "Logout failed: " + failure.toString());
          runOnUiThread(() ->
          {
            // TODO: None of the these Toasts in runOnUiThread() seem to work!
            // Probably just need .show()
            Toast.makeText(ProductListActivity.this, "Log out failed!", Toast.LENGTH_SHORT);
          });
        }
      );
    });
  }
}
