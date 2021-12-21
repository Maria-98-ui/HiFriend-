package com.hifriend.parents;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hifriend.R;
import com.hifriend.Therapist.schedule_adapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class sched_adapter extends RecyclerView.Adapter<sched_adapter.schedViewHolder> {

    private Context mCtx;
    private List<consult_class> consultList;

    public sched_adapter(Context mCtx, List<consult_class> consultList){
        this.mCtx=mCtx;
        this.consultList=consultList;


    }


    @NonNull
    @NotNull
    @Override
    public schedViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.parent_sched_layout,parent,false);

        return new schedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull schedViewHolder holder, int position) {
        consult_class consult_class = consultList.get(position);
        holder.thera_Name.setText(consult_class.getTherapist_Name());
        holder.thera_Email.setText(consult_class.getTherapist_Email());
        holder.date.setText(consult_class.getConsult_Date());
        holder.time.setText(consult_class.getConsult_time());

    }

    @Override
    public int getItemCount() {
        return consultList.size();
    }

    class schedViewHolder extends RecyclerView.ViewHolder {
        TextView thera_Name, thera_Email, date, time;
        CircleImageView image;

        public schedViewHolder(View itemView) {
            super(itemView);

            thera_Name = itemView.findViewById(R.id.name_text);
            thera_Email = itemView.findViewById(R.id.email_text);
            date = itemView.findViewById(R.id.date_txt);
            time = itemView.findViewById(R.id.time_txt);


        }
    }
}
