package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.kieudatquochung.ecommercesellphone.Adapter.CartListAdapter;
import com.kieudatquochung.ecommercesellphone.Adapter.OrderAdapter;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.OrderResponse;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityOrderBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_order);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getOrder();
        setTitle();
    }

    private void setTitle() {
        binding.include.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.include.txtActionBar.setText("Order History");
    }
    private void getOrder(){
        ApiService.apiservice.getAllOrder(SignInActivity.token).enqueue(new Callback<List<OrderResponse>>() {
            @Override
            public void onResponse(Call<List<OrderResponse>> call, Response<List<OrderResponse>> response) {
                List<OrderResponse> orderResponse = response.body();
                binding.rcvOrder.setAdapter(new OrderAdapter(orderResponse));
                binding.rcvOrder.setLayoutManager(new LinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false));
            }
            @Override
            public void onFailure(Call<List<OrderResponse>> call, Throwable t) {
            }
        });
    }
}