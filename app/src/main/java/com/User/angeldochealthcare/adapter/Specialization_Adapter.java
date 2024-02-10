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
import com.User.angeldochealthcare.response.Notification_Response;
import com.User.angeldochealthcare.response.Specialization_Response;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Specialization_Adapter extends RecyclerView.Adapter<Specialization_Adapter.ViewHolder>{
    public static List<Specialization_Response.Result> SpecList;
    private Context context;
    ListSpecListener listener;
    public Specialization_Adapter(Context context, List<Specialization_Response.Result> SpecList, ListSpecListener listener) {
        this.SpecList = SpecList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Specialization_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spec_layout, parent, false);
        Specialization_Adapter.ViewHolder viewHolder = new Specialization_Adapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Specialization_Adapter.ViewHolder holder, int position) {
        holder.spec_name.setText(SpecList.get(position).getName());
        if (SpecList.get(position).getImage()==null){
            holder.spec_image.setBackgroundResource(R.drawable.heart);
        }
        else {
            Picasso.with(context)
                    .load(SpecList.get(position).getImage())
                    .into(holder.spec_image);
        }
    }

    @Override
    public int getItemCount() {
        if (SpecList == null) return 0;
        return SpecList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView spec_name;
        ImageView spec_image;
        CardView item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            spec_image = (ImageView) itemView.findViewById(R.id.spec_image);
            spec_name = (TextView) itemView.findViewById(R.id.spec_name);
            item = (CardView) itemView.findViewById(R.id.item);

            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClickedItem(SpecList.get(getAdapterPosition()), getAdapterPosition());
                }
            });
        }
    }
}
