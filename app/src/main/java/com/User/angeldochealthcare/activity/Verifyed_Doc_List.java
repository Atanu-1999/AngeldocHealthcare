package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.adapter.Verify_Doc_Adapter;
import com.User.angeldochealthcare.adapter.Verify_List_Adapter;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.interfacs.Verify_Doc_Listner;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Verifyed_Doc_List extends AppCompatActivity {
    ImageView back;
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String token,TOKEN;
    RecyclerView rv_verify;
    List<Verifyed_Doc_Response.Result> verifyResponse;
    Verify_List_Adapter verifyDocAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyed_doc_list);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        Get_verify_Spec();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void Get_verify_Spec() {
        progressDialog.showProgressDialog();
        TOKEN = "Bearer " + token;
        int limit = 50;
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
                    verifyDocAdapter = new Verify_List_Adapter(Verifyed_Doc_List.this, verifyResponse, new Verify_Doc_Listner() {
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
                    rv_verify.setAdapter(verifyDocAdapter);
                    rv_verify.setLayoutManager(new LinearLayoutManager(Verifyed_Doc_List.this, LinearLayoutManager.VERTICAL, false));
                } else {
                    progressDialog.hideProgressDialog();
                    Toast.makeText(Verifyed_Doc_List.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Verifyed_Doc_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }

    private void inita() {
        progressDialog = new CustomProgressDialog(this);
        rv_verify = findViewById(R.id.rv_verify);
        back = findViewById(R.id.back);
    }
}