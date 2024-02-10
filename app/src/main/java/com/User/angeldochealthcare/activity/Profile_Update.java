package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.response.Get_Profile_Response;
import com.User.angeldochealthcare.response.LoginResponse;
import com.User.angeldochealthcare.response.Profile_Insert_Response;
import com.User.angeldochealthcare.response.Profile_Upadate_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;
import com.User.angeldochealthcare.utility.DateUtils;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile_Update extends AppCompatActivity {
    String[] ptGender = {"Male", "Female", "Other"};
    Spinner sp_gender;
    EditText et_name,et_nick_name,et_email,et_number,et_DOB;
    ImageView iv_date,upload_image;
    private Calendar calendar;
    String currentTime,dob,name,nickName,email,contactNumber,gender,Token,token;
    Date dateNow = null;
    TextView btn_update,txt_name;
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        Get_Profile();
        /*Gender Drop down*/
        ArrayAdapter<String> pt_gender = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ptGender);
        pt_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(pt_gender);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gender = sp_gender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*Current date DOB*/
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        calendar = Calendar.getInstance();
        iv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        Profile_Update.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                et_DOB.setText(dateFormat1.format(calendar.getTime()));
                                dob = dateFormat1.format(calendar.getTime());
                            }
                        }, year, month, dayOfMonth);
                try {
                    dateNow = formatter.parse(currentTime);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().isEmpty()){
                    Toast.makeText(Profile_Update.this, "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                }
                else if (et_email.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_Update.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                else if (et_number.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_Update.this, "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                }
                else if (et_DOB.getText().toString().isEmpty()) {
                    Toast.makeText(Profile_Update.this, "Please Enter Your DOB", Toast.LENGTH_SHORT).show();
                }
                else {
                    Insert_Details();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void Get_Profile() {
        progressDialog.showProgressDialog();
        Token = "Bearer " + token;
        Call<Get_Profile_Response> get_apiCall = ApiService.apiHolders().get_profile(Token);
        get_apiCall.enqueue(new Callback<Get_Profile_Response>() {
            @Override
            public void onResponse(Call<Get_Profile_Response> call, Response<Get_Profile_Response> response) {
                if (response.code() == 404){
                    progressDialog.hideProgressDialog();
                }
                else {
                    if (response.isSuccessful()){
                        progressDialog.hideProgressDialog();
                        et_name.setText(response.body().getName());
                        txt_name.setText(response.body().getName());
                        et_nick_name.setText(response.body().getNickName());
                        et_email.setText(response.body().getEmail());
                        et_number.setText(response.body().getContactNumber());
                        et_DOB.setText(response.body().getDob());
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
    private void update_Profile() {
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        contactNumber = et_number.getText().toString();
        nickName = et_nick_name.getText().toString();
        dob = et_DOB.getText().toString();
        Token = "Bearer " + token;
        progressDialog.showProgressDialog();
        Call<Profile_Upadate_Response> update_apiCall = ApiService.apiHolders().profile_update(name,nickName,email,contactNumber,dob,gender,Token);
        update_apiCall.enqueue(new Callback<Profile_Upadate_Response>() {
            @Override
            public void onResponse(Call<Profile_Upadate_Response> call, Response<Profile_Upadate_Response> response) {
                if (response.isSuccessful()){
                    progressDialog.hideProgressDialog();
                    Get_Profile();
                    Toast.makeText(Profile_Update.this, "Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.hideProgressDialog();
                }
            }
            @Override
            public void onFailure(Call<Profile_Upadate_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void Insert_Details() {
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        contactNumber = et_number.getText().toString();
        nickName = et_nick_name.getText().toString();
        dob = et_DOB.getText().toString();
        Token = "Bearer " + token;
        progressDialog.showProgressDialog();
        Call<Profile_Insert_Response> insert_apiCall = ApiService.apiHolders().profile_add(name,nickName,email,contactNumber,dob,gender,Token);
        insert_apiCall.enqueue(new Callback<Profile_Insert_Response>() {
            @Override
            public void onResponse(Call<Profile_Insert_Response> call, Response<Profile_Insert_Response> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Profile_Update.this, "Profile Upload Successfully", Toast.LENGTH_SHORT).show();
                    progressDialog.hideProgressDialog();
                }
                else {
                    progressDialog.hideProgressDialog();
                }
            }
            @Override
            public void onFailure(Call<Profile_Insert_Response> call, Throwable t) {
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void inita() {
        progressDialog = new CustomProgressDialog(this);
        sp_gender = findViewById(R.id.sp_gender);
        et_name = findViewById(R.id.et_name);
        et_nick_name = findViewById(R.id.et_nick_name);
        et_email = findViewById(R.id.et_email);
        et_number = findViewById(R.id.et_number);
        et_DOB = findViewById(R.id.et_DOB);
        iv_date = findViewById(R.id.iv_date);
        btn_update = findViewById(R.id.btn_update);
        txt_name = findViewById(R.id.txt_name);
        back = findViewById(R.id.back);
        upload_image = findViewById(R.id.upload_image);
    }
}