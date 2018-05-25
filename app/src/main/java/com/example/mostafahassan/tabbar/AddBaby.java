package com.example.mostafahassan.tabbar;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

import com.google.firebase.database.FirebaseDatabase;

public class AddBaby extends Fragment implements View.OnClickListener {
    public EditText childName, date, weight, gender, idNum, birthDay, incNum;
    public Button AddBaby;

    public ProgressBar mRegisterProgress;
    public DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add_baby, container, false);

        incNum = (EditText) rootView.findViewById(R.id.IncNum_baby);
        incNum.setOnClickListener(this);
        childName = (EditText) rootView.findViewById(R.id.ChildNam_baby);
        childName.setOnClickListener(this);
        date = (EditText) rootView.findViewById(R.id.Date_baby);
        date.setOnClickListener(this);
        weight = (EditText) rootView.findViewById(R.id.Weight_baby);
        weight.setOnClickListener(this);
        gender = (EditText) rootView.findViewById(R.id.Gender_baby);
        gender.setOnClickListener(this);
        idNum = (EditText) rootView.findViewById(R.id.IdNum_baby);
        idNum.setOnClickListener(this);
        birthDay = (EditText) rootView.findViewById(R.id.BirthDay_baby);
        birthDay.setOnClickListener(this);
        mRegisterProgress = (ProgressBar) rootView.findViewById(R.id.registerProgress);
        mRegisterProgress.setOnClickListener(this);
        AddBaby= (Button) rootView.findViewById(R.id.Add_Baby);
        AddBaby.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.Add_Baby:
                // take the insert data
                final String ChildName = childName.getText().toString().trim();
                final String Weight = weight.getText().toString().trim();
                final String Gender = gender.getText().toString().trim();
                final String Date = date.getText().toString().trim();
                final String IncNum = incNum.getText().toString().trim();
                final String BirthDay = birthDay.getText().toString().trim();
                final String IdNum = idNum.getText().toString().trim();

                mRegisterProgress.setVisibility(View.VISIBLE);

                // validate if the data is empty or not
                if (ChildName.isEmpty() || Weight.isEmpty() || Gender.isEmpty() || Date.isEmpty()
                        || IncNum.isEmpty() || BirthDay.isEmpty()|| IdNum.isEmpty() ) {

                    mRegisterProgress.setVisibility(View.INVISIBLE);

                    if (ChildName.isEmpty()) {
                        childName.setError("Invalid Name");
                    }
                    if (Weight.isEmpty()) {
                        weight.setError("Invalid weight");
                    }
                    if (Gender.isEmpty()) {
                        gender.setError("Invalid gender");
                    }
                    if (Date.isEmpty()) {
                        date.setError("Invalid date");
                    }
                    if (IncNum.isEmpty()) {
                        incNum.setError("Invalid IncNum");
                    }
                    if (BirthDay.isEmpty()) {
                        birthDay.setError("Invalid BirthDay");
                    }
                    if (IdNum.isEmpty()) {
                        idNum.setError("Invalid IdNum");
                    }

                } else {

                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child(IncNum);
                    // push data one by one
                    DatabaseReference childRoot = mDatabase.push();
                    // hashmap used for store data in Realtime Data base
                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("Weight", Weight);
                    map.put("Gender", Gender);
                    map.put("Date", Date);
                    map.put("ChildName", ChildName);
                    map.put("IncNum", IncNum);
                    map.put("BirthDay", BirthDay);
                    map.put("IdNum", IdNum);

                    childRoot.updateChildren(map);// save data from map in mDatabase

                    mDatabase.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {

                                mRegisterProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), "Done", Toast.LENGTH_SHORT).show();

                            } else {
                                mRegisterProgress.setVisibility(View.INVISIBLE);
                                Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                break;

        }
    }
}
