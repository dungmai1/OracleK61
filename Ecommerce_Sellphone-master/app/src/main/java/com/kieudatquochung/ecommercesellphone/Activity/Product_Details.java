package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.CartRequest;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityProductDetailsBinding;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Product_Details extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    private Product object;
    private boolean checkWishlist = false;
    private boolean checkCart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_product_details);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundle();
        EventClickBookMark();
        EventClickAddToCart();
    }

    private void EventClickAddToCart() {
        binding.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddToCart();
            }
        });
    }
    private void AddToCart(){
        if(checkCart()==true){
            updateQuantityIncrease(object.getId());
        }else{
            CartRequest cartRequest = new CartRequest(object.getId(),1);
            ApiService.apiservice.addToCart(SignInActivity.token,cartRequest).enqueue(new Callback<APIResponse>() {
                @Override
                public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                    Toast.makeText(Product_Details.this, "Added to cart success", Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFailure(Call<APIResponse> call, Throwable t) {
                    Toast.makeText(Product_Details.this, "Added to cart fail", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
    private void updateQuantityIncrease(int product_id){
        ApiService.apiservice.updateQuantityIncrease(SignInActivity.token,product_id).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
            }
            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
            }
        });
    }
    private void checkIfProductInWishlist(String token,int productId) {
        ApiService.apiservice.checkWishlist(token,productId).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    checkWishlist = response.body();
                    if(checkWishlist) {
                        binding.imgBookmark.setImageResource(R.drawable.baseline_bookmark_24);
                    }else {
                        binding.imgBookmark.setImageResource(R.drawable.bookmark);
                    }
                } else {
                    Toast.makeText(Product_Details.this, "Loi", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(Product_Details.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
        private void EventClickBookMark() {
        binding.imgBookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable currentImage = binding.imgBookmark.getDrawable();
                if (currentImage.getConstantState().equals(getResources().getDrawable(R.drawable.baseline_bookmark_24).getConstantState())) {
                    binding.imgBookmark.setImageResource(R.drawable.bookmark);
                    ApiService.apiservice.deleteWishlist(SignInActivity.token, object.getId()).enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                            Toast.makeText(Product_Details.this, "Xoa thanh cong", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            Toast.makeText(Product_Details.this, "Fail", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    binding.imgBookmark.setImageResource(R.drawable.baseline_bookmark_24);
                    ApiService.apiservice.addWishlist(object,SignInActivity.token).enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
//                            if(response.isSuccessful()){
//                                WishlistResponse wishlistResponse = response.body();
//                                Toast.makeText(Product_Details.this, wishlistResponse.getMessage(), Toast.LENGTH_LONG).show();
//                            }
                            Toast.makeText(Product_Details.this, "Đã thêm vào yêu thích", Toast.LENGTH_LONG).show();
                        }
                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            Toast.makeText(Product_Details.this, "Fail", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
    private Boolean checkCart(){
        ApiService.apiservice.checkCart(SignInActivity.token, object.getId()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                checkCart = response.body();
            }
            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
            }
        });
        return checkCart;
    }
    private void getBundle() {
        object = (Product) getIntent().getSerializableExtra("object");
        checkIfProductInWishlist( SignInActivity.token,object.getId());
        Glide.with(this)
                .load(object.getPictures())
                .into(binding.itemPic);
        binding.txtTitle1.setText(object.getProduct_name());
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        String PriceFormat = decimalFormat.format(object.getPrice());
        binding.txtPrice1.setText(PriceFormat+ " VND");
        binding.txtDescription.setText(object.getProduct_describe());
    }
}