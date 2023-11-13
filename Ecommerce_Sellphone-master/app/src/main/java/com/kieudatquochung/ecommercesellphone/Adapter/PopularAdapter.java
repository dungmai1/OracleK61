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

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    List<PopularProductResponse> items;
    Context context;

    public PopularAdapter(List<PopularProductResponse> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_pop_list, parent, false);
        context = parent.getContext();
        return new PopularAdapter.ViewHolder(inflate);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtTitle.setText(items.get(position).getProduct().getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(items.get(position).getProduct().getPrice());
        holder.txtFee.setText(PriceFormat+" VNĐ");
        Glide.with(holder.pic.getContext())
                .load(items.get(position).getProduct().getPictures())
                .into(holder.pic);
        holder.itemView.setOnClickListener(v -> {
            Product product = items.get(position).getProduct();
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
