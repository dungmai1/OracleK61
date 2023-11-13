package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.SignInRequest;
import com.kieudatquochung.ecommercesellphone.Models.SignInResponse;
import com.kieudatquochung.ecommercesellphone.databinding.ActivitySignInBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {
    ActivitySignInBinding binding;
    private String password;
    private int phone;
    public static String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_sign_in);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        EventClickLogin();
        EventClickSignUp();
    }
    private void EventClickSignUp() {
        binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    private void EventClickLogin() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneStr = binding.editTextPhone.getText().toString();
                phone = 0;
                if (!phoneStr.isEmpty()) {
                    try {
                        phone = Integer.parseInt(phoneStr);
                    } catch (NumberFormatException e) {
                    }
                }
                password = binding.editTextPassword.getText().toString();
                if (password.isEmpty() || phone == 0) {
                    Toast.makeText(SignInActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                } else {
                    SignIn(phone, password);
                }
            }
        });
    }
    private void SignIn(int phone, String password) {
        SignInRequest signInRequest = new SignInRequest(phone,password);
        ApiService.apiservice.SignIn(signInRequest).enqueue(new Callback<SignInResponse>() {
            @Override
            public void onResponse(Call<SignInResponse> call, Response<SignInResponse> response) {
                if(response.isSuccessful()){
                    SignInResponse signInResponse = response.body();
                    token = signInResponse.getToken().toString();
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    intent.putExtra("customer_name",signInResponse.getCustomer().getName());
                    intent.putExtra("email",signInResponse.getCustomer().getEmail());
                    intent.putExtra("phone",signInResponse.getCustomer().getPhone());
                    intent.putExtra("customer_id",signInResponse.getCustomer().getId());
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SignInActivity.this, "SĐT hoặc mật khẩu không hợp lệ", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<SignInResponse> call, Throwable t) {
                Toast.makeText(SignInActivity.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
}