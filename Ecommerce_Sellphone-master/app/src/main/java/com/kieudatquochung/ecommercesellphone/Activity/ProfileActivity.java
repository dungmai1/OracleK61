package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.Models.Customer;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    ActivityProfileBinding binding;
    String customer_name,email;
    private static final int REQUEST_CODE = 1; // Define your request code here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setProfile();
        ButtonClick();
        EventClickOrderHistory();
    }

    private void EventClickOrderHistory() {
        binding.btnOrderHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }
    private void ButtonClick() {
        binding.btnPersonalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("customer_name",customer_name);
                intent.putExtra("email",email);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        binding.btnWishlist.setOnClickListener(v -> startActivity(new Intent(ProfileActivity.this, WishlistActivity.class)));
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, SignInActivity.class);
                startActivity(intent);
                finishAffinity();
            }
        });
    }
    private void setProfile() {
        ApiService.apiservice.getSingleCustomer(MainActivity.customer_id).enqueue(new Callback<Customer>() {
            @Override
            public void onResponse(Call<Customer> call, Response<Customer> response) {
                Customer customer = response.body();
                customer_name = customer.getName();
                email = customer.getEmail();
                binding.txtCustomerName.setText(customer_name);
                binding.txtEmail.setText(email);
            }
            @Override
            public void onFailure(Call<Customer> call, Throwable t) {

            }
        });
    }
    // Xử lý kết quả từ hoạt động chỉnh sửa
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                binding.txtCustomerName.setText(data.getStringExtra("customer_name"));
                binding.txtEmail.setText(data.getStringExtra("email"));
            }
        }
    }
}