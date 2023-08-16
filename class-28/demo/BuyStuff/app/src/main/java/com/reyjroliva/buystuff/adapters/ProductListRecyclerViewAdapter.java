package com.reyjroliva.buystuff.adapters;

import static com.reyjroliva.buystuff.MainActivity.PRODUCT_NAME_EXTRA_TAG;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reyjroliva.buystuff.MainActivity;
import com.reyjroliva.buystuff.R;
import com.reyjroliva.buystuff.activities.OrderFormActivity;
import com.reyjroliva.buystuff.models.Product;

import java.util.List;

// TODO: Step 1-4: Make a custom RecyclerViewAdapter class which extends RecyclerView.Adapter
//public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter {
// TODO: Step 3-1: Refactor Adpater class to work specifically with our custom View Holder
public class ProductListRecyclerViewAdapter extends RecyclerView.Adapter<ProductListRecyclerViewAdapter.ProductListViewHolder> {
  // TODO: Step 2-3 cont.: Create a Product List variable and constructor wihtin the adapter
  List<Product> products;
  // TODO: Step 3-2 cont.: Create a Context variable and update constructor
  Context callingActivity;

  public ProductListRecyclerViewAdapter(List<Product> products, Context callingActivity) {
    this.products = products;
    this.callingActivity = callingActivity;
  }

  @NonNull
  @Override
  public ProductListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // TODO: Step 1-7: Inflate the fragment (add the fragment to the viewHolder)
    View productFragment = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product_list, parent, false);
    // TODO: Step 1-9: Attach fragment to the ViewHolder
    return new ProductListViewHolder(productFragment);
  }

  @Override
  public void onBindViewHolder(@NonNull ProductListViewHolder holder, int position) {
    // TODO: Step 2-4: Bind data items to Fragments inside of ViewsHolders
    TextView productFragmentTextView = (TextView) holder.itemView.findViewById(R.id.productFragmentTextView);
    String productFragmentText = (position+1) + ". " + products.get(position).getName();
    productFragmentTextView.setText(productFragmentText);

    // TODO: Step 3-3: Create an onClickListener, make an intent inside of it, and call this intent with an extra to go to a new activity
    View productViewHolder = holder.itemView;
    productViewHolder.setOnClickListener(v -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG, products.get(position).getName());
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }

  @Override
  public int getItemCount() {
    //// TODO: Step 1-10: Set the number of items you'd like to see
    //return 10;
    // TODO: Step 2-5: Make the size of the list dynamic based on the size of the product list
    return products.size();
  }

  // TODO: Step 1-8: Make a ViewHolder class to hold our fragments (nested within ProductListRecyclerViewAdapter)
  public static class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ProductListViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
