package com.kieudatquochung.ecommercesellphone.ApiService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kieudatquochung.ecommercesellphone.Models.CartRequest;
import com.kieudatquochung.ecommercesellphone.Models.Cart;
import com.kieudatquochung.ecommercesellphone.Models.Customer;
import com.kieudatquochung.ecommercesellphone.Models.DistrictResponse;
import com.kieudatquochung.ecommercesellphone.Models.OrderDetailsResponse;
import com.kieudatquochung.ecommercesellphone.Models.OrderResponse;
import com.kieudatquochung.ecommercesellphone.Models.PopularProductResponse;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.Models.ProvinceResponse;
import com.kieudatquochung.ecommercesellphone.Models.SignInRequest;
import com.kieudatquochung.ecommercesellphone.Models.SignInResponse;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.Models.WardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    final GsonBuilder gsonBuilder = new GsonBuilder();
    final Gson gson = gsonBuilder.create();
    ApiService apiservice = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    ApiService api_address = new Retrofit.Builder()
            .baseUrl("https://vapi.vnappmob.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @GET("product/")
    Call<List<Product>> getProduct();
    @GET("/product/{category_id}")
    Call<List<Product>> getProductByCategory(@Path("category_id") int category_id);
    @GET("/product/search")
    Call<List<Product>> searchProductByName(@Query("product_name")String product_name);
    @POST("/customer/signin")
    Call<SignInResponse> SignIn(@Body SignInRequest signInRequest);
    @POST("/wishlist/add")
    Call<APIResponse> addWishlist(@Body Product product,
                                  @Query("token") String token);
    @GET("wishlist/{token}")
    Call<List<Product>> getWishlist(@Path("token") String token);
    @DELETE("wishlist/delete/{token}")
    Call<APIResponse> deleteWishlist(@Path("token")String token,
                                     @Query("product_id")int product_id);
    @GET("/wishlist/check/{token}")
    Call<Boolean> checkWishlist(@Path("token")String token,
                                @Query("product_id")int product_id);
    @POST("/cart/add")
    Call<APIResponse> addToCart(@Query("token")String token,
                                @Body CartRequest cartRequest);
    @GET("cart/")
    Call<Cart> getCart(@Query("token")String token);
    @DELETE("/cart/delete/{cartItemId}")
    Call<APIResponse> deleteCart(@Path("cartItemId")int cartItemId,
                                 @Query("token")String token);
    @GET("/cart/check/{token}")
    Call<Boolean> checkCart(@Path("token")String token,
                            @Query("product_id")int product_id);
    @POST("/cart/update/decrease/{token}")
    Call<APIResponse> updateQuantityDecrease(@Path("token")String token,
                                             @Query("product_id")int product_id);
    @POST("/cart/update/increase/{token}")
    Call<APIResponse> updateQuantityIncrease(@Path("token")String token,
                                             @Query("product_id")int product_id);
    @POST("customer/update/{token}")
    Call<APIResponse> updateCustomer(@Path("token")String token,
                                     @Query("customer_id")int customer_id,
                                     @Query("name")String name,
                                     @Query("email")String email);
    @POST("/order/create/{token}")
    Call<APIResponse> createOrder(@Path("token")String token,
                                  @Body Cart cart,
                                  @Query("payment_method")String payment_method,
                                  @Query("address")String address);
    @POST("/customer/signup")
    Call<APIResponse> SignUp(@Body Customer customer);
    @GET("api/province")
    Call<ProvinceResponse> loadProvince();
    @GET("api/province/district/{province_id}")
    Call<DistrictResponse> loadDistrict(@Path("province_id") String province_id);
    @GET("api/province/ward/{district_id}")
    Call<WardResponse> loadWard(@Path("district_id") String district_id);
    @GET("/customer/getSingleCustomer")
    Call<Customer> getSingleCustomer(@Query("customer_id")int customer_id);
    @GET("/order/{token}")
    Call <List<OrderResponse>> getAllOrder(@Path("token")String token);
    @GET("/orderdetails/{token}")
    Call<List<OrderDetailsResponse>> getAllOrderDetails(
            @Path("token") String token,
            @Query("order_id") int order_id
    );
    @GET("/popular/")
    Call<List<PopularProductResponse>> getPopularProduct();
}
