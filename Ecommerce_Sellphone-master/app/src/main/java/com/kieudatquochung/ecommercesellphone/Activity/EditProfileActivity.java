package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityEditProfileBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    ActivityEditProfileBinding binding;
    private String name,email;
    private int phone,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_edit_profile);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getText_EditProfile();
        EventClickSave();
    }

    private void EventClickSave() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SaveEditProfile();
            }
        });
    }
    private void SaveEditProfile() {
        name = binding.editTextName.getText().toString();
        email = binding.editTextEmail.getText().toString();
        id = MainActivity.customer_id;
        ApiService.apiservice.updateCustomer(SignInActivity.token, id, name, email).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    APIResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        Toast.makeText(EditProfileActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("customer_name", binding.editTextName.getText().toString());
                        resultIntent.putExtra("email", binding.editTextEmail.getText().toString());
                        setResult(RESULT_OK, resultIntent);
                        finish();
                    } else {
                        Toast.makeText(EditProfileActivity.this, "Response body is null", Toast.LENGTH_LONG).show();
                    }
                } else {
                    int responseCode = response.code();
                    Toast.makeText(EditProfileActivity.this, "HTTP Response Code: " + responseCode, Toast.LENGTH_LONG).show();
                }
                Toast.makeText(EditProfileActivity.this, "thanh cong", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
            }
        });
    }

    private void getText_EditProfile() {
        Intent intent = getIntent();
        binding.editTextName.setText(intent.getStringExtra("customer_name"));
        binding.editTextEmail.setText(intent.getStringExtra("email"));
    }
}