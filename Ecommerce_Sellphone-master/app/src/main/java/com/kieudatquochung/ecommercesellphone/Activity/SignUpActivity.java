package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.Models.Customer;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivitySignUpBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    public String email,customer_name,password;
    public int phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_up);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EventClickLogin();
        EventClickSignUP();
    }

    private void EventClickSignUP() {
        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUp();
            }
        });
    }

    private void EventClickLogin() {
        binding.btnLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void SignUp(){
        String phoneStr = binding.signupPhone.getText().toString();
        phone = 0;
        if (!phoneStr.isEmpty()) {
            try {
                phone = Integer.parseInt(phoneStr);
            } catch (NumberFormatException e) {
            }
        }
        email = binding.signupEmail.getText().toString();
        customer_name = binding.signupName.getText().toString();
        password = binding.signupPassword.getText().toString();
        if(phone==0||email.isEmpty()||email.isEmpty()||customer_name.isEmpty()){
            Toast.makeText(SignUpActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
        }else{
            Customer customer = new Customer(phone,customer_name,email,password);
            ApiService.apiservice.SignUp(customer).enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    APIResponse apiResponse = response.body();
                    if(response.isSuccessful()){
                        Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(SignUpActivity.this, "SĐT đã được đăng kí", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Fail", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}