package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.adapter.Spec_Search_Adapter;
import com.User.angeldochealthcare.adapter.Specialization_Adapter;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.interfacs.ListSpecListener;
import com.User.angeldochealthcare.interfacs.Spec_Search_Listner;
import com.User.angeldochealthcare.response.Spec_Search_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Specialization_List extends AppCompatActivity {
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    ImageView back;
    String token,TOKEN;
    RecyclerView rv_spec;
    List<Specialization_Response.Result> specResponse;
    Specialization_Adapter specializationAdapter;
    List<Spec_Search_Response.Result> searchResponse;
    Spec_Search_Adapter spec_search_adapter;
    LinearLayout ll_view;
    SearchView sv_search;
    String cName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialization_list);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        Get_Spec();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                cName = s;
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                cName = newText;
                searchSku(newText);
                return false;
            }
        });
    }

    private void searchSku(String newText) {
        if (newText.isEmpty()) {
            // Toast.makeText(getApplicationContext(), "Please Enter Diseases Name", Toast.LENGTH_SHORT).show();
//            rv_disease.setVisibility(View.GONE);
//            ll_search.setVisibility(View.VISIBLE);
        } else {
            callSearchDisease(newText);
        }
    }

    private void callSearchDisease(String newText) {
        progressDialog.showProgressDialog();
        TOKEN = "Bearer " + token;
        int limit = 50;
        int offset = 0;
        String keyword = newText;
        boolean status = true;
        Call<Spec_Search_Response> get_Search_spec = ApiService.apiHolders().Spec_Search(TOKEN,limit,offset,keyword,status);
        get_Search_spec.enqueue(new Callback<Spec_Search_Response>() {
            @Override
            public void onResponse(Call<Spec_Search_Response> call, Response<Spec_Search_Response> response) {
                if (response.isSuccessful()) {
                    progressDialog.hideProgressDialog();
                    assert response.body() != null;
                    searchResponse = response.body().getResult();
                    if (searchResponse.size()>0)
                    {
                        ll_view.setVisibility(View.GONE);
                    }

                    spec_search_adapter = new Spec_Search_Adapter(getApplicationContext(), searchResponse, new Spec_Search_Listner() {
                        @Override
                        public void onItemClickedItem(Spec_Search_Response.Result item, int position) {
                            int SpecId = item.getId();
                            String spec_name = item.getName();
                            Bundle bundle = new Bundle();
                            bundle.putInt("SPEC_ID", SpecId);
                            bundle.putString("SPEC_Name",spec_name);
                            Intent i = new Intent(Specialization_List.this, Specialist_Doctor_List.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    rv_spec.setLayoutManager(layoutManager);
                    rv_spec.setAdapter(spec_search_adapter);
                } else {
                    progressDialog.hideProgressDialog();
                    ll_view.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Spec_Search_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }

    private void Get_Spec() {
        progressDialog.showProgressDialog();
        TOKEN = "Bearer " + token;
        int limit = 50;
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
                        ll_view.setVisibility(View.GONE);
                    }

                    specializationAdapter = new Specialization_Adapter(getApplicationContext(), specResponse, new ListSpecListener() {
                        @Override
                        public void onItemClickedItem(Specialization_Response.Result item, int position) {
                            int SpecId = item.getId();
                            String spec_name = item.getName();
                            Bundle bundle = new Bundle();
                            bundle.putInt("SPEC_ID", SpecId);
                            bundle.putString("SPEC_Name",spec_name);
                            Intent i = new Intent(Specialization_List.this, Specialist_Doctor_List.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                    GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
                    rv_spec.setLayoutManager(layoutManager);
                    rv_spec.setAdapter(specializationAdapter);
                } else {
                    progressDialog.hideProgressDialog();
                    ll_view.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Specialization_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }

    private void inita() {
        progressDialog = new CustomProgressDialog(this);
        rv_spec = findViewById(R.id.rv_spec);
        ll_view = findViewById(R.id.ll_view);
        back = findViewById(R.id.back);
        sv_search = findViewById(R.id.sv_search);
    }
}