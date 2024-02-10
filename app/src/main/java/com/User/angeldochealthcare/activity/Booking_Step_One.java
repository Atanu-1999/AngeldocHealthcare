package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.adapter.Schedule_Adapter;
import com.User.angeldochealthcare.adapter.Specialization_Adapter;
import com.User.angeldochealthcare.apiServices.ApiService;
import com.User.angeldochealthcare.interfacs.ListSpecListener;
import com.User.angeldochealthcare.interfacs.Schedule_Time_Listner;
import com.User.angeldochealthcare.response.Schedule_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.User.angeldochealthcare.utility.CustomProgressDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Booking_Step_One extends AppCompatActivity {
    String[] drOther = {"My Self", "Father", "Mother", "Son", "Daughter", "Other"};
    String[] ptGender = {"Male", "Female", "Other"};
    Spinner sp_dr, sp_gender;
    TextView btn_book, txt_profile, tv_years, txt_degree, tv_fee, tv_morning, tv_afternoon, tv_evening, txt_night;
    private Calendar calendar;
    String currentTime, app_date;
    ImageView iv_date, back;
    EditText et_DOB;
    Date dateNow = null;
    int doc_id, doc_exp, doc_fee;
    String doc_name, doc_degree;
    CustomProgressDialog progressDialog;
    SharedPreferences loginPref;
    SharedPreferences.Editor editor;
    String token, TOKEN, type;
    RecyclerView rv_schedule;
    List<Schedule_Response.Period> scheduleResponse;
    Schedule_Adapter schedule_adapter;
    LinearLayout no_schedule_layout, if_not_Sch_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step_one);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
        loginPref = getSharedPreferences("login_pref", Context.MODE_PRIVATE);
        editor = loginPref.edit();
        token = loginPref.getString("TOKEN", null);
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        assert bundle != null;
        doc_id = bundle.getInt("DOC_ID", 0);
        doc_name = bundle.getString("doc_name", "");
        doc_exp = bundle.getInt("doc_exp", 0);
        doc_fee = bundle.getInt("doc_fee", 0);
        //doc_degree = bundle.getString("doc_degree","");

        txt_profile.setText(doc_name);
        tv_years.setText(String.valueOf(doc_exp) + "Years");
        tv_fee.setText("₹ " + String.valueOf(doc_fee));
        txt_degree.setText(doc_degree);

        ArrayAdapter<String> Dr = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, drOther);
        Dr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_dr.setAdapter(Dr);
        sp_dr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dr = sp_dr.getSelectedItem().toString();

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
                        Booking_Step_One.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                                et_DOB.setText(dateFormat1.format(calendar.getTime()));
                                app_date = dateFormat1.format(calendar.getTime());
                            }
                        }, year, month, dayOfMonth);
//                try {
//                    dateNow = formatter.parse(currentTime);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
                //datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });

        ArrayAdapter<String> pt_gender = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, ptGender);
        pt_gender.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_gender.setAdapter(pt_gender);
        sp_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dr = sp_gender.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Booking_Step_One.this, Booking_Preview.class));
            }
        });
        tv_morning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_morning.setBackgroundResource(R.drawable.schedule_bg);
                tv_afternoon.setBackgroundResource(R.drawable.edite_bg);
                tv_evening.setBackgroundResource(R.drawable.edite_bg);
                txt_night.setBackgroundResource(R.drawable.edite_bg);

                tv_afternoon.setTextColor(getResources().getColor(R.color.black));
                tv_morning.setTextColor(getResources().getColor(R.color.white));
                tv_evening.setTextColor(getResources().getColor(R.color.black));
                txt_night.setTextColor(getResources().getColor(R.color.black));

                type = tv_morning.getText().toString();
                if (et_DOB.getText().toString().equals("")){
                    Toast.makeText(Booking_Step_One.this, "Please Select Appointment Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Schedule_Booking(type);
                }

            }
        });
        tv_afternoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_afternoon.setBackgroundResource(R.drawable.schedule_bg);
                tv_morning.setBackgroundResource(R.drawable.edite_bg);
                tv_evening.setBackgroundResource(R.drawable.edite_bg);
                txt_night.setBackgroundResource(R.drawable.edite_bg);

                tv_afternoon.setTextColor(getResources().getColor(R.color.white));
                tv_morning.setTextColor(getResources().getColor(R.color.black));
                tv_evening.setTextColor(getResources().getColor(R.color.black));
                txt_night.setTextColor(getResources().getColor(R.color.black));
                type = tv_afternoon.getText().toString();
                if (et_DOB.getText().toString().equals("")){
                    Toast.makeText(Booking_Step_One.this, "Please Select Appointment Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Schedule_Booking(type);
                }
            }
        });
        tv_evening.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_evening.setBackgroundResource(R.drawable.schedule_bg);
                tv_morning.setBackgroundResource(R.drawable.edite_bg);
                tv_afternoon.setBackgroundResource(R.drawable.edite_bg);
                txt_night.setBackgroundResource(R.drawable.edite_bg);

                tv_afternoon.setTextColor(getResources().getColor(R.color.black));
                tv_morning.setTextColor(getResources().getColor(R.color.black));
                tv_evening.setTextColor(getResources().getColor(R.color.white));
                txt_night.setTextColor(getResources().getColor(R.color.black));
                type = tv_evening.getText().toString();
                if (et_DOB.getText().toString().equals("")){
                    Toast.makeText(Booking_Step_One.this, "Please Select Appointment Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Schedule_Booking(type);
                }
            }
        });
        txt_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt_night.setBackgroundResource(R.drawable.schedule_bg);
                tv_morning.setBackgroundResource(R.drawable.edite_bg);
                tv_afternoon.setBackgroundResource(R.drawable.edite_bg);
                tv_evening.setBackgroundResource(R.drawable.edite_bg);

                tv_afternoon.setTextColor(getResources().getColor(R.color.black));
                tv_morning.setTextColor(getResources().getColor(R.color.black));
                tv_evening.setTextColor(getResources().getColor(R.color.black));
                txt_night.setTextColor(getResources().getColor(R.color.white));
                type = txt_night.getText().toString();
                if (et_DOB.getText().toString().equals("")){
                    Toast.makeText(Booking_Step_One.this, "Please Select Appointment Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    Schedule_Booking(type);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Schedule_Booking(String type) {
        progressDialog.showProgressDialog();
        TOKEN = "Bearer " + token;
        String fromDate = app_date;
        String toDate = app_date;
        String mode = "Regular";
        Call<Schedule_Response> get_schdule = ApiService.apiHolders().Get_Schedule(doc_id, TOKEN, fromDate,toDate , mode, type);
        get_schdule.enqueue(new Callback<Schedule_Response>() {
            @Override
            public void onResponse(Call<Schedule_Response> call, Response<Schedule_Response> response) {
                if (response.isSuccessful()) {
                    progressDialog.hideProgressDialog();
                    assert response.body() != null;
                    scheduleResponse = response.body().getResult().getPeriod();
                    if (scheduleResponse.size() > 0) {
                        rv_schedule.setVisibility(View.VISIBLE);
                        if_not_Sch_Layout.setVisibility(View.VISIBLE);
                        no_schedule_layout.setVisibility(View.GONE);
                        progressDialog.hideProgressDialog();
                        schedule_adapter = new Schedule_Adapter(getApplicationContext(), scheduleResponse, new Schedule_Time_Listner() {
                            @Override
                            public void onItemClickedItem(Schedule_Response.Period item, int position) {
//                            int SpecId = item.getId();
//                            String spec_name = item.getName();
//                            Bundle bundle = new Bundle();
//                            bundle.putInt("SPEC_ID", SpecId);
//                            bundle.putString("SPEC_Name",spec_name);
//                            Intent i = new Intent(Booking_Step_One.this, Specialist_Doctor_List.class);
//                            i.putExtras(bundle);
//                            startActivity(i);
                            }
                        });
                        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
                        rv_schedule.setLayoutManager(layoutManager);
                        rv_schedule.setAdapter(schedule_adapter);
                    } else {
                        rv_schedule.setVisibility(View.GONE);
                        if_not_Sch_Layout.setVisibility(View.GONE);
                        no_schedule_layout.setVisibility(View.VISIBLE);
                        progressDialog.hideProgressDialog();
                    }
                } else {
                    progressDialog.hideProgressDialog();
                    Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Schedule_Response> call, Throwable t) {
                rv_schedule.setVisibility(View.GONE);
                if_not_Sch_Layout.setVisibility(View.GONE);
                no_schedule_layout.setVisibility(View.VISIBLE);
                progressDialog.hideProgressDialog();
            }
        });
    }
    private void inita() {
        progressDialog = new CustomProgressDialog(this);
        sp_dr = findViewById(R.id.sp_dr);
        sp_gender = findViewById(R.id.sp_gender);
        btn_book = findViewById(R.id.btn_book);
        iv_date = findViewById(R.id.iv_date);
        et_DOB = findViewById(R.id.et_DOB);
        txt_profile = findViewById(R.id.txt_profile);
        tv_years = findViewById(R.id.tv_years);
        txt_degree = findViewById(R.id.txt_degree);
        tv_fee = findViewById(R.id.tv_fee);
        txt_night = findViewById(R.id.txt_night);
        tv_morning = findViewById(R.id.tv_morning);
        tv_afternoon = findViewById(R.id.tv_afternoon);
        tv_evening = findViewById(R.id.tv_evening);
        back = findViewById(R.id.back);
        rv_schedule = findViewById(R.id.rv_schedule);
        no_schedule_layout = findViewById(R.id.no_schedule_layout);
        if_not_Sch_Layout = findViewById(R.id.if_not_Sch_Layout);
    }
}