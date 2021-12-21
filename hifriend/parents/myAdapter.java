package com.hifriend.parents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.hifriend.R;
import com.hifriend.Therapist.Therapist;


import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends FirebaseRecyclerAdapter<modelClass,myAdapter.myViewHolder> {

    public myAdapter(FirebaseRecyclerOptions<modelClass> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(myViewHolder holder, int position, modelClass model) {
        holder.name.setText(model.getFull_name());
        holder.email.setText(model.getEmail());
        holder.expertise.setText(model.getArea_of_expertise());
        holder.bio.setText(model.getBio());
        holder.phone.setText(model.getPhone());
        Glide.with(holder.img.getContext()).load(model.image).into(holder.img);


    }


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_therapist_layout,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name, email,expertise, bio, phone;

        public myViewHolder(View itemView) {

            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.name_text);
            email = (TextView) itemView.findViewById(R.id.email_text);
            expertise = (TextView) itemView.findViewById(R.id.expertise_txt);
            bio = (TextView) itemView.findViewById(R.id.bio_txt);
            phone = (TextView) itemView.findViewById(R.id.phone_txt);

         }
    }

}
