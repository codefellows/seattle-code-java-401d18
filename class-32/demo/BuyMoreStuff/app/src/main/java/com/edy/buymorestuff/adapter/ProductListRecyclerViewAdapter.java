package com.edy.buymorestuff.adapter;

import static com.edy.buymorestuff.activity.ProductListActivity.PRODUCT_NAME_EXTRA_TAG;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Product;
import com.edy.buymorestuff.R;
import com.edy.buymorestuff.activity.OrderFormActivity;

import java.util.List;

public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductListViewHolder>
{
  List<Product> products;
  Context callingActivity;

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

    productFragmentTextView.setText(position + ". " + product.getName()
      + "\n" + product.getDescription()
      + "\n" + product.getDateCreated()  // TODO: Make this less ugly
      + "\n" + product.getProductCategory());

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
