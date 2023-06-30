package com.edy.buymorestuff.adapter;

import static com.edy.buymorestuff.activity.ProductListActivity.PRODUCT_NAME_EXTRA_TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.edy.buymorestuff.R;
import com.edy.buymorestuff.activity.OrderFormActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductListViewHolder>
{
  List<Product> products;
  Context callingActivity;

  public static final String TAG = "productListRecyclerViewAdapter";

  public ProductListRecyclerViewAdapter(List<Product> products, Context callingActivity)
  {
    this.products = products;
    this.callingActivity = callingActivity;
  }

  @NonNull
  @Override
  public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
    View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list, parent, false);

    return new ProductListViewHolder(productFragment);
  }

  @Override
  public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position)  // position is zero-indexed index into your list of items
  {
    TextView productFragmentTextView = (TextView) holder.itemView.findViewById(R.id.productFragmentTextView);
    Product product = products.get(position);
    DateFormat dateCreatedFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault());
    dateCreatedFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    String dateCreatedString = "";

    try
    {
      {
        Date dateCreatedJavaDate = dateCreatedFormat.parse(product.getDateCreated().format());
        if (dateCreatedJavaDate != null)
          dateCreatedString = dateCreatedFormat.format(dateCreatedJavaDate);
      }
    }
    catch (ParseException pe)
    {
      Log.e(TAG, "Error converting product date to String: " + pe.getMessage(), pe);
    }

    productFragmentTextView.setText(position + ". " + product.getName()
      + "\nDescription: " + product.getDescription()
      + "\nDate Created: " + dateCreatedString  // TODO: This is still 7 hours off!
      + "\nProduct Contact: " + product.getContactPerson().getFullName()
      + "\nProduct Category: " + product.getProductCategory());

    View productViewHolder = holder.itemView;
    productViewHolder.setOnClickListener(view -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(PRODUCT_NAME_EXTRA_TAG, product.getName());
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }

  @Override
  public int getItemCount()
  {
    return products.size();
  }

  public static class ProductListViewHolder extends RecyclerView.ViewHolder
  {
    public ProductListViewHolder(View fragmentItemView)
    {
      super(fragmentItemView);
    }
  }
}
