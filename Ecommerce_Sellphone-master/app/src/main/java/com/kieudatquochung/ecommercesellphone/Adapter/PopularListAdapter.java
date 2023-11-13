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
import com.kieudatquochung.ecommercesellphone.Models.PopularProductResponse;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;

import java.text.DecimalFormat;
import java.util.List;

public class PopularListAdapter extends RecyclerView.Adapter<PopularListAdapter.ViewHolder> {
    List<Product> items;
    Context context;

    public PopularListAdapter(List<Product> items) {
        this.items = items;
    }
    @NonNull
    @Override
    public PopularListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pop_list, parent, false);
        context = parent.getContext();
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull PopularListAdapter.ViewHolder holder, int position) {
        holder.txtTitle.setText(items.get(position).getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(items.get(position).getPrice());
        holder.txtFee.setText(PriceFormat+" VNĐ");
        Glide.with(holder.pic.getContext())
                .load(items.get(position).getPictures())
                .into(holder.pic);
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
        TextView txtTitle, txtFee;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtFee = itemView.findViewById(R.id.txtFee);
            pic = itemView.findViewById(R.id.pic);
        }
    }
}
