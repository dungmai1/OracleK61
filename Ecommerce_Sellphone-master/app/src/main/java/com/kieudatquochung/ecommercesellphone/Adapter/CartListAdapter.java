package com.kieudatquochung.ecommercesellphone.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kieudatquochung.ecommercesellphone.Activity.CartActivity;
import com.kieudatquochung.ecommercesellphone.Activity.SignInActivity;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.Helper.ChangNumberItemListener;
import com.kieudatquochung.ecommercesellphone.Helper.ManagementCart;
import com.kieudatquochung.ecommercesellphone.Models.cartItemDTOList;
import com.kieudatquochung.ecommercesellphone.R;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    List<cartItemDTOList> cartItemDTOLists;
    private Context context;
    private ManagementCart managementCart;
    private int total;
    CartActivity cartActivity;
    TextView txtTotal;

    public CartListAdapter(List<cartItemDTOList> cartItemDTOLists, TextView txtTotal) {
        this.cartItemDTOLists = cartItemDTOLists;
        this.txtTotal = txtTotal;
    }

    @NonNull
    @Override
    public CartListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtProductName.setText(cartItemDTOLists.get(position).getProduct().getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(cartItemDTOLists.get(position).getProduct().getPrice());
        holder.txtPrice.setText(PriceFormat+" VND");
        Glide.with(holder.imageCart.getContext())
                .load(cartItemDTOLists.get(position).getProduct().getPictures())
                .into(holder.imageCart);
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItemDTOLists == null){
                }else{
                    ApiService.apiservice.deleteCart(cartItemDTOLists.get(position).getId(),SignInActivity.token).enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                            cartItemDTOLists.remove(position);
                            updateprice();
                            notifyItemRemoved(position);
                            Toast.makeText(view.getContext(), "Xóa thành công", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            Toast.makeText(view.getContext(), "Xóa thất bại", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        holder.num.setText(String.valueOf(cartItemDTOLists.get(position).getQuantity()));
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartItemDTOLists.get(position).setQuantity(cartItemDTOLists.get(position).getQuantity() + 1);
                int productId = cartItemDTOLists.get(position).getProduct().getId();
                updateQuantityIncrease(productId,position);
                notifyDataSetChanged();
                updateprice();
            }
        });
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = cartItemDTOLists.get(position).getQuantity();
                if (count > 1) {
                    cartItemDTOLists.get(position).setQuantity(cartItemDTOLists.get(position).getQuantity() - 1);
                    int productId = cartItemDTOLists.get(position).getProduct().getId();
                    updateQuantityDecrease(productId,position);
                    total = count * cartItemDTOLists.get(position).getProduct().getPrice();
                    notifyDataSetChanged();
                    updateprice();
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        if (cartItemDTOLists != null) {
            return cartItemDTOLists.size();
        } else {
            return 0;
        }
    }
    public int getSelectItem(){
        return total;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtProductName, txtPrice, plusItem, minusItem;
        ImageView imageCart,btnDelete;
        TextView num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.txtProductName);
            imageCart = itemView.findViewById(R.id.imageCart);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            plusItem = itemView.findViewById(R.id.txtPlusCart);
            minusItem = itemView.findViewById(R.id.txtMinusCart);
            num = itemView.findViewById(R.id.txtNumberItem);
            btnDelete= itemView.findViewById(R.id.btnDelete);
        }
    }
    private void updateQuantityDecrease(int product_id,int position){
        ApiService.apiservice.updateQuantityDecrease(SignInActivity.token,product_id).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
            }
        });
        notifyItemChanged(position);
    }
    private void updateQuantityIncrease(int product_id,int position){
        ApiService.apiservice.updateQuantityIncrease(SignInActivity.token,product_id).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
            }
        });
        notifyItemChanged(position);
    }
    public void updateprice()
    {
        int sum=0,i;
        for (i = 0; i < cartItemDTOLists.size(); i++) {
            sum = sum + cartItemDTOLists.get(i).getQuantity() * cartItemDTOLists.get(i).getProduct().getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(sum);
        txtTotal.setText(PriceFormat+" VNĐ");
    }

}
