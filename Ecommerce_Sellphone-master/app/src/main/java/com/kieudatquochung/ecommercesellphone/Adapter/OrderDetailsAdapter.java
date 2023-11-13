package com.kieudatquochung.ecommercesellphone.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kieudatquochung.ecommercesellphone.Models.OrderDetailsResponse;
import com.kieudatquochung.ecommercesellphone.R;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ViewHolder>{
    List<OrderDetailsResponse> orderDetailsResponseList;

    public OrderDetailsAdapter(List<OrderDetailsResponse> orderDetailsResponseList) {
        this.orderDetailsResponseList = orderDetailsResponseList;
    }

    @NonNull
    @Override
    public OrderDetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order_details, parent, false);
        return new OrderDetailsAdapter.ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailsAdapter.ViewHolder holder, int position) {
        holder.txtProductName.setText(orderDetailsResponseList.get(position).getProduct().getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(orderDetailsResponseList.get(position).getProduct().getPrice());
        holder.txtPrice.setText(PriceFormat+" VNĐ");
        holder.txtQuantity.setText("x"+String.valueOf(orderDetailsResponseList.get(position).getQuantity()));
        Glide.with(holder.imageProduct.getContext())
                .load(orderDetailsResponseList.get(position).getProduct().getPictures())
                .into(holder.imageProduct);
    }
    @Override
    public int getItemCount() {
        if (orderDetailsResponseList != null) {
            return orderDetailsResponseList.size();
        } else {
            return 0;
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtPrice,txtQuantity,txtAddressOrder,txtPayment,txtTotal;
        ImageView imageProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            imageProduct = itemView.findViewById(R.id.imageProduct);
            txtAddressOrder = itemView.findViewById(R.id.txtAddressOrder);
            txtPayment = itemView.findViewById(R.id.txtPayment);
            txtTotal = itemView.findViewById(R.id.txtTotal);

        }
    }
}
