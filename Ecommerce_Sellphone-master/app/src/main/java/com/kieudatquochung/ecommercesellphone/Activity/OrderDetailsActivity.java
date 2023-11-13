package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.Adapter.CartListAdapter;
import com.kieudatquochung.ecommercesellphone.Adapter.OrderDetailsAdapter;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.OrderDetailsResponse;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityOrderDetailsBinding;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {
    ActivityOrderDetailsBinding binding;
    List<OrderDetailsResponse> orderDetailsResponseList;
    private int orderId,total;
    private String address, payment_method;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_order_details);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getOrderID();
        getOrderDetails();
        setTitle();
    }

    private void setTitle() {
        binding.include.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.include.txtActionBar.setText("Chi tiết đơn hàng");
        binding.txtAddressOrder.setText(address);
        binding.txtPayment.setText(payment_method);
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(total);
        binding.txtTotal.setText(PriceFormat+" VNĐ");
    }
    private void getOrderID() {
        Intent intent = getIntent();
        orderId = intent.getIntExtra("orderId",-1);
        total = intent.getIntExtra("total",-1);
        address = intent.getStringExtra("address");
        payment_method = intent.getStringExtra("payment_method");
    }
    private void getOrderDetails(){
        ApiService.apiservice.getAllOrderDetails(SignInActivity.token,orderId).enqueue(new Callback<List<OrderDetailsResponse>>() {
            @Override
            public void onResponse(Call<List<OrderDetailsResponse>> call, Response<List<OrderDetailsResponse>> response) {
                if(response.isSuccessful()){
                    orderDetailsResponseList = response.body();
                    binding.rcvOrderDetails.setAdapter(new OrderDetailsAdapter(orderDetailsResponseList));
                    binding.rcvOrderDetails.setLayoutManager(new LinearLayoutManager(OrderDetailsActivity.this, LinearLayoutManager.VERTICAL, false));
                    Toast.makeText(OrderDetailsActivity.this, "order details", Toast.LENGTH_LONG).show();
                }
                else {
                    int statusCode = response.code();
                    Toast.makeText(OrderDetailsActivity.this, "Response fail: " + statusCode + " - " + response.errorBody(), Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<OrderDetailsResponse>> call, Throwable t) {
                Toast.makeText(OrderDetailsActivity.this, "fail", Toast.LENGTH_LONG).show();

            }
        });
    }
}