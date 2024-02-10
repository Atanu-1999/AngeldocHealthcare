package com.User.angeldochealthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.interfacs.ListSpecListener;
import com.User.angeldochealthcare.interfacs.Schedule_Time_Listner;
import com.User.angeldochealthcare.response.Schedule_Response;
import com.User.angeldochealthcare.response.Specialization_Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Schedule_Adapter extends RecyclerView.Adapter<Schedule_Adapter.ViewHolder>{
    public static List<Schedule_Response.Period> timeList;
    private Context context;
    Schedule_Time_Listner listener;

    public Schedule_Adapter(Context context, List<Schedule_Response.Period> timeList, Schedule_Time_Listner listener) {
        this.timeList = timeList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Schedule_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_layout, parent, false);
        Schedule_Adapter.ViewHolder viewHolder = new Schedule_Adapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull Schedule_Adapter.ViewHolder holder, int position) {
        try {
            // Parse the input time string
            SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = inputFormat.parse(timeList.get(position).getTimeStart());
            // Create a calendar object and set the parsed time
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            // Format the time in the desired output format
            SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
            String outputTime = outputFormat.format(calendar.getTime());
            holder.start_time.setText(outputTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            // Parse the input time string
            SimpleDateFormat inputFormat1 = new SimpleDateFormat("HH:mm:ss");
            Date date1 = inputFormat1.parse(timeList.get(position).getTimeEnd());
            // Create a calendar object and set the parsed time
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date1);

            // Format the time in the desired output format
            SimpleDateFormat outputFormat1 = new SimpleDateFormat("hh:mm a");
            String outputTime1 = outputFormat1.format(calendar1.getTime());
            holder.end_time.setText(outputTime1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int getItemCount() {
        if (timeList == null) return 0;
        return timeList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView start_time,end_time;
        LinearLayout item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            start_time = (TextView) itemView.findViewById(R.id.start_time);
            end_time = (TextView) itemView.findViewById(R.id.end_time);
            item = (LinearLayout) itemView.findViewById(R.id.item);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickedItem(timeList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
