package com.hifriend.parents;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.hifriend.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class adapter_thera extends FirebaseRecyclerAdapter<modelClass_consult,adapter_thera.myViewHolder> {


    public adapter_thera(FirebaseRecyclerOptions<modelClass_consult> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(myViewHolder holder, int position, modelClass_consult model) {
        holder.name.setText(model.getFull_name());
        holder.email.setText(model.getEmail());
       // holder.expertise.setText(model.getArea_of_expertise());
       // holder.phone.setText(model.getPhone());
        Glide.with(holder.img1.getContext()).load(model.getImage()).into(holder.img1);



        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity)v.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new therapist_parent_consult(model.getFull_name(),model.getEmail(),model.getArea_of_expertise(),model.getPhone(),
                                model.getImage(),model.getDate(),model.getTime()))
                        .addToBackStack(null).commit();
            }
        });

//        Button consult;
//        consult = (Button) therapist_parent_consult.consult;
//        consult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AppCompatActivity activity = (AppCompatActivity)v.getContext();
//                activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                        new book_consultation(model.getFull_name(),model.getEmail(),model.getArea_of_expertise(),model.getPhone()))
//                        .addToBackStack(null).commit();
//            }
//        });

    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_therapist_layout,parent,false);
        return new myViewHolder(view);

    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img1;
        TextView name, email, expertise, phone,click;

        public myViewHolder(View itemView){
            super(itemView);
            img1 = itemView.findViewById(R.id.img1);
            name = itemView.findViewById(R.id.name_text);
            email = itemView.findViewById(R.id.email_text);
           // expertise = itemView.findViewById(R.id.expertise_txt);
           // phone = itemView.findViewById(R.id.phone_txt);

            //click = itemView.findViewById(R.id.click_here);

        }

    }
}
