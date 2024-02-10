package com.User.angeldochealthcare.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.adapter.Notification_Adapter;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.response.Notification_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Notification extends Fragment {

    LinearLayout ll_view;
    RecyclerView rv_notification;
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String token,Token;
    List<Notification_Response.Result> notification;
    Notification_Adapter notificationAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View notification = inflater.inflate(R.layout.fragment_notification, container, false);
        progressDialog = new CustomProgressDialog(getContext());
        rv_notification = notification.findViewById(R.id.rv_notification);
        ll_view = notification.findViewById(R.id.ll_view);
        /*shared Pref*/
        loginPref = getContext().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        get_Notification();
        return notification;
    }

    private void get_Notification() {
        progressDialog.showProgressDialog();
        Token = "Bearer " + token;
        Call<Notification_Response> banner_apiCall = ApiService.apiHolders().get_notification(Token);
        banner_apiCall.enqueue(new Callback<Notification_Response>() {
            @Override
            public void onResponse(Call<Notification_Response> call, Response<Notification_Response> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    assert response.body() != null;
                    int total = response.body().getTotal();
                    if (total == 0) {
                        //iv_noti.setVisibility(View.VISIBLE);
                        ll_view.setVisibility(View.VISIBLE);
                    }
                    notification = response.body().getResult();
                    notificationAdapter = new Notification_Adapter(getContext(), notification);
                    rv_notification.setAdapter(notificationAdapter);
                    rv_notification.setLayoutManager(new LinearLayoutManager(getContext()));

                } else {
                    progressDialog.dismiss();
                    //iv_noti.setVisibility(View.VISIBLE);
                    ll_view.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Notification_Response> call, Throwable t) {
                progressDialog.dismiss();
                //iv_noti.setVisibility(View.VISIBLE);
                ll_view.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}