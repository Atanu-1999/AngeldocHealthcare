package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.response.Get_Profile_Response;
import com.User.angeldochealthcare.response.Pages_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pages extends AppCompatActivity {

    TextView pages_name,page_desc;
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String Token,token,pageId;
    ImageView back;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pages);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        pages_name = findViewById(R.id.pages_name);
        page_desc = findViewById(R.id.page_desc);
        progressDialog = new CustomProgressDialog(this);
        back = findViewById(R.id.back);

        /*Function*/
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        assert bundle != null;
        pageId = bundle.getString("PAGE_ID");
        pages_Api(pageId);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void pages_Api(String pageId) {
        progressDialog.showProgressDialog();
        Token = "Bearer " + token;
        Call<Pages_Response> get_page = ApiService.apiHolders().getPage(pageId);
        get_page.enqueue(new Callback<Pages_Response>() {
            @Override
            public void onResponse(Call<Pages_Response> call, Response<Pages_Response> response) {
                if (response.isSuccessful()){
                    progressDialog.hideProgressDialog();
                    pages_name.setText(response.body().getTitle());
                    page_desc.setText(response.body().getDesc());
                }
                else {
                    progressDialog.hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<Pages_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
}