package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.Adapter.PopularAdapter;
import com.kieudatquochung.ecommercesellphone.Adapter.PopularListAdapter;
import com.kieudatquochung.ecommercesellphone.Adapter.SearchAdapter;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.PopularProductResponse;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    List<Product> products;

    public static String name,email;
    public static int customer_id,phone;
    private View emptyView; // Thêm view cho thông báo rỗng
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //initRecyclerView();
        bottom_navigation();
        setCustomerName();
        getProduct();
        EventClickSmartPhone();
        EventClickSIM();
        EventClickAll();
        searchProductByName();
        EventClickAccessory();
        EventClickOldSmartPhone();
    }

    private void EventClickOldSmartPhone() {
        binding.btnAccessory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCategory_ID(4);
            }
        });
    }

    private void EventClickAccessory() {
        binding.btnOldSmartPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCategory_ID(3);
            }
        });
    }


    private void setCustomerName() {
        Intent intent = getIntent();
        name = intent.getStringExtra("customer_name");
        email = intent.getStringExtra("email");
        customer_id = intent.getIntExtra("customer_id",0);
        phone= intent.getIntExtra("phone",0);
        binding.txtTen.setText(name);
    }
    private void searchProductByName() {
        binding.IDSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //searchProduct(query);
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchProduct(newText);
                return false;
            }
        });
    }
    private void searchProduct(String product_name){
        ApiService.apiservice.searchProductByName(product_name).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {
                    products = response.body();
                    if (product_name.isEmpty()) {
                        binding.rcvSearchProduct.setVisibility(View.GONE); // Ẩn RecyclerView nếu không có kết quả
                    } else {
                        binding.rcvSearchProduct.setVisibility(View.VISIBLE); // Hiển thị RecyclerView nếu có kết quả
                    }
                    binding.rcvSearchProduct.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
                    binding.rcvSearchProduct.setAdapter(new SearchAdapter(products));
                } else {
                    Toast.makeText(MainActivity.this, "Fail", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail11", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void EventClickAll() {
        binding.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Product_Category.class);
                startActivity(intent);          }
        });
    }
    private void EventClickSIM() {
        binding.btnSIM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCategory_ID(5);
            }
        });
    }
    private void EventClickSmartPhone() {
        binding.btnSmartPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCategory_ID(2);
            }
        });
    }
    private void setCategory_ID(int category_id){
        Intent intent = new Intent(MainActivity.this, Product_Category.class);
        intent.putExtra("category_id", category_id);
        startActivity(intent);
    }
    private void getProduct() {
        ApiService.apiservice.getPopularProduct().enqueue(new Callback<List<PopularProductResponse>>() {
            @Override
            public void onResponse(Call<List<PopularProductResponse>> call, Response<List<PopularProductResponse>> response) {
                List<PopularProductResponse> popularProducts = response.body();
                binding.recycleView1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));
                binding.recycleView1.setAdapter(new PopularAdapter(popularProducts));
            }

            @Override
            public void onFailure(Call<List<PopularProductResponse>> call, Throwable t) {

            }
        });
    }
    private void bottom_navigation() {
        binding.homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));
        binding.cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
        binding.btnWishlist.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, WishlistActivity.class)));
        binding.btnProfile.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));
    }
}