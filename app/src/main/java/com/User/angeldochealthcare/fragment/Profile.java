package com.User.angeldochealthcare.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.activity.LoginPage;
import com.User.angeldochealthcare.activity.OTP_Pages;
import com.User.angeldochealthcare.activity.Pages;
import com.User.angeldochealthcare.activity.Profile_Update;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.response.Get_Profile_Response;
import com.User.angeldochealthcare.response.Profile_Insert_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends Fragment {
    TextView btn_edite,txt_profile;
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String Token,token;
    LinearLayout btn_privacy,btn_about_us,btn_terms,btn_share,ll_logOut;
    private Dialog noInternetDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View profile = inflater.inflate(R.layout.fragment_profile, container, false);
        txt_profile = profile.findViewById(R.id.txt_profile);
        btn_edite = profile.findViewById(R.id.btn_edite);
        btn_privacy = profile.findViewById(R.id.btn_privacy);
        btn_about_us = profile.findViewById(R.id.btn_about_us);
        btn_terms = profile.findViewById(R.id.btn_terms);
        btn_share = profile.findViewById(R.id.btn_share);
        ll_logOut = profile.findViewById(R.id.ll_logOut);
        progressDialog = new CustomProgressDialog(getContext());

        /*Function*/
        loginPref = getContext().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        GET_profile();

        btn_edite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Profile_Update.class));
            }
        });
        btn_privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("PAGE_ID", "2");
                Intent i = new Intent(getContext(), Pages.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btn_about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("PAGE_ID", "1");
                Intent i = new Intent(getContext(), Pages.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btn_terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("PAGE_ID", "4");
                Intent i = new Intent(getContext(), Pages.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Download this app." + " "+"https://play.google.com/store/apps/details?id=com.wapss.digo360&pcampaignid=web_share"+" " +
                            "Join Us Now"  );
                    startActivity(Intent.createChooser(shareIntent, "Refer and Share"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ll_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noInternetDialog = new Dialog(getContext());
                noInternetDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                noInternetDialog.setContentView(R.layout.logout_layout);
                noInternetDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                noInternetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                noInternetDialog.show();
                TextView et_yes = (TextView)noInternetDialog.findViewById(R.id.et_yes);
                TextView et_cancel = (TextView)noInternetDialog.findViewById(R.id.et_cancel);
                et_yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences preferences = getContext().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        Intent intent = new Intent(getContext(), LoginPage.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                });
                et_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        noInternetDialog.dismiss();
                    }
                });
                noInternetDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
                noInternetDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });
        return profile;
    }
    private void GET_profile() {
        progressDialog.showProgressDialog();
        Token = "Bearer " + token;
        Call<Get_Profile_Response> get_apiCall = ApiService.apiHolders().get_profile(Token);
        get_apiCall.enqueue(new Callback<Get_Profile_Response>() {
            @Override
            public void onResponse(Call<Get_Profile_Response> call, Response<Get_Profile_Response> response) {
                if (response.code() == 404){
                    progressDialog.hideProgressDialog();
                    txt_profile.setText("Hi.User");
                }
                else {
                    if (response.isSuccessful()){
                        progressDialog.hideProgressDialog();
                        txt_profile.setText("Hi." + response.body().getNickName());
                    }
                    else {
                        progressDialog.hideProgressDialog();
                    }
                }
            }
            @Override
            public void onFailure(Call<Get_Profile_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
}