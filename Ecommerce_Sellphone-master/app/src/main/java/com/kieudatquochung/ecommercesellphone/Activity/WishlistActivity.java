package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.Adapter.PopularListAdapter;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityWishlistBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishlistActivity extends AppCompatActivity {
    List<Product> products;
    ActivityWishlistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWishlistBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_wishlist);
        setContentView(binding.getRoot());
        setToolbar();
        getWishlist();
    }

    private void setToolbar() {
        binding.include.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.include.txtActionBar.setText("Sản Phẩm Yêu Thích");
    }
    private void getWishlist() {
        ApiService.apiservice.getWishlist(SignInActivity.token).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                GridLayoutManager gridLayoutManager = new GridLayoutManager(WishlistActivity.this, 2);
                binding.rcvWishlist.setLayoutManager(gridLayoutManager);
                binding.rcvWishlist.setAdapter(new PopularListAdapter(products));
                Toast.makeText(WishlistActivity.this, "Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("get",t.toString());
                Toast.makeText(WishlistActivity.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}