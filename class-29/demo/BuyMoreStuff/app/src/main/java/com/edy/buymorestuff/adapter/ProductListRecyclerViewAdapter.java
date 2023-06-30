package com.edy.buymorestuff.adapter;

import static com.edy.buymorestuff.activity.ProductListActivity.PRODUCT_NAME_EXTRA_TAG;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.edy.buymorestuff.R;
import com.edy.buymorestuff.activity.OrderFormActivity;
import com.edy.buymorestuff.model.Product;

import java.util.List;

// TODO: Step 1-4: Make a class whose sole purpose is to manage RecyclerViews: a RecyclerView.Adapter
// TODO: Step 3-1: Clean up the RecyclerView.Adapter references to actually use ProductListRecyclerViewAdapter
// (You'll need to change it in the methods below also)
public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductListViewHolder>
{
  // TODO: Step 2-2: Hand in data items
  List<Product> products;
  Context callingActivity;

  // TODO: Step 2-2: Hand in data items
  // TODO: Step 3-3: Hand in the activity context
  public ProductListRecyclerViewAdapter(List<Product> products, Context callingActivity)
  {
    this.products = products;
    this.callingActivity = callingActivity;
  }

  @NonNull
  @Override
  public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
  {
    // TODO: Step 1-7: Inflate fragment
    View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list, parent, false);

    // TODO: Step 1-9: Attach fragment to ViewHolder
    return new ProductListViewHolder(productFragment);
  }

  @Override
  // TODO: Step 2-3: Bind data items to Fragments inside of ViewHolders
  public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position)  // position is zero-indexed index into your list of items
  {
    TextView productFragmentTextView = (TextView) holder.itemView.findViewById(R.id.productFragmentTextView);
    Product product = products.get(position);

    productFragmentTextView.setText(position + ". " + product.getName()
      + "\n" + product.getDescription()
      + "\n" + product.getDateCreated()
      + "\n" + product.getProductCategory());

    // TODO: Step 3-2: Make OnClickHandler so we can interact with the RecyclerView items
    View productViewHolder = holder.itemView;
    productViewHolder.setOnClickListener(view -> {
      // TODO: Step 3-4: Create Intent, populate data, and call Intent
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(PRODUCT_NAME_EXTRA_TAG, product.getName());
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }

  @Override
  public int getItemCount()
  {
    // TODO: Step 1-10: For testing purposes, hardcode a large number of items
    // TODO: Step 2-4: Make the size of the list dynamic

    return products.size();
  }

  // TODO: Step 1-8: Make a ViewHolder class to hold a fragment

  public static class ProductListViewHolder extends RecyclerView.ViewHolder
  {
    public ProductListViewHolder(View fragmentItemView)
    {
      super(fragmentItemView);
    }
  }
}
