package com.example.mostafahassan.tabbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ViewData extends AppCompatActivity {

    private DatabaseReference mDatabase;
    public TextView Temperature, BodyTemperature, Humidity, Heart, incNum,
            childName, date, weight, gender, idNum, birthDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);

        Temperature = (TextView) findViewById(R.id.Temperature);
        BodyTemperature = (TextView) findViewById(R.id.BodyTemperature);
        Humidity = (TextView) findViewById(R.id.Humidity);
        Heart = (TextView) findViewById(R.id.Heart);

        incNum = (TextView) findViewById(R.id.IncNum_ViewData);
        childName = (TextView) findViewById(R.id.ChildName_ViewData);
        date = (TextView) findViewById(R.id.Date_ViewData);
        weight = (TextView) findViewById(R.id.Weight_ViewData);
        gender = (TextView) findViewById(R.id.Gender_ViewData);
        idNum = (TextView) findViewById(R.id.IdNum_ViewData);
        birthDay = (TextView) findViewById(R.id.BirthDay_ViewData);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Heart");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HeartValue = dataSnapshot.getValue().toString();
                Heart.setText(HeartValue);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Temperature");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String TemperatureValue = dataSnapshot.getValue().toString();
                Temperature.setText(TemperatureValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("BodyTemperature");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String BodyTemperatureValue = dataSnapshot.getValue().toString();
                BodyTemperature.setText(BodyTemperatureValue);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Humidity");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                Humidity.setText(HumidityValue);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("Weight");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                weight.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("Gender");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                gender.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("Date");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                date.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("ChildName");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                childName.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("IncNum");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                incNum.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("IdNum");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                idNum.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Incubators").child("1").child("BirthDay");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HumidityValue = dataSnapshot.getValue().toString();
                birthDay.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}