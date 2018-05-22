package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewData extends AppCompatActivity {

    private DatabaseReference mDatabase;
    public TextView Temperature,BodyTemperature,Humidity,Heart
            ,IncNum, ChildName, Date, Weight, Gender, IdNum;
    Button ViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_data);

        Temperature = (TextView)findViewById(R.id.Temperature);
        BodyTemperature = (TextView)findViewById(R.id.BodyTemperature);
        Humidity = (TextView)findViewById(R.id.Humidity);
        Heart = (TextView)findViewById(R.id.Heart);

        IncNum = (TextView) findViewById(R.id.IncNum_UserInc);
        ChildName = (TextView) findViewById(R.id.ChildName_UserInc);
        Date = (TextView) findViewById(R.id.Date_UserInc);
        Weight = (TextView) findViewById(R.id.Weight_UserInc);
        Gender = (TextView) findViewById(R.id.Gender_UserInc);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Heart");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HeartValue =dataSnapshot.getValue().toString();
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
                String TemperatureValue =dataSnapshot.getValue().toString();
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
                String BodyTemperatureValue =dataSnapshot.getValue().toString();
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
                String HumidityValue =dataSnapshot.getValue().toString();
                Humidity.setText(HumidityValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("ChildName");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Child =dataSnapshot.getValue().toString();
                ChildName.setText(Child);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Gender");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String getdata =dataSnapshot.getValue().toString();
                Gender.setText(getdata);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("IncNum");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HeartValue =dataSnapshot.getValue().toString();
                IncNum.setText(HeartValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Weight");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HeartValue =dataSnapshot.getValue().toString();
                Weight.setText(HeartValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Date");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HeartValue =dataSnapshot.getValue().toString();
                Date.setText(HeartValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("IdNum");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String HeartValue =dataSnapshot.getValue().toString();
                IdNum.setText(HeartValue);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ViewDetails = (Button) findViewById(R.id.ViewDetail);

        ViewDetails.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewData.this, ViewDetails.class);
                startActivity(intent);
            }
        });

    }
}