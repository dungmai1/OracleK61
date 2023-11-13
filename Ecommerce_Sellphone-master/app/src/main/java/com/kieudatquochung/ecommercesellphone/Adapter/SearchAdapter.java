package com.kieudatquochung.ecommercesellphone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kieudatquochung.ecommercesellphone.Activity.Product_Details;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;

import java.text.DecimalFormat;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    List<Product> items;
    Context context;
    public SearchAdapter(List<Product> items) {
        this.items = items;
    }

        @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        context = parent.getContext();
        return new SearchAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        holder.txtProductName.setText(items.get(position).getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(items.get(position).getPrice());
        holder.txtPrice.setText(PriceFormat+" VNĐ");
        Glide.with(holder.imageProduct.getContext())
                .load(items.get(position).getPictures())
                .into(holder.imageProduct);
        holder.itemView.setOnClickListener(v -> {
            Product product = items.get(position);
            Intent intent = new Intent(holder.itemView.getContext(), Product_Details.class);
            intent.putExtra("object", product);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtProductName, txtPrice;
        ImageView imageProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imageProduct = itemView.findViewById(R.id.imageProductSearch);
        }
    }
}
