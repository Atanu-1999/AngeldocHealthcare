package com.User.angeldochealthcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.interfacs.ListSpecListener;
import com.User.angeldochealthcare.interfacs.Spec_doc_Listner;
import com.User.angeldochealthcare.response.Id_Specialization_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Specialization_By_Doctor_Adapter extends RecyclerView.Adapter<Specialization_By_Doctor_Adapter.ViewHolder>{

    public static List<Id_Specialization_Response.Result> SpecList;
    private Context context;
    Spec_doc_Listner listener;

    public Specialization_By_Doctor_Adapter(Context context,List<Id_Specialization_Response.Result> SpecList, Spec_doc_Listner listener) {
        this.SpecList = SpecList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Specialization_By_Doctor_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_doc_layout, parent, false);
        Specialization_By_Doctor_Adapter.ViewHolder viewHolder = new Specialization_By_Doctor_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Specialization_By_Doctor_Adapter.ViewHolder holder, int position) {
        holder.txt_doc_name.setText("Dr. " + SpecList.get(position).getName());
        holder.txt_fee.setText("₹ " + String.valueOf(SpecList.get(position).getLiveDoctorFee()) + " Consulting Fee ");
        holder.txt_exp.setText("Overall " + String.valueOf(SpecList.get(position).getExperience()) + " year Experience ");
        holder.txt_degree.setText(SpecList.get(position).getDoctorSpecialization().get(0).getSpecialization().getName());
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
        if (SpecList == null) return 0;
        return SpecList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_doc_name,txt_degree,txt_exp,txt_fee,btn_appointment;
        ImageView doc_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            doc_image = (ImageView) itemView.findViewById(R.id.doc_image);
            txt_doc_name = (TextView) itemView.findViewById(R.id.txt_doc_name);
            txt_degree = (TextView) itemView.findViewById(R.id.txt_degree);
            txt_exp = (TextView) itemView.findViewById(R.id.txt_exp);
            txt_fee = (TextView) itemView.findViewById(R.id.txt_fee);
            btn_appointment = (TextView) itemView.findViewById(R.id.btn_appointment);

            btn_appointment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickedItem(SpecList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
