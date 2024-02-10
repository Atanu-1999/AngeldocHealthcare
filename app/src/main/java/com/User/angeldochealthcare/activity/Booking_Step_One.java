package com.User.angeldochealthcare.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.User.angeldochealthcare.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Booking_Step_One extends AppCompatActivity {

    String[] drOther = {"My Self", "Father", "Mother", "Son", "Daughter", "Other"};
    String[] ptGender = {"Male", "Female", "Other"};
    Spinner sp_dr,sp_gender;
    TextView btn_book,txt_profile,tv_years,txt_degree,tv_fee;
    private Calendar calendar;
    String currentTime,app_date;
    ImageView iv_date;
    EditText et_DOB;
    Date dateNow = null;
    int doc_id,doc_exp,doc_fee;
    String doc_name,doc_degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_step_one);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));
        inita();
        Bundle bundle = getIntent().getExtras();
        //Extract the data…
        assert bundle != null;
        doc_id = bundle.getInt("DOC_ID",0);
        doc_name = bundle.getString("doc_name","");
        doc_exp = bundle.getInt("doc_exp",0);
        doc_fee = bundle.getInt("doc_fee",0);
        doc_degree = bundle.getString("doc_degree","");

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
    }
    private void inita() {
        sp_dr = findViewById(R.id.sp_dr);
        sp_gender = findViewById(R.id.sp_gender);
        btn_book = findViewById(R.id.btn_book);
        iv_date = findViewById(R.id.iv_date);
        et_DOB = findViewById(R.id.et_DOB);
        txt_profile = findViewById(R.id.txt_profile);
        tv_years = findViewById(R.id.tv_years);
        txt_degree = findViewById(R.id.txt_degree);
        tv_fee = findViewById(R.id.tv_fee);
    }
}