package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.response.LoginResponse;
import com.User.angeldochealthcare.utility.CustomProgressDialog;
import com.User.angeldochealthcare.utility.DeviceUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginPage extends AppCompatActivity {
    TextView btn_login;
    EditText et_phone;
    String regex = "^[6-9][0-9]{9}$",deviceId;
    CustomProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );

        initia();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = et_phone.getText().toString();
                if (et_phone.getText().toString().length() == 0){
                    Toast.makeText(LoginPage.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    callApi(phone);
                }
            }
        });
    }

    private void callApi(String phone) {
        progressDialog.showProgressDialog();
        Call<LoginResponse> login_apiCall = ApiService.apiHolders().login(phone,"true","true", deviceId);
        login_apiCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.hideProgressDialog();
                    Bundle bundle = new Bundle();
                    bundle.putString("LOGIN_ID", phone);
                    Intent i = new Intent(LoginPage.this, OTP_Pages.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
                else {
                    progressDialog.hideProgressDialog();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }

    private void initia() {
        progressDialog = new CustomProgressDialog(this);
        btn_login = findViewById(R.id.btn_login);
        et_phone = findViewById(R.id.et_phone);
        deviceId = DeviceUtils.getDeviceId(getApplicationContext());
        Log.d("d_ID", deviceId);
    }
}