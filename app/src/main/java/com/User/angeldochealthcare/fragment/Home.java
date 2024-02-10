package com.User.angeldochealthcare.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.MainActivity;
import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.activity.Booking_Step_One;
import com.User.angeldochealthcare.activity.Doctor_All_Page;
import com.User.angeldochealthcare.activity.Pages;
import com.User.angeldochealthcare.activity.Specialist_Doctor_List;
import com.User.angeldochealthcare.activity.Specialization_List;
import com.User.angeldochealthcare.activity.Verifyed_Doc_List;
import com.User.angeldochealthcare.adapter.Banner_Adapter;
import com.User.angeldochealthcare.adapter.Home_Spec_Adapter;
import com.User.angeldochealthcare.adapter.Specialization_Adapter;
import com.User.angeldochealthcare.adapter.Verify_Doc_Adapter;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.interfacs.ListSpecListener;
import com.User.angeldochealthcare.interfacs.Verify_Doc_Listner;
import com.User.angeldochealthcare.response.Banner_Response;
import com.User.angeldochealthcare.response.Get_Profile_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends Fragment {

    TextView tv_viewAll,tv_spec_viewAll;
    LinearLayout btn_spec_doctor,heart_suregen;
    ViewPager view_pager;
    CustomProgressDialog progressDialog;
    List<Banner_Response.Result> GetBanner;
    private int currentPage = 0;
    private final long DELAY_MS = 5000; // Delay in milliseconds before flipping to the next page
    private final long PERIOD_MS = 5000; // Time period between each auto-flipping
    RecyclerView rv_spec,rv_verify_doc;
    List<Specialization_Response.Result> specResponse;
    Home_Spec_Adapter specializationAdapter;
    List<Verifyed_Doc_Response.Result> verifyResponse;
    Verify_Doc_Adapter verifyDocAdapter;
    String token,TOKEN;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View home = inflater.inflate(R.layout.fragment_home, container, false);
        progressDialog = new CustomProgressDialog(getContext());
        rv_spec = home.findViewById(R.id.rv_spec);
        rv_verify_doc = home.findViewById(R.id.rv_verify_doc);
        tv_spec_viewAll = home.findViewById(R.id.tv_spec_viewAll);
        view_pager = home.findViewById(R.id.view_pager);
        tv_viewAll = home.findViewById(R.id.tv_viewAll);
        loginPref = getContext().getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        tv_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Verifyed_Doc_List.class));
            }
        });
        tv_spec_viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Specialization_List.class));
            }
        });
        getBanner();
        getSpecialization();
        get_verify_doc();
        return home;
    }
    private void get_verify_doc() {
        progressDialog.showProgressDialog();
        TOKEN = "Bearer " + token;
        int limit = 3;
        int offset = 0;
        String keyword = "";
        boolean verify = true;
        Call<Verifyed_Doc_Response> get_spec = ApiService.apiHolders().Get_verify_doc(TOKEN,limit,offset,keyword,verify);
        get_spec.enqueue(new Callback<Verifyed_Doc_Response>() {
            @Override
            public void onResponse(Call<Verifyed_Doc_Response> call, Response<Verifyed_Doc_Response> response) {
                if (response.isSuccessful()) {
                    progressDialog.hideProgressDialog();
                    assert response.body() != null;
                    verifyResponse = response.body().getResult();
                    if (verifyResponse.size()>0)
                    {

                    }
                    else {

                    }
                    verifyDocAdapter = new Verify_Doc_Adapter(getContext(), verifyResponse, new Verify_Doc_Listner() {
                        @Override
                        public void onItemClickedItem(Verifyed_Doc_Response.Result item, int position) {
                            int SpecId = item.getId();
                            String spec_name = item.getName();
                            Bundle bundle = new Bundle();
                            bundle.putInt("SPEC_ID", SpecId);
                            bundle.putString("SPEC_Name",spec_name);
//                            Intent i = new Intent(getContext(), Specialist_Doctor_List.class);
//                            i.putExtras(bundle);
//                            startActivity(i);
                        }
                    });
                    rv_verify_doc.setAdapter(verifyDocAdapter);
                    rv_verify_doc.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
                } else {
                    progressDialog.hideProgressDialog();
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Verifyed_Doc_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void getSpecialization() {
        progressDialog.showProgressDialog();
        TOKEN = "Bearer " + token;
        int limit = 8;
        int offset = 0;
        String keyword = "";
        boolean status = true;
        Call<Specialization_Response> get_spec = ApiService.apiHolders().Get_Spec(TOKEN,limit,offset,keyword,status);
        get_spec.enqueue(new Callback<Specialization_Response>() {
            @Override
            public void onResponse(Call<Specialization_Response> call, Response<Specialization_Response> response) {
                if (response.isSuccessful()) {
                    progressDialog.hideProgressDialog();
                    assert response.body() != null;
                    specResponse = response.body().getResult();
                    if (specResponse.size()>0)
                    {

                    }
                    else {

                    }
                    specializationAdapter = new Home_Spec_Adapter(getContext(), specResponse, new ListSpecListener() {
                        @Override
                        public void onItemClickedItem(Specialization_Response.Result item, int position) {
                            int SpecId = item.getId();
                            String spec_name = item.getName();
                            Bundle bundle = new Bundle();
                            bundle.putInt("SPEC_ID", SpecId);
                            bundle.putString("SPEC_Name",spec_name);
                            Intent i = new Intent(getContext(), Specialist_Doctor_List.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                    GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4);
                    rv_spec.setLayoutManager(layoutManager);
                    rv_spec.setAdapter(specializationAdapter);
                } else {
                    progressDialog.hideProgressDialog();
                    Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Specialization_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void getBanner() {
        progressDialog.showProgressDialog();
        String type = "User";
        Call<Banner_Response> get_banner_api = ApiService.apiHolders().getBanner(type);
        get_banner_api.enqueue(new Callback<Banner_Response>() {
            @Override
            public void onResponse(Call<Banner_Response> call, Response<Banner_Response> response) {
                if (response.isSuccessful()){
                    progressDialog.hideProgressDialog();
                    GetBanner = response.body().getResult();
                    Banner_Adapter bannerAdapter = new Banner_Adapter(getContext(), GetBanner);
                    view_pager.setAdapter(bannerAdapter);
                    final Handler handler = new Handler(Looper.getMainLooper());
                    final Runnable update = () -> {
                        if (currentPage == GetBanner.size()) {
                            currentPage = 0;
                        }
                        view_pager.setCurrentItem(currentPage++, true);
                    };
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.post(update);
                        }
                    }, DELAY_MS, PERIOD_MS);
                }
                else {
                    progressDialog.hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<Banner_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
}