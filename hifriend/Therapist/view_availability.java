package com.hifriend.Therapist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.hifriend.parents.consult_class;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.webrtc.ContextUtils.getApplicationContext;


public class view_availability extends Fragment {
    RecyclerView recyclerView;
    DatabaseReference theraRef, slotRef;
    String _NAME;
    FirebaseAuth mAuth;

    private String _ID;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_availab_rec,container,false);
       // setContentView(R.layout.view_availab_rec);

        recyclerView = (RecyclerView) view.findViewById(R.id.thera_viewAvailab_recView);
        mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getCurrentUser().getUid();
        theraRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist")
                .child(id);

        _ID = home._NAME;
        slotRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("slots")
        .child(_ID);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();



        theraRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                _NAME = snapshot.child("full_name").getValue().toString();

                mAuth = FirebaseAuth.getInstance();
                String id = mAuth.getCurrentUser().getUid();
                Query firebaseSearchQuery = slotRef.orderByChild("thera_id").equalTo(id);
                FirebaseRecyclerOptions<slotsClass> options = new FirebaseRecyclerOptions.Builder<slotsClass>().setQuery(firebaseSearchQuery, slotsClass.class).build();


//        Query firebaseSearchQuery = consultRef.orderByChild("therapist_Name").equalTo(_NAME);
//        FirebaseRecyclerOptions<consult_class> options = new FirebaseRecyclerOptions.Builder<consult_class>().setQuery(firebaseSearchQuery,consult_class.class).build();

                FirebaseRecyclerAdapter<slotsClass, view_availability.AvailabViewHolder> adapter = new FirebaseRecyclerAdapter<slotsClass, AvailabViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(AvailabViewHolder holder, int position, slotsClass model) {

                        holder.date.setText(model.getDate());
                        holder.time.setText(model.getTime());

//                        holder.parent_Name.setText(model.getParent_name());
//                        holder.parent_Email.setText(model.getParent_Email());
//                        holder.date.setText(model.getConsult_Date());
//                        holder.time.setText(model.getConsult_time());
                        holder.cancel.setClickable(true);

                        holder.cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Delete Slot");
                                builder.setMessage("Cancel...?");

                                builder.setPositiveButton("Yes", ((dialog, which) -> {
                                    slotRef.child(getRef(position).getKey()).removeValue();
                                    Toast.makeText(getContext(), "Slot deleted", Toast.LENGTH_LONG).show();


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
                    public view_availability.AvailabViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_availab_layout, parent, false);
                        view_availability.AvailabViewHolder ViewHolder = new view_availability.AvailabViewHolder(view);

                        return ViewHolder;
                    }
                };

                recyclerView.setAdapter(adapter);
                adapter.startListening();



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//
//
//        });


    }

    static class AvailabViewHolder extends RecyclerView.ViewHolder {
        TextView  date, time, cancel;
        CircleImageView image;

        public AvailabViewHolder(View itemView) {
            super(itemView);

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

