package com.hifriend.parents;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hifriend.R;
import com.hifriend.Therapist.Therapist;
import com.hifriend.Therapist.availability;
import com.hifriend.Therapist.home;
import com.hifriend.Therapist.slots;
import com.hifriend.Therapist.slotsT;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

public class book_consultation extends Fragment {
    String name, email, expertise, phone, image,date,time;
    EditText name_thera, email_thera, expertise_thera, phone_thera, parentName,parentEmail,childName, childDiagnosisAge;
    Toolbar toolbar;
    Button confirm;
    String _DATE,_TIME;
    String _PARENTNAME, _PARENTEMAIL, _CHILDNAME, _CHILDDIAGNOSIS, _IMAGE, _PARENTRES;
    AutoCompleteTextView dateChooser, timeChooser;
    DatabaseReference databaseReference,refSlots, refConsult,refConsult2;
    public static FirebaseAuth auth;
    public static String id;
    private ArrayList<slots> arr = new ArrayList<>();
    private ArrayList<slotsT> arr2 = new ArrayList<>();
    final ArrayList<String> mconsult = new ArrayList<>();

    public book_consultation(){

    }

    public book_consultation(String name, String email, String expertise, String phone, String image,String date,String time){
        this.name=name;
        this.email=email;
        this.expertise=expertise;
        this.phone=phone;
//
        this.image=image;
        this.date=date;
        this.time=time;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_consultation_layout,container,false);
//        AppCompatActivity compatActivity = (AppCompatActivity) getActivity();
//        compatActivity.getSupportActionBar().hide();

        BottomNavigationView navBar = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_nav);
        navBar.setVisibility(View.GONE);


        dateChooser = (AutoCompleteTextView) view.findViewById(R.id.datechooser);
        timeChooser = (AutoCompleteTextView) view.findViewById(R.id.timechooser);

        parentName = view.findViewById(R.id.name_parent);
        parentEmail = view.findViewById(R.id.email_parent);
        childName = view.findViewById(R.id.childname);
        childDiagnosisAge = view.findViewById(R.id.childDiag);
        confirm = view.findViewById(R.id.confirmbtn);

        name_thera = view.findViewById(R.id.name_full_thera);
        name_thera.setText(name);
        email_thera = view.findViewById(R.id.email_thera);
        email_thera.setText(email);
        expertise_thera = view.findViewById(R.id.expertis_thera);
        expertise_thera.setText(expertise);
        phone_thera = view.findViewById(R.id.phonee_thera);
        phone_thera.setText(phone);

        refConsult = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("consulations")
                .push();
        String key = refConsult.push().getKey();

//        arr.add(date);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.countries_option,arr);
//        dateChooser.setAdapter(arrayAdapter);
//
//        arr2.add(time);
//        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(getContext(),R.layout.countries_option,arr2);
//        timeChooser.setAdapter(arrayAdapter2);


//        arr2.add(time);
//
//        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.countries_option,arr);
//
//        //dateChooser.setText(adapter.getItem(0).toString(),false);
//
//        dateChooser.setAdapter(adapter);
//
//        ArrayAdapter adapter2 = new ArrayAdapter(getContext(),R.layout.countries_option,arr2);
//
//        //timeChooser.setText(adapter.getContext().toString());
//        timeChooser.setAdapter(adapter2);





//        String idd = databaseReference.getKey();
//
//        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist").child(idd);
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//
//                for(DataSnapshot ds: snapshot.getChildren()){
//                    if(image.equals(snapshot.child("image").getValue().toString())){
//                        String id = ds.getKey();
//                        String get = ds.child("slots").child(id).child("date").getValue().toString();
//                        arr.add(get);
//
//
//                    }
//
//
//
////                    String id = ds.getKey();
////                    String get = ds.child(date).getValue().toString();
////                    arr.add(Integer.parseInt(get),id);
//
//
//                }
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.countries_option,arr);
//                dateChooser.setAdapter(arrayAdapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });


       // databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist").getRoot();

//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.equals(image)){
////                    _DATE = snapshot.child("slot").child("date").getValue().toString();
////
////                    _TIME = snapshot.child("slot").child("time").getValue().toString();
//
////                    arr.add(_DATE);
////                    arr2.add(_TIME);
//
//                    for(DataSnapshot item: snapshot.getChildren()){
//                        arr.add(item.child("slots").child("date").getValue(String.class));
//
//                    }
//
//                    ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.countries_option,arr);
//                    //dateChooser.setText(adapter.getItem(0).toString(),false);
//                    //dateChooser.setText(adapter.getItem(4).toString());
//                    dateChooser.setText(adapter.getContext().toString());
//                    dateChooser.setAdapter(adapter);
//
//
////                    for(DataSnapshot item: snapshot.child("slot").child("date").getChildren()){
////                        arr.add(String.valueOf(item));
////
////                    }
//
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });





//        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Therapist");
//
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    for(DataSnapshot item: snapshot.getChildren()){
//                        arr.add(item.child(slot).child(date).getValue().toString());
//
//                    }
//
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//
//
//
//
//        });
//
//        ArrayAdapter adapter = new ArrayAdapter(getContext(),R.layout.countries_option, arr);
//        dateChooser.setText(adapter.getItem(0).toString(),false);
//        dateChooser.setAdapter(adapter);




//        String getId = databaseReference.child(getName).push().getKey();
//        Log.i("uid",getId);



//        modelClass_consult m = new modelClass_consult();
//        String getName = m.getFull_name();


//        String getId = databaseReference.child("Andrea Lee").push().getKey();

        refSlots = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").
                getReference("slots");

        String getName = refSlots.getKey();
        refSlots.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                //String txt = name_thera.getText().toString();

                arr.clear();
                arr2.clear();
                String getText = snapshot.getValue().toString();
                if(snapshot.exists()){
//                    if(name.equals(snapshot.child(name).toString())){
                        for(DataSnapshot ds: snapshot.child(name).getChildren()) {
                            //String getName = availability.get_ID;

                            _DATE = Objects.requireNonNull(ds.child("date").getValue()).toString();
                            arr.add(new slots(getName, _DATE));
                            _TIME = Objects.requireNonNull(ds.child("time").getValue()).toString();

                            arr2.add(new slotsT(getName, _TIME));


//                        }

//                            _TIME = Objects.requireNonNull(ds.child("time").getValue()).toString();
//
//                            arr2.add(new slotsT(getName,_TIME));


//                            _TIME = Objects.requireNonNull(ds.child("time").getValue()).toString();
//
//                            arr2.add(new slotsT(getName,_TIME));

                        }

                        ArrayAdapter<slots> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.countries_option,arr);
                        dateChooser.setAdapter(arrayAdapter);
                        //String dateId = dateChooser.getText().toString();
                        ArrayAdapter<slotsT> arrayAdapter2 = new ArrayAdapter<>(getContext(),R.layout.countries_option,arr2);
                        timeChooser.setAdapter(arrayAdapter2);


                }


//                _DATE = Objects.requireNonNull(snapshot.child(getName).child("date").getValue()).toString();
//                    //_DATE = Objects.requireNonNull(ds.child("date").getValue()).toString();
//                    arr.add(_DATE);
//


                    //}

//                _DATE = snapshot.child("date").getValue().toString();
//                arr.add(_DATE);

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }


        });



        auth = FirebaseAuth.getInstance();
        id= auth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent")
                .child(id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                _PARENTNAME = Objects.requireNonNull(snapshot.child("fullname").getValue()).toString();
                parentName.setText(_PARENTNAME);
                _PARENTEMAIL = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
                parentEmail.setText(_PARENTEMAIL);
                _CHILDNAME = Objects.requireNonNull(snapshot.child("childname").getValue()).toString();
                childName.setText(_CHILDNAME);
                _CHILDDIAGNOSIS = Objects.requireNonNull(snapshot.child("diagnosisAge").getValue()).toString();
                childDiagnosisAge.setText(_CHILDDIAGNOSIS);
                _IMAGE = Objects.requireNonNull(snapshot.child("imageUri").getValue()).toString();
                _PARENTRES = Objects.requireNonNull(snapshot.child("residency").getValue()).toString();


            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });


        refConsult2 = FirebaseDatabase.
                getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("consulations");

        refConsult2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if(ds.exists()){
                        mconsult.add(ds.child("consult_Date").getValue().toString());

                    }
                }

                if(mconsult.size()>0){
                     dateChooser.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            String selectedDate = parent.getItemAtPosition(position).toString();

                            if(mconsult.contains(selectedDate)){
                                Toast.makeText(getContext(),"sorry, thera not available",Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



//        Query query = FirebaseDatabase.getInstance().getReference("slots")
//                .orderByChild("image")
//                .equalTo(image);
//
//        query.addListenerForSingleValueEvent(valueEventListener);

//
//

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConsult();
            }
        });




//

       return view;

    }

    public void checkAvailab(){

//        refConsult2 = FirebaseDatabase.
//                getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/")
//                .getReference("consulations");
//
//        refConsult2.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                for(DataSnapshot ds: snapshot.getChildren()){
//                    if(ds.exists()){
//                        mconsult.add(ds.child("consult_Date").getValue().toString());
//
//                    }
//                }
//
//                if(mconsult.size()>0){
//                    String getTextDate = dateChooser.getText().toString();
//                    if(mconsult.contains(getTextDate)){
//                        Toast.makeText(getContext(),"sorry, thera not available",Toast.LENGTH_LONG).show();
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
    }

    public void setConsult(){
        auth = FirebaseAuth.getInstance();
        id = auth.getCurrentUser().getUid();

        String getDate = dateChooser.getText().toString();
        String getTime = timeChooser.getText().toString();



        refConsult.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {


                refConsult.child("parent_id").setValue(id).toString();
                refConsult.child("parent_name").setValue(_PARENTNAME).toString();
                refConsult.child("parent_Email").setValue(_PARENTEMAIL).toString();
                refConsult.child("chils_Name").setValue(_CHILDNAME).toString();
                refConsult.child("child_Diagnosis_Age").setValue(_CHILDDIAGNOSIS).toString();
                refConsult.child("therapist_Name").setValue(name).toString();
                refConsult.child("therapist_Email").setValue(email).toString();
                refConsult.child("consult_Date").setValue(getDate).toString();
                refConsult.child("consult_time").setValue(getTime).toString();
                refConsult.child("parent_image").setValue(_IMAGE).toString();
                refConsult.child("parent_residency").setValue(_PARENTRES).toString();
                refConsult.child("thera_image").setValue(image).toString();

                ////



            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });


        Toast.makeText(getContext(),"Consultation booked successfully",Toast.LENGTH_LONG).show();


    }

//    @Override
//    public void onCreateView(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //View view = inflater.inflate(R.layout.book_consultation_layout,container,false);
//        setContentView(R.layout.book_consultation_layout);
//
//
//
//        //parent
//
//        parentName = findViewById(R.id.name_parent);
//        parentEmail = findViewById(R.id.email_parent);
//        childName = findViewById(R.id.childname);
//        childDiagnosisAge = findViewById(R.id.childDiag);
//
//        name_thera = findViewById(R.id.name_full_thera);
//        name_thera.setText(name);
//        email_thera = findViewById(R.id.email_thera);
//        email_thera.setText(email);
//        expertise_thera = findViewById(R.id.expertis_thera);
//        expertise_thera.setText(expertise);
//        phone_thera = findViewById(R.id.phonee_thera);
//        phone_thera.setText(phone);
//
//        auth = FirebaseAuth.getInstance();
//        String id = auth.getCurrentUser().getUid();
//        databaseReference = FirebaseDatabase.getInstance("https://hifriend-dc6fe-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("parent")
//                .child(id);
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                String name = Objects.requireNonNull(snapshot.child("fullname").getValue()).toString();
//                parentName.setText(name);
//                String email = Objects.requireNonNull(snapshot.child("email").getValue()).toString();
//                parentEmail.setText(email);
//                String childsname = Objects.requireNonNull(snapshot.child("childname").getValue()).toString();
//                childName.setText(childsname);
//                String childsdiag = Objects.requireNonNull(snapshot.child("diagnosisAge").getValue()).toString();
//                childDiagnosisAge.setText(childsdiag);
//
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//
//            }
//        });


//        toolbar = (Toolbar) view.findViewById(R.id.app_bar);
//        view.set
//        view.setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });

//    ValueEventListener valueEventListener = new ValueEventListener() {
//        @Override
//        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//            arr.clear();
//            if(snapshot.exists()){
//                for(DataSnapshot snapshot1 : snapshot.child("slots").getChildren()){
//                    //String take = snapshot1.getValue();
//                    arr.add(take);
//                }
//
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.countries_option,arr);
//                dateChooser.setAdapter(arrayAdapter);
//            }
//
//
//
//        }
//
//        @Override
//        public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//        }
//    };

}


