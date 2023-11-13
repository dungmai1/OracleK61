package com.kieudatquochung.ecommercesellphone.Adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kieudatquochung.ecommercesellphone.Activity.OrderDetailsActivity;
import com.kieudatquochung.ecommercesellphone.Activity.Product_Details;
import com.kieudatquochung.ecommercesellphone.Models.OrderResponse;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    private List<OrderResponse> orderList;

    public OrderAdapter(List<OrderResponse> orderList) {
        this.orderList = orderList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_order, parent, false);
        return new OrderAdapter.ViewHolder(inflate);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtOrderDate.setText(orderList.get(position).getOrder_date().toString());
        holder.txtOrder_Delivery.setText(orderList.get(position).getPayment_Method());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), OrderDetailsActivity.class);
                intent.putExtra("orderId",orderList.get(position).getId());
                intent.putExtra("address",orderList.get(position).getAddress());
                intent.putExtra("payment_method",orderList.get(position).getPayment_Method());
                intent.putExtra("total",orderList.get(position).getOrder_total());

                holder.itemView.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtOrder_Delivery, txtOrderDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtOrder_Delivery = itemView.findViewById(R.id.txtOrder_Delivery);
            txtOrderDate = itemView.findViewById(R.id.txtOrderDate);
        }
    }
}
