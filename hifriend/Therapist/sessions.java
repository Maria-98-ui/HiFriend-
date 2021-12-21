package com.hifriend.Therapist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.installations.InstallationTokenResult;
import com.hifriend.R;
import com.hifriend.SendNotifPack.SendNotif;
import com.hifriend.SendNotifPack.Token;
import com.hifriend.parents.consult_class;
import com.hifriend.parents.parent_sessions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.hifriend.Therapist.setRoomNumber.roomName;

public class sessions extends Fragment {
    RecyclerView recyclerView;
    private session_thera_adapter adapter;
    private List<consult_class> consult_classList;
    public String _name;
    FirebaseAuth auth;
    DatabaseReference theraRef,refRoom;
    public static ValueEventListener valueEventListener;
    static String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_session_thera, container, false);
        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
        compatActivity.getSupportActionBar().hide();

        recyclerView = view.findViewById(R.id.thera_session_recView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        consult_classList = new ArrayList<>();
        adapter = new session_thera_adapter(getContext(),consult_classList);
        recyclerView.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        String id = auth.getCurrentUser().getUid();


        theraRef = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist").child(id);
        theraRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                _name = snapshot.child("full_name").getValue().toString();

                Query query = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
                        .orderByChild("therapist_Name").equalTo(_name);

                query.addListenerForSingleValueEvent(valueEventListenerr);
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        return view;
    }

    ValueEventListener valueEventListenerr = new ValueEventListener() {
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


    public class session_thera_adapter extends RecyclerView.Adapter<session_thera_adapter.sessionTheraViewHolder> {

        private Context mCtx;
        private List<consult_class> consultList;

        public session_thera_adapter(Context mCtx, List<consult_class> consultList){
            this.mCtx=mCtx;
            this.consultList=consultList;

        }
        @NonNull
        @NotNull
        @Override
        public sessionTheraViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mCtx).inflate(R.layout.sessions_layout_thera,parent,false);

            Button send,video;
            send = (Button) view.findViewById(R.id.setRoomVidBtn);
            video = (Button) view.findViewById(R.id.startVidBtn);

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(),setRoomNumber.class));

                }
            });

            video.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getContext(),video_call.class));
                }
            });



            return new sessionTheraViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull @NotNull session_thera_adapter.sessionTheraViewHolder holder, int position) {
            consult_class consult_class = consultList.get(position);
            holder.parent_name.setText(consult_class.getParent_name());
            //holder.thera_Email.setText(consult_class.getTherapist_Email());
            holder.datentime.setText("Session on " + consult_class.getConsult_Date() + "at " + consult_class.getConsult_time());

            Glide.with(holder.imageSet.getContext()).load(consult_class.getParent_image()).into(holder.imageSet);
            token = consult_class.getParent_token();

//            Intent i = new Intent(getActivity().getBaseContext(),setRoomNumber.class);
//            i.putExtra("TOKEN",token);
//            getActivity().startActivity(i);




            valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {



//                    refRoom = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("room names").push();
//                    refRoom.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                            refRoom.child("room_name").setValue(room_name_txt).toString();
//                            refRoom.child("parent_id").setValue(consult_class.getParent_id()).toString();
//
//
//                        }
//
//                        @Override
//                        public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//                        }
//                    });

                }

                @Override
                public void onCancelled(@NonNull @NotNull DatabaseError error) {

                }
            };


        }

        @Override
        public int getItemCount() {
            return consultList.size();
        }

        class sessionTheraViewHolder extends RecyclerView.ViewHolder {
            TextView parent_name, thera_Email, datentime;
            CircleImageView imageSet;

            public sessionTheraViewHolder(View itemView) {
                super(itemView);

                parent_name = itemView.findViewById(R.id.name_text);
                //thera_Email = itemView.findViewById(R.id.email_text);
                datentime = itemView.findViewById(R.id.dateNtime);
                imageSet = itemView.findViewById(R.id.image_set);
//                date = itemView.findViewById(R.id.date_txt);
//
//                time = itemView.findViewById(R.id.time_txt);


            }
        }

    }




}
