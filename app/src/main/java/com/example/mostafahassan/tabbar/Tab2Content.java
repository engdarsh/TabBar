package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Tab2Content extends Fragment implements View.OnClickListener {
    public EditText childName, date, weight, gender, idNum, BirthDay;
    public Button AddUser;
    DatabaseReference databaseReferance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add_baby, container, false);

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
        BirthDay = (EditText) rootView.findViewById(R.id.BirthDay_baby);
        BirthDay.setOnClickListener(this);

//        AddUser = (Button) rootView.findViewById(R.id.Add_user);
//        AddUser.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Add_user:
                databaseReferance.child("IdNum").setValue(idNum.getText().toString());
                databaseReferance.child("Date").setValue(date.getText().toString());
                databaseReferance.child("Weight").setValue(weight.getText().toString());
                databaseReferance.child("Gender").setValue(gender.getText().toString());
                databaseReferance.child("ChildName").setValue(childName.getText().toString());
                databaseReferance.child("BirthDay").setValue(BirthDay.getText().toString());
                break;
        }
    }
}
