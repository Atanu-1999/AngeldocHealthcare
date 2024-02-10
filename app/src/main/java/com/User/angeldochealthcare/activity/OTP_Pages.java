package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.MainActivity;
import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.response.LoginResponse;
import com.User.angeldochealthcare.response.OTP_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;
import com.User.angeldochealthcare.utility.DeviceUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OTP_Pages extends AppCompatActivity {
    TextView btn_Otp_submit,tv_phone,count_time,btn_resend;
    LinearLayout tv_verify;
    EditText otp1,otp2,otp3,otp4,otp5,otp6;
    String otp,phone,deviceToken,deviceId;
    CustomProgressDialog progressDialog;
    private CountDownTimer countDownTimer;
    long timerDuration = 20000;
    long timerInterval = 1000;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_pages);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE );
        initi();
        otp_move();
        CountDown();
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        phone = bundle.getString("LOGIN_ID");
        tv_phone.setText("We have sent an OTP to " + " +91-"  + phone + " " + "Please wait for 2 min Before resend Attempt .");

        btn_Otp_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp = otp1.getText().toString()+otp2.getText().toString()+otp3.getText().toString()+otp4.getText().toString()+otp5.getText().toString()+otp6.getText().toString();
                if (otp1.getText().toString().isEmpty() || otp2.getText().toString().isEmpty() || otp3.getText().toString().isEmpty() ||
                        otp4.getText().toString().isEmpty() || otp5.getText().toString().isEmpty() || otp6.getText().toString().isEmpty()){
                    Toast.makeText(OTP_Pages.this, "Please Enter OTP", Toast.LENGTH_SHORT).show();
                }
                else {
                    callVerifyOTP(phone,otp);
                }
            }
        });
        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                otp1.getText().clear();
                otp2.getText().clear();
                otp3.getText().clear();
                otp4.getText().clear();
                otp5.getText().clear();
                otp6.getText().clear();
                otp1.requestFocus();
                Resend_Otp();
                CountDown();
                count_time.setVisibility(View.VISIBLE);
                btn_resend.setVisibility(View.GONE);
            }
        });
    }

    private void Resend_Otp() {
        progressDialog.showProgressDialog();
        Call<LoginResponse> login_apiCall = ApiService.apiHolders().login(phone,"true","true", deviceId);
        login_apiCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(OTP_Pages.this, "OTP Resend Successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.hideProgressDialog();
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

    private void CountDown() {
        countDownTimer = new CountDownTimer(timerDuration, timerInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timerTextView with the remaining time
                count_time.setText( millisUntilFinished / 1000 + " Sec");
                btn_resend.setVisibility(View.GONE);
                tv_verify.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFinish() {
                // Timer finished, you can perform actions here
                btn_resend.setVisibility(View.VISIBLE);
                count_time.setVisibility(View.GONE);
                tv_verify.setVisibility(View.GONE);
            }
        };
        // Start the timer
        countDownTimer.start();
    }
    private void otp_move() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp1.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                otp1.setTextColor(getResources().getColor(R.color.white));
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (otp1.getText().toString().length() == 1) {
                    otp1.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                } else {
                    otp1.requestFocus();
                    otp1.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                    //otp1.setTextColor(getResources().getColor(R.color.white));
                }
            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp2.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                otp2.setTextColor(getResources().getColor(R.color.white));

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (otp2.getText().toString().length() == 1) {
                    otp2.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                } else {
                    otp2.clearFocus();
                    otp1.requestFocus();
                    otp1.setCursorVisible(true);
                    otp2.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp3.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                otp3.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (otp3.getText().toString().length() == 1) {
                    otp3.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);
                } else {
                    otp3.clearFocus();
                    otp2.requestFocus();
                    otp2.setCursorVisible(true);
                    otp3.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp4.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                otp4.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (otp4.getText().toString().length() == 1) {
                    otp4.clearFocus();
                    otp5.requestFocus();
                    otp5.setCursorVisible(true);
                } else {
                    otp4.clearFocus();
                    otp3.requestFocus();
                    otp3.setCursorVisible(true);
                    otp4.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp5.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                otp5.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (otp5.getText().toString().length() == 1) {
                    otp5.clearFocus();
                    otp6.requestFocus();
                    otp6.setCursorVisible(true);
                } else {
                    otp5.clearFocus();
                    otp4.requestFocus();
                    otp4.setCursorVisible(true);
                    otp5.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
        otp6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                otp6.setBackgroundDrawable(getResources().getDrawable(R.drawable.otp_bg));
                otp6.setTextColor(getResources().getColor(R.color.white));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (otp6.getText().toString().length() == 1) {
                    otp6.clearFocus();
                    // otp5.setCursorVisible(true);
                } else {
                    otp6.clearFocus();
                    otp5.requestFocus();
                    otp5.setCursorVisible(true);
                    otp6.setBackgroundDrawable(getResources().getDrawable(R.drawable.time_bg));
                }
            }
        });
    }
    private void callVerifyOTP(String phone, String otp) {
        progressDialog.showProgressDialog();
        Call<OTP_Response> verify_apiCall = ApiService.apiHolders().OTP_verify(phone,otp);
        verify_apiCall.enqueue(new Callback<OTP_Response>() {
            @Override
            public void onResponse(Call<OTP_Response> call, Response<OTP_Response> response) {
                if (response.code() == 406){
                    Toast.makeText(OTP_Pages.this, "OTP Mismatch", Toast.LENGTH_SHORT).show();
                    progressDialog.hideProgressDialog();
                }
                else {
                    if(response.code() == 201) {
                        assert response.body() != null;
                        deviceToken = response.body().getToken();
                        editor.putString("TOKEN", deviceToken);
                        editor.commit();
                        Toast.makeText(OTP_Pages.this, "OTP Verify Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(OTP_Pages.this,GetStarted_Page.class));
                        progressDialog.hideProgressDialog();
                    }
                    else {
                        Toast.makeText(OTP_Pages.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                        progressDialog.hideProgressDialog();
                    }
                }
            }
            @Override
            public void onFailure(Call<OTP_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void initi() {
        progressDialog = new CustomProgressDialog(this);
        btn_Otp_submit = findViewById(R.id.btn_Otp_submit);
        otp1 = findViewById(R.id.otp1);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);
        otp5 = findViewById(R.id.otp5);
        otp6 = findViewById(R.id.otp6);
        tv_phone = findViewById(R.id.tv_phone);
        btn_resend = findViewById(R.id.btn_resend);
        count_time = findViewById(R.id.count_time);
        tv_verify = findViewById(R.id.tv_verify);
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        deviceId = DeviceUtils.getDeviceId(getApplicationContext());
        Log.d("d_ID", deviceId);
    }
}