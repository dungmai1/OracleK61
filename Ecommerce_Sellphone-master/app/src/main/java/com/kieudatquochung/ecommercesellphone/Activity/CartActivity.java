package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.kieudatquochung.ecommercesellphone.Adapter.CartListAdapter;
import com.kieudatquochung.ecommercesellphone.ApiService.ApiService;
import com.kieudatquochung.ecommercesellphone.Models.APIResponse;
import com.kieudatquochung.ecommercesellphone.Models.Cart;
import com.kieudatquochung.ecommercesellphone.Models.District;
import com.kieudatquochung.ecommercesellphone.Models.DistrictResponse;
import com.kieudatquochung.ecommercesellphone.Models.Product;
import com.kieudatquochung.ecommercesellphone.Models.Province;
import com.kieudatquochung.ecommercesellphone.Models.ProvinceResponse;
import com.kieudatquochung.ecommercesellphone.Models.Ward;
import com.kieudatquochung.ecommercesellphone.Models.WardResponse;
import com.kieudatquochung.ecommercesellphone.Models.cartItemDTOList;
import com.kieudatquochung.ecommercesellphone.R;
import com.kieudatquochung.ecommercesellphone.databinding.ActivityCartBinding;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity{
    ActivityCartBinding binding;
    public List<cartItemDTOList> cartItemDTOListList;
    public List<Province> provinceList;
    public List<District> districtList;
    public List<Ward> wardList;


    Cart cartResponseList;
    private int total=0;
    AlertDialog diaglog;
    Spinner spinnerProvince;
    Spinner spinnerVillage;
    Spinner spinnerDistrict;
    String selectedProvinceId;
    String selectedDistrictId;
    CartListAdapter cartListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_cart);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getCart();
        EventClickPayment();
        EventClickAddress();
        setTxt();
        EventClickPaymentMethod();
    }

    private void setTxt() {
        binding.txtAddress.setText("No Address");
    }
    private void EventClickPayment() {
        binding.btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartItemDTOListList.size()==0){
                    Toast.makeText(CartActivity.this, "Giỏ hàng trống", Toast.LENGTH_LONG).show();
                }else{
                    createOrder();
                }
            }
        });
    }
    private void getCart() {
        ApiService.apiservice.getCart(SignInActivity.token).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    cartResponseList = response.body();
                    if (cartResponseList != null) {
                        cartItemDTOListList = cartResponseList.getCartItemDTOListList();
                        binding.recycleCart.setAdapter(new CartListAdapter(cartItemDTOListList,binding.txtTotal));
                        DecimalFormat decimalFormat = new DecimalFormat("#,###");
                        String PriceFormat = decimalFormat.format(cartResponseList.getTotalCost()) + "VNĐ";
                        total = cartResponseList.getTotalCost();
                        binding.txtTotal.setText(PriceFormat);
                        binding.recycleCart.setLayoutManager(new LinearLayoutManager(CartActivity.this, LinearLayoutManager.VERTICAL, false));
                    } else {
                        Toast.makeText(CartActivity.this, "Empty cart", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(CartActivity.this, "Failed to update cart", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Failed to update cart", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void createOrder() {
        String payment_method = binding.txtPaymentMethod.getText().toString();
        String address = binding.txtAddress.getText().toString();
        Cart cart = new Cart(cartItemDTOListList, total);
        ApiService.apiservice.createOrder(SignInActivity.token, cart,payment_method,address).enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.isSuccessful()) {
                    APIResponse apiResponse = response.body();
                    if (apiResponse != null) {
                        Toast.makeText(CartActivity.this, apiResponse.getMessage(), Toast.LENGTH_LONG).show();
                        finish();
                    } else {
                        Toast.makeText(CartActivity.this, "Response body is null", Toast.LENGTH_LONG).show();
                    }
                } else {
                    int responseCode = response.code();
                    Toast.makeText(CartActivity.this, "HTTP Response Code: " + responseCode, Toast.LENGTH_LONG).show();
                }
                Toast.makeText(CartActivity.this, "Success", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void OpenDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add address");
        View view = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        EditText edtName;
        edtName = view.findViewById(R.id.edt_Address);
        Button submit = view.findViewById(R.id.btnSubmit);
        spinnerProvince = view.findViewById(R.id.spinner_Province);
        spinnerDistrict = view.findViewById(R.id.spinner_District);
        spinnerVillage = view.findViewById(R.id.spinner_Village);
        loadProvince();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedProvince = spinnerProvince.getSelectedItem().toString();
                String selectedDistrict = spinnerDistrict.getSelectedItem().toString();
                String selectedVillage = spinnerVillage.getSelectedItem().toString();
                String editTextAddress = edtName.getText().toString();
                if(editTextAddress.isEmpty()){
                    Toast.makeText(CartActivity.this, "Địa chỉ không được trống", Toast.LENGTH_LONG).show();
                }else{
                    binding.txtAddress.setText(editTextAddress+", "+selectedVillage+", "+ selectedDistrict+", "+ selectedProvince);
                    diaglog.dismiss();
                }
            }
        });
        builder.setView(view);
        diaglog = builder.create();
        diaglog.show();
    }
    private void OpenDialog_Payment() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Payment Method");
        View view = getLayoutInflater().inflate(R.layout.custom_dialog_payment, null);
        Button submit = view.findViewById(R.id.btnSubmit_PaymentMethod);
        RadioGroup radioGroup = view.findViewById(R.id.paymentRadioGroup);
        RadioButton radioButton_PaymentOnDelivery = view.findViewById(R.id.radioButton_PaymentOnDelivery);
        RadioButton radioButton_Momo = view.findViewById(R.id.radioButton_Momo);
        RadioButton radioButton_Banking = view.findViewById(R.id.radioButton_Banking);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String selectedPaymentMethod = "";
                if(radioButton_Momo.isChecked()){
                    selectedPaymentMethod = radioButton_Momo.getText().toString();
                }else if(radioButton_PaymentOnDelivery.isChecked()){
                    selectedPaymentMethod = radioButton_PaymentOnDelivery.getText().toString();
                }else{
                    selectedPaymentMethod = radioButton_Banking.getText().toString();
                }
                binding.txtPaymentMethod.setText(selectedPaymentMethod);
                diaglog.dismiss();
            }
        });
        builder.setView(view);
        diaglog = builder.create();
        diaglog.show();
    }
    private void EventClickAddress() {
        binding.layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialog();
            }
        });
    }
    private void EventClickPaymentMethod(){
        binding.layoutPaymentMethod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDialog_Payment();
            }
        });
    }

    private void loadProvince() {
        ApiService.api_address.loadProvince().enqueue(new Callback<ProvinceResponse>() {
            @Override
            public void onResponse(Call<ProvinceResponse> call, Response<ProvinceResponse> response) {
                if (response.isSuccessful()) {
                    ProvinceResponse provinceResponse = response.body();
                    if (provinceResponse != null) {
                        provinceList = provinceResponse.getResults();
                        List<String> provinceNames = new ArrayList<>();
                        for (Province province : provinceList) {
                            provinceNames.add(province.getProvince_name());
                        }
                        ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_spinner_item, provinceNames);
                        provinceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerProvince.setAdapter(provinceAdapter);
                        EventClickSpinnerProvince();
                    } else {
                        Toast.makeText(CartActivity.this, "Null List", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<ProvinceResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, "Fail", Toast.LENGTH_LONG).show();
            }
        });
    }
    private String EventClickSpinnerProvince(){
        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Province selectedProvince = provinceList.get(position);
                selectedProvinceId = selectedProvince.getProvince_id();
                loadDistrict(selectedProvinceId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if no item is selected
            }
        });
        return selectedProvinceId;
    }
    private void loadDistrict(String selectedProvinceId) {
        ApiService.api_address.loadDistrict(selectedProvinceId).enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                if (response.isSuccessful()) {
                    DistrictResponse districtResponse = response.body();
                    if (districtResponse != null) {
                        districtList = districtResponse.getResults();
                        List<String> districtName = new ArrayList<>();
                        for (District district : districtList) {
                            districtName.add(district.getDistrict_name());
                        }
                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_spinner_item, districtName);
                        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerDistrict.setAdapter(districtAdapter);
                        EventClickSpinnerDistrict();
                    } else {
                        Toast.makeText(CartActivity.this, "Null List", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
            }
        });
    }
    private String EventClickSpinnerDistrict(){
        spinnerDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                District selectedDistrict = districtList.get(position);
                selectedDistrictId = selectedDistrict.getDistrict_id();
                loadWard(selectedDistrictId);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing if no item is selected
            }
        });
        return selectedDistrictId;
    }
    private void loadWard(String selectedDistrictId) {
        ApiService.api_address.loadWard(selectedDistrictId).enqueue(new Callback<WardResponse>() {
            @Override
            public void onResponse(Call<WardResponse> call, Response<WardResponse> response) {
                if (response.isSuccessful()) {
                    WardResponse wardResponse = response.body();
                    if (wardResponse != null) {
                        wardList = wardResponse.getResults();
                        List<String> wardName = new ArrayList<>();
                        for (Ward ward : wardList) {
                            wardName.add(ward.getWard_name());
                        }
                        ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(CartActivity.this, android.R.layout.simple_spinner_item, wardName);
                        districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinnerVillage.setAdapter(districtAdapter);
                        EventClickSpinnerDistrict();
                    } else {
                        Toast.makeText(CartActivity.this, "Null List", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<WardResponse> call, Throwable t) {
            }
        });
    }
}