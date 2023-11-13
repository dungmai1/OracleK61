package com.kieudatquochung.ecommercesellphone.Activity;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.Adapter.PopularListAdapter;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityProductCategoryBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Category extends AppCompatActivity {

    ActivityProductCategoryBinding binding;
    List<Product> products;
    private int category_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);
        binding = ActivityProductCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getData();
        getProductByCategory(category_id);
        TxtActionBar();
        EventClickBack();
    }

    private void EventClickBack() {
        binding.include.btnBack.setOnClickListener(v -> finish());
    }
    private void getData() {
        Intent intent = getIntent();
        category_id = intent.getIntExtra("category_id",0);
    }
    private void getProductByCategory(int category_id) {
        if(category_id == 0){
            getProduct();
        }
        else{
            ApiService.apiservice.getProductByCategory(category_id).enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                    products = response.body();
                    binding.rcvCategory.setHasFixedSize(true);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(Product_Category.this, 2);
                    binding.rcvCategory.setLayoutManager(gridLayoutManager);
                    binding.rcvCategory.setAdapter(new PopularListAdapter(products));
                }
                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {
                    Log.d("get",t.toString());
                    Toast.makeText(Product_Category.this, "Fail", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private void TxtActionBar(){
        switch (category_id){
            case 2:
                binding.include.txtActionBar.setText("Danh sách điện thoại");
                break;
            case 5:
                binding.include.txtActionBar.setText("Danh sách SIM");
                break;
            case 4:
                binding.include.txtActionBar.setText("Phụ Kiện");
                break;
            case 3:
                binding.include.txtActionBar.setText("Điện thoại cũ");
                break;
            default:
                break;
        }
    }
    private void getProduct() {
        binding.include.txtActionBar.setText("Tất cả sản phẩm");
        ApiService.apiservice.getProduct().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                products = response.body();
                binding.rcvCategory.setHasFixedSize(true);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(Product_Category.this, 2);
                binding.rcvCategory.setLayoutManager(gridLayoutManager);
                binding.rcvCategory.setAdapter(new PopularListAdapter(products));
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.d("get",t.toString());
                Toast.makeText(Product_Category.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}