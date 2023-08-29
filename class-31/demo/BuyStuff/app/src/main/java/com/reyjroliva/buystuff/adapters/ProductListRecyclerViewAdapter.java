package com.reyjroliva.buystuff.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;
import com.reyjroliva.buystuff.activities.OrderFormActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import kotlin.time.TimeSource;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductListViewHolder> {
  Context callingActivity;
  List<Product> products;

  public ProductListRecyclerViewAdapter(List<Product> products, Context callingActivity) {
    this.products = products;
    this.callingActivity = callingActivity;
  }

  @NonNull
  @Override
  public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list, parent, false);
    return new ProductListViewHolder(productFragment);
  }

  @Override
  public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
    TextView productFragmentTextView = holder.itemView.findViewById(R.id.productFragmentTextView);
    String dateString = formatDateString(products.get(position));
    String productFragmentText = (position) + ". " + products.get(position).getName()
      + "\n" + products.get(position).getDescription()
      + "\n" + dateString
      + "\n" + products.get(position).getProductCategory();

    productFragmentTextView.setText(productFragmentText);

    View productViewHolder = holder.itemView;
    productViewHolder.setOnClickListener(v -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG, products.get(position).getName());
      goToOrderFormIntent.putExtra(MainActivity.PRODUCT_ID_EXTRA_TAG, products.get(position).getId());
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }

  @Override
  public int getItemCount() {
    return this.products.size();
  }

  private String formatDateString(Product product) {
    DateFormat dateCreatedIso8601InputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    dateCreatedIso8601InputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    DateFormat dateCreatedOutputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    dateCreatedOutputFormat.setTimeZone(TimeZone.getDefault());
    String dateCreatedString = "";

    try {
        Date dateCreatedJavaDate = dateCreatedIso8601InputFormat.parse(product.getDateCreated().format());
        if (dateCreatedJavaDate != null) {
          dateCreatedString = dateCreatedOutputFormat.format(dateCreatedJavaDate);
        }
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return dateCreatedString;
  }

  public static class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ProductListViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
