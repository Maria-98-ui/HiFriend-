package com.hifriend.Therapist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hifriend.R;
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class schedule_view_adapt extends FirebaseRecyclerAdapter<consult_class,schedule_view_adapt.scheduleViewHolder> {


 public schedule_view_adapt(FirebaseRecyclerOptions<consult_class> options) {
        super(options);
        }


    @Override
    protected void onBindViewHolder(@NonNull @NotNull schedule_view_adapt.scheduleViewHolder holder, int position, @NonNull @NotNull consult_class model) {
        holder.parent_Name.setText(model.getParent_name());
        holder.parent_Email.setText(model.getParent_Email());
        holder.date.setText(model.getConsult_Date());
        holder.time.setText(model.getConsult_time());

        final DatabaseReference consultRef;
        consultRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations");


        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.parent_Name.getContext());
                builder.setTitle("Delete Schedule");
                builder.setMessage("Cancel...?");

                builder.setPositiveButton("Yes", ((dialog, which) -> {
                    consultRef.child(getRef(position).getKey()).removeValue();
                    Toast.makeText(builder.getContext(), "Schedule cancelled", Toast.LENGTH_LONG).show();


                }));
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

            }
        });
    }



    @Override
    public schedule_view_adapt.scheduleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_thera_layout, parent, false);

//        TextView cancel;
//        cancel = view.findViewById(R.id.cancelBtn);
        final DatabaseReference consultRef;
        consultRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations");

//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
//                builder.setTitle("Delete Schedule");
//                builder.setMessage("Cancel...?");
//
//                builder.setPositiveButton("Yes", ((dialog, which) -> {
//                    consultRef.child(getRef(viewType).getKey()).removeValue();
//                    Toast.makeText(builder.getContext(), "Schedule cancelled", Toast.LENGTH_LONG).show();
//
//
//                }));
//                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//            }
       // });

        return new scheduleViewHolder(view);
    }

    static class scheduleViewHolder extends RecyclerView.ViewHolder {
        TextView parent_Name, parent_Email, date, time ,cancel;
        CircleImageView image;

        public scheduleViewHolder(View itemView) {
            super(itemView);

            parent_Name = itemView.findViewById(R.id.name_text);
            parent_Email = itemView.findViewById(R.id.email_text);
            date = itemView.findViewById(R.id.date_txt);
            time = itemView.findViewById(R.id.time_txt);
           // cancel = itemView.findViewById(R.id.cancelBtn);


//            res = itemView.findViewById(R.id.res_txt);
//            image = itemView.findViewById(R.id.img1);

        }
    }




}
