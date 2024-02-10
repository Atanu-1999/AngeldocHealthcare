package com.User.angeldochealthcare.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.adapter.Specialization_Adapter;
import com.User.angeldochealthcare.adapter.Specialization_By_Doctor_Adapter;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.interfacs.ListSpecListener;
import com.User.angeldochealthcare.interfacs.Spec_doc_Listner;
import com.User.angeldochealthcare.response.Id_Specialization_Response;
import com.User.angeldochealthcare.response.Pages_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Specialist_Doctor_List extends AppCompatActivity {
    CustomProgressDialog progressDialog;
    ImageView back;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String Token,token,spec_name;
    int spec_id;
    RecyclerView rv_spec_doc;
    List<Id_Specialization_Response.Result> specResponse;
    Specialization_By_Doctor_Adapter specialization_by_doctor_adapter;
    LinearLayout ll_view;
    TextView specialization_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialist_doctor_list);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
        /*Function*/
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        assert bundle != null;
        spec_id = bundle.getInt("SPEC_ID",0);
        spec_name = bundle.getString("SPEC_Name","");
        specialization_name.setText(spec_name);
        spec_by_ID(spec_id);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void spec_by_ID(int spec_id) {
        progressDialog.showProgressDialog();
        Token = "Bearer " + token;
        int limit = 50;
        int offset = 0;
        String keyword = "";

        Call<Id_Specialization_Response> get_id_spec = ApiService.apiHolders().Get_Id_Spec(spec_id,Token,limit,offset,keyword);
        get_id_spec.enqueue(new Callback<Id_Specialization_Response>() {
            @Override
            public void onResponse(Call<Id_Specialization_Response> call, Response<Id_Specialization_Response> response) {
                if (response.isSuccessful()) {
                    progressDialog.hideProgressDialog();
                    assert response.body() != null;
                    specResponse = response.body().getResult();
                    if (specResponse.size()>0)
                    {
                        ll_view.setVisibility(View.GONE);
                        rv_spec_doc.setVisibility(View.VISIBLE);
                        progressDialog.hideProgressDialog();
                    }
                    else {
                        ll_view.setVisibility(View.VISIBLE);
                        rv_spec_doc.setVisibility(View.GONE);
                    }
                    specialization_by_doctor_adapter = new Specialization_By_Doctor_Adapter(getApplicationContext(), specResponse, new Spec_doc_Listner() {
                        @Override
                        public void onItemClickedItem(Id_Specialization_Response.Result item, int position) {
                            int DocId = item.getId();
                            String doc_name = item.getName();
                            int doc_exp = item.getExperience();
                            int doc_fee = item.getLiveDoctorFee();
                            String doc_degree = item.getDoctorSpecialization().get(0).getSpecialization().getName();

                            Bundle bundle = new Bundle();
                            bundle.putInt("DOC_ID", DocId);
                            bundle.putString("doc_name",doc_name);
                            bundle.putInt("doc_exp",doc_exp);
                            bundle.putInt("doc_fee",doc_fee);
                            bundle.putString("doc_degree",doc_degree);
                            Intent i = new Intent(Specialist_Doctor_List.this, Booking_Step_One.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                    });
                    rv_spec_doc.setLayoutManager(new LinearLayoutManager(Specialist_Doctor_List.this));
                    rv_spec_doc.setAdapter(specialization_by_doctor_adapter);
                } else {
                    progressDialog.hideProgressDialog();
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Id_Specialization_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void inita() {
        progressDialog = new CustomProgressDialog(this);
        rv_spec_doc = findViewById(R.id.rv_spec_doc);
        back = findViewById(R.id.back);
        ll_view = findViewById(R.id.ll_view);
        specialization_name = findViewById(R.id.specialization_name);
    }
}