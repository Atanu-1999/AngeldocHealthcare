package com.User.angeldochealthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.interfacs.Spec_doc_Listner;
import com.User.angeldochealthcare.interfacs.Verify_Doc_Listner;
import com.User.angeldochealthcare.response.Id_Specialization_Response;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;

import java.util.List;

public class Verify_Doc_Adapter extends RecyclerView.Adapter<Verify_Doc_Adapter.ViewHolder>{
    public static List<Verifyed_Doc_Response.Result> VerifyList;
    private Context context;
    Verify_Doc_Listner listener;

    public Verify_Doc_Adapter(Context context,List<Verifyed_Doc_Response.Result> VerifyList, Verify_Doc_Listner listener) {
        this.VerifyList = VerifyList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Verify_Doc_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.verify_doctor_layout, parent, false);
        Verify_Doc_Adapter.ViewHolder viewHolder = new Verify_Doc_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Verify_Doc_Adapter.ViewHolder holder, int position) {
        holder.txt_doc_Name.setText("Dr. " + VerifyList.get(position).getName());
        holder.tv_fee.setText("₹ " + String.valueOf(VerifyList.get(position).getLiveDoctorFee()) + "Fee ");
        holder.txt_exp.setText("Overall " + String.valueOf(VerifyList.get(position).getExperience()) + " year Experience ");
        holder.txt_degree.setText(VerifyList.get(position).getDoctorSpecialization().get(0).getSpecialization().getName());
        //        if (SpecList.get(position).getImage()==null){
//            holder.spec_image.setBackgroundResource(R.drawable.heart);
//        }
//        else {
//            Picasso.with(context)
//                    .load(SpecList.get(position).getImage())
//                    .into(holder.spec_image);
//        }
    }

    @Override
    public int getItemCount() {
        if (VerifyList == null) return 0;
        return VerifyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_doc_Name,txt_degree,txt_exp,tv_fee,btn_appointment;
        ImageView doc_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doc_image = (ImageView) itemView.findViewById(R.id.doc_image);
            txt_doc_Name = (TextView) itemView.findViewById(R.id.txt_doc_Name);
            txt_degree = (TextView) itemView.findViewById(R.id.txt_degree);
            txt_exp = (TextView) itemView.findViewById(R.id.txt_exp);
            tv_fee = (TextView) itemView.findViewById(R.id.tv_fee);
            btn_appointment = (TextView) itemView.findViewById(R.id.btn_appointment);

            btn_appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickedItem(VerifyList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
