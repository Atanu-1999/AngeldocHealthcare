package com.User.angeldochealthcare.adapter;

import static com.User.angeldochealthcare.adapter.Verify_Doc_Adapter.VerifyList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.User.angeldochealthcare.R;
import com.User.angeldochealthcare.interfacs.Specialist_Listner;
import com.User.angeldochealthcare.interfacs.Verify_Doc_Listner;
import com.User.angeldochealthcare.response.Specialist_Response;
import com.User.angeldochealthcare.response.Verifyed_Doc_Response;

import java.util.List;

public class Specialist_Adapter extends RecyclerView.Adapter<Specialist_Adapter.ViewHolder>{
    public static List<Specialist_Response.Result> specialistList;
    private Context context;
    Specialist_Listner listener;

    public Specialist_Adapter(Context context,List<Specialist_Response.Result> specialistList, Specialist_Listner listener) {
        this.specialistList = specialistList;
        this.context = context;
        this.listener = listener;
    }
    @NonNull
    @Override
    public Specialist_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.specialist_layout, parent, false);
        Specialist_Adapter.ViewHolder viewHolder = new Specialist_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Specialist_Adapter.ViewHolder holder, int position) {
        holder.tv_spec_name.setText(specialistList.get(position).getName());
//                if (SpecList.get(position).getImage()==null){
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
        if (specialistList == null) return 0;
        return specialistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_spec_name,btn_spec_List;
        ImageView tv_spec_image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_spec_image = (ImageView) itemView.findViewById(R.id.tv_spec_image);
            tv_spec_name = (TextView) itemView.findViewById(R.id.tv_spec_name);
            btn_spec_List = (TextView) itemView.findViewById(R.id.btn_spec_List);
            btn_spec_List.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickedItem(specialistList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
