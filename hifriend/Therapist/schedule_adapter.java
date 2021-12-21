package com.hifriend.Therapist;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class schedule_adapter extends RecyclerView.Adapter<schedule_adapter.scheduleViewHolder> {

    private Context mCtx;
    private List<consult_class> consultList;
    DatabaseReference consultRef;

    public schedule_adapter(Context mCtx, List<consult_class> consultList){
        this.mCtx=mCtx;
        this.consultList=consultList;


    }


    @Override
    public scheduleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.schedule_thera_layout,parent,false);

//        TextView cancel;
//        cancel=view.findViewById(R.id.cancelBtn);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "HELLO", Toast.LENGTH_LONG).show();
//            }
//        });

        return new scheduleViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull scheduleViewHolder holder, int position) {
        consult_class consult_class = consultList.get(position);
        holder.parent_Name.setText(consult_class.getParent_name());
        holder.parent_Email.setText(consult_class.getParent_Email());
        holder.date.setText(consult_class.getConsult_Date());
        holder.time.setText(consult_class.getConsult_time());
       // final String key = getRef(position).getKey();
        consultRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("consulations");

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "HELLO", Toast.LENGTH_LONG).show();
            }
        });
          holder.cancel.setOnClickListener((view) ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.parent_Name.getContext());
            builder.setTitle("Delete Schedule");
            builder.setMessage("Cancel...?");
            builder.setPositiveButton("Yes",((dialog, which) -> {
                consultRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                        //consult_class = snapshot.getRef().getKey();


                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });


            }));
        });

//        holder.res.setText("Residency: " + consult_class.getParent_residency());
//        Glide.with(holder.image.getContext()).load(consult_class.getParent_image()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return consultList.size();
    }

    class scheduleViewHolder extends RecyclerView.ViewHolder{
        TextView parent_Name, parent_Email, date, time,cancel;
        CircleImageView image;

        public scheduleViewHolder(View itemView){
            super(itemView);

            parent_Name = itemView.findViewById(R.id.name_text);
            parent_Email = itemView.findViewById(R.id.email_text);
            date = itemView.findViewById(R.id.date_txt);
            time = itemView.findViewById(R.id.time_txt);
          //  cancel = itemView.findViewById(R.id.cancelBtn);

//            res = itemView.findViewById(R.id.res_txt);
//            image = itemView.findViewById(R.id.img1);

        }


    }


}
