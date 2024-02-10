package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.razorpay.Checkout;

import org.json.JSONObject;

public class Booking_Preview extends AppCompatActivity {
    TextView btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_preview);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
    }
    private void inita() {
        btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int amount = 100;
                payment(amount);
            }
        });
    }

    private void payment(int amount) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_live_GbnM6KdaZa4TXm");
        try{
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name","Atanu Sahoo");
            jsonObject.put("description","ONLINE PAYMENT");
            jsonObject.put("prefill.email", "atanu1999@gmail.com");
            jsonObject.put("prefill.contact","9330603949");
            jsonObject.put("image","R.drawable.user");
            jsonObject.put("color","#3399cc");
            jsonObject.put("currency","INR");
            jsonObject.put("amount", amount*100);
            jsonObject.put("orderId","787576465");

            JSONObject retryObj = new JSONObject();
            retryObj.put("enabled",true);
            retryObj.put("max_count", 1);

            jsonObject.put("retry", retryObj);
            checkout.open(Booking_Preview.this, jsonObject);
        } catch(Exception e) {
            Toast.makeText(this, "Something went Wrong", Toast.LENGTH_SHORT).show();
        }
    }
}