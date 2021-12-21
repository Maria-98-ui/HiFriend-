package com.hifriend.parents;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hifriend.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class schedule_view_parent extends Fragment {
    RecyclerView recyclerView;
     DatabaseReference consultRef, theraRef;
    private FirebaseAuth mAuth;
    String _NAME;
    ArrayList arrayList = new ArrayList();

    public schedule_view_parent(){

        //empty constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sched,container,false);
        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();


//        toolbar = (Toolbar) root.findViewById(R.id.app_bar);
        // compatActivity.getActionBar().setTitle("Your Schedule");

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        consultRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations");


        recyclerView = view.findViewById(R.id.parent_recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setHasFixedSize(true);



        return view;

    }

    @Override
    public void onStart () {
        super.onStart();

//        theraRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                _NAME = snapshot.child("full_name").getValue().toString();

        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();

        Query firebaseSearchQuery = consultRef.orderByChild("parent_id").startAt(id).endAt(id + "\uf8ff");
        FirebaseRecyclerOptions<consult_class> options = new FirebaseRecyclerOptions.Builder<consult_class>().setQuery(firebaseSearchQuery, consult_class.class).build();


//        Query firebaseSearchQuery = consultRef.orderByChild("therapist_Name").equalTo(_NAME);
//        FirebaseRecyclerOptions<consult_class> options = new FirebaseRecyclerOptions.Builder<consult_class>().setQuery(firebaseSearchQuery,consult_class.class).build();

        FirebaseRecyclerAdapter<consult_class, scheduleViewHolder> adapter = new FirebaseRecyclerAdapter<consult_class, scheduleViewHolder>(options) {
            @Override
            protected void onBindViewHolder(scheduleViewHolder holder, int position, consult_class model) {

                holder.thera_Name.setText(model.getTherapist_Name());
                holder.thera_Email.setText(model.getTherapist_Email());
                holder.date.setText(model.getConsult_Date());
                holder.time.setText(model.getConsult_time());
                holder.cancel.setClickable(true);

                holder.cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Delete Schedule");
                        builder.setMessage("Cancel...?");

                        builder.setPositiveButton("Yes", ((dialog, which) -> {
                            consultRef.child(getRef(position).getKey()).removeValue();
                            Toast.makeText(getContext(), "Schedule cancelled", Toast.LENGTH_LONG).show();


                        }));
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();

                    }
                });
            }
            @Override
            public scheduleViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parent_sched_layout, parent, false);
                scheduleViewHolder scheduleViewHolder = new scheduleViewHolder(view);


                return scheduleViewHolder;
            }
          };

                recyclerView.setAdapter(adapter);
                adapter.startListening();




//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//
//
//        });


}

    static class scheduleViewHolder extends RecyclerView.ViewHolder {
        TextView thera_Name, thera_Email, date, time, cancel;
        CircleImageView image;

        public scheduleViewHolder(View itemView) {
            super(itemView);

            thera_Name = itemView.findViewById(R.id.name_text);
            thera_Email = itemView.findViewById(R.id.email_text);
            date = itemView.findViewById(R.id.date_txt);
            time = itemView.findViewById(R.id.time_txt);
            cancel = itemView.findViewById(R.id.cancel);
            //itemView.setOnClickListener(cancel.findViewById(R.id.cancelBtn));


//            res = itemView.findViewById(R.id.res_txt);
//            image = itemView.findViewById(R.id.img1);

        }


    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
