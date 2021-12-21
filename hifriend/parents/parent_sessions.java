package com.hifriend.parents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class parent_sessions extends Fragment {

    RecyclerView recyclerView;
    private session_adapter adapter;
    private List<consult_class> consult_classList;
    public String _name;
    FirebaseAuth auth;
    DatabaseReference parentRef,roomRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().hide();

        recyclerView = view.findViewById(R.id.session_rec_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        consult_classList = new ArrayList<>();
        adapter = new session_adapter(getContext(),consult_classList);
        recyclerView.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();

        Query query = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
                .orderByChild("parent_id").equalTo(id);

        query.addListenerForSingleValueEvent(valueEventListener);

        return view;

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
            consult_classList.clear();

            if(snapshot.exists()){
                for(DataSnapshot ds: snapshot.getChildren()){
                    consult_class consultClass = ds.getValue(consult_class.class);
                    consult_classList.add(consultClass);

                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };


    public class session_adapter extends RecyclerView.Adapter<session_adapter.sessionViewHolder> {

        private Context mCtx;
        private List<consult_class> consultList;

        public session_adapter(Context mCtx, List<consult_class> consultList){
            this.mCtx=mCtx;
            this.consultList=consultList;


        }


        @NonNull
        @NotNull
        @Override
        public sessionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.sessions_layout,parent,false);
            Button startVid;
            startVid = (Button) view.findViewById(R.id.startVidBtn);
            startVid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(),vid_call.class));
                }
            });




            return new sessionViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull sessionViewHolder holder, int position) {
            consult_class consult_class = consultList.get(position);
            holder.thera_Name.setText(consult_class.getTherapist_Name());
            //holder.thera_Email.setText(consult_class.getTherapist_Email());
            holder.datentime.setText("Session on " + consult_class.getConsult_Date() + "at " + consult_class.getConsult_time());

            Glide.with(holder.imageSet.getContext()).load(consult_class.getThera_image()).into(holder.imageSet);

            auth = FirebaseAuth.getInstance();
            String id = auth.getCurrentUser().getUid();

            roomRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("room names");

            String key = roomRef.child("room names").push().getKey();

            roomRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            String parentID = ds.child("parent_id").getValue().toString();
                            if(parentID.equals(consult_class.getParent_id())){
                                String roomName = ds.child("room_name").getValue().toString();
                                holder.room_txt.setText("Room name: " + roomName);

                            } else {
                               holder.room_txt.setText("Room name not set yet");
                           }

                        }
//
                    }

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            });
//            holder.date.setText(consult_class.getConsult_Date());
//            holder.time.setText(consult_class.getConsult_time()
        }

        @Override
        public int getItemCount() {
            return consultList.size();
        }

        class sessionViewHolder extends RecyclerView.ViewHolder {
            TextView thera_Name, thera_Email, datentime,room_txt;
            CircleImageView imageSet;

            public sessionViewHolder(View itemView) {
                super(itemView);

                thera_Name = itemView.findViewById(R.id.name_text);
                //thera_Email = itemView.findViewById(R.id.email_text);
                datentime = itemView.findViewById(R.id.date_n_time);
                imageSet = itemView.findViewById(R.id.image_set);
                room_txt = itemView.findViewById(R.id.room_name);
//                date = itemView.findViewById(R.id.date_txt);
//
//                time = itemView.findViewById(R.id.time_txt);


            }
        }
    }

    ValueEventListener valueEventListenerr = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


        }

        @Override
        public void onCancelled(@NonNull @NotNull DatabaseError error) {

        }
    };

}
