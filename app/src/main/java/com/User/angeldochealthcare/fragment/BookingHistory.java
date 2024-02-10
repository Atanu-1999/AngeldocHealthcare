package com.User.angeldochealthcare.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.activity.View_Appointment;

public class BookingHistory extends Fragment {

    TextView txt_view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View history = inflater.inflate(R.layout.fragment_booking_history, container, false);

        txt_view = history.findViewById(R.id.txt_view);
        txt_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), View_Appointment.class));
            }
        });

        return  history;
    }
}