package com.reyjroliva.buystuff.adapters;

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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

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
    DateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm zzz"); // Consider passing the current phone's Local to suppress warning
    String productFragmentText = (position) + ". " + products.get(position).getName()
      + "\n" + products.get(position).getDescription()
      + "\n" + dateFormatter.format(products.get(position).getDateCreated())
      + "\n" + products.get(position).getCategory();

    productFragmentTextView.setText(productFragmentText);

    View productViewHolder = holder.itemView;
    productViewHolder.setOnClickListener(v -> {
      Intent goToOrderFormIntent = new Intent(callingActivity, OrderFormActivity.class);
      goToOrderFormIntent.putExtra(MainActivity.PRODUCT_NAME_EXTRA_TAG, products.get(position).getName());
      callingActivity.startActivity(goToOrderFormIntent);
    });
  }

  @Override
  public int getItemCount() {
    return this.products.size();
  }

  public static class ProductListViewHolder extends RecyclerView.ViewHolder {
    public ProductListViewHolder(@NonNull View itemView) {
      super(itemView);
    }
  }
}
