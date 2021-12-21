package com.hifriend.Therapist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hifriend.R;
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class  parent_adapter extends RecyclerView.Adapter<parent_adapter.parentViewHolder> {

    private Context mCtx;
    private List<consult_class> consultList;

    public parent_adapter(Context mCtx, List<consult_class> consultList){
        this.mCtx=mCtx;
        this.consultList=consultList;


    }
    @Override
    public parentViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view =LayoutInflater.from(mCtx).inflate(R.layout.thera_parents_layout,parent,false);

        return new parentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull parentViewHolder holder, int position) {

        consult_class consult_class = consultList.get(position);
        holder.parent_Name.setText(consult_class.getParent_name());
        holder.parent_Email.setText("Email: " + consult_class.getParent_Email());
        holder.childs_Name.setText("Childs name: " + consult_class.getChils_Name());
        holder.childs_Diagnosis.setText("Childs diagnosis age: " + consult_class.getChild_Diagnosis_Age());
        holder.res.setText("Residency: " + consult_class.getParent_residency());
        Glide.with(holder.image.getContext()).load(consult_class.getParent_image()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return consultList.size();
    }

    class parentViewHolder extends RecyclerView.ViewHolder{
        TextView parent_Name, parent_Email, childs_Name, childs_Diagnosis, res;
        CircleImageView image;

        public parentViewHolder(View itemView){
            super(itemView);

            parent_Name = itemView.findViewById(R.id.name_text);
            parent_Email = itemView.findViewById(R.id.email_text);
            childs_Name = itemView.findViewById(R.id.childs_name_txt);
            childs_Diagnosis = itemView.findViewById(R.id.childs_dia_txt);
            res = itemView.findViewById(R.id.res_txt);
            image = itemView.findViewById(R.id.img1);

        }


    }
}
