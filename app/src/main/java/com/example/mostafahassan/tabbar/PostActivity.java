package com.example.mostafahassan.tabbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class PostActivity extends AppCompatActivity {

    private EditText childName, weight, idNum, incNum;
    private TextView Date, birthDay;
    private String gender;
    private Button submitBtn;
    private DatabaseReference databaseReference;
    private ProgressDialog progress;
    private DatePickerDialog.OnDateSetListener mDate, mBirthDay;

    private Spinner spinner;
    private static final String[] paths = {"Male", "Female"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_post );

        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Incubators" );

        incNum = (EditText) findViewById( R.id.IncNum_Edite );
        childName = (EditText) findViewById( R.id.ChildNam_Edite );
        weight = (EditText) findViewById( R.id.Weight_Edite );
        idNum = (EditText) findViewById( R.id.IdNum_Edite );

        Date = (TextView) findViewById( R.id.Date_Edite );
        birthDay = (TextView) findViewById( R.id.BirthDay_Edite );
        submitBtn = (Button) findViewById( R.id.submitButton );

        progress = new ProgressDialog( this );

        Date.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get( Calendar.YEAR );
                int month = cal.get( Calendar.MONTH );
                int day = cal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog dialog = new DatePickerDialog( PostActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDate, year, day, month );
                dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
                dialog.show();
            }
        } );
        mDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                Date.setText( date );
            }
        };

        birthDay.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get( Calendar.YEAR );
                int month = cal.get( Calendar.MONTH );
                int day = cal.get( Calendar.DAY_OF_MONTH );

                DatePickerDialog dialog = new DatePickerDialog( PostActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mBirthDay, year, day, month );
                dialog.getWindow().setBackgroundDrawable( new ColorDrawable( Color.TRANSPARENT ) );
                dialog.show();
            }
        } );
        mBirthDay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                birthDay.setText( date );
            }
        };

        spinner = (Spinner) findViewById( R.id.spinner );
        ArrayAdapter <String> adapter = new ArrayAdapter <String>( PostActivity.this, android.R.layout.simple_spinner_item, paths );

        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        spinner.setAdapter( adapter );

        spinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        gender = ("Male");
                        break;

                    case 1:
                        gender = ("Female");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView <?> parent) {
                // TODO Auto-generated method stub
            }
        } );

        submitBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                checkInc();
                startPosting();
            }
        } );

    }

//    void checkInc() {
//
//        final String incNum_val = incNum.getText().toString().trim();
//
//        final Query firebaseSearchQuery = databaseReference.orderByChild("IncNum").startAt(incNum_val).endAt(incNum_val + "\uf8ff");
//
//        firebaseSearchQuery.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//
//                    String idNum_val = (String) dataSnapshot.child(incNum_val).child("IdNum").getValue();
//
//                    if (idNum_val.equals(" ")) {
//                        startPosting();
//                    } else {
//                        incNum.setError("This Inc Not Empity");
//                        Toast.makeText(PostActivity.this, "This Inc Not Empity", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                } else {
//                    incNum.setError("This Inc Not Found");
//                    Toast.makeText(PostActivity.this, "This Inc Not Found", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }

    private void startPosting() {
        progress.setMessage( "Uploading Data ..." );

        final String incNum_val = incNum.getText().toString().trim();
        final String childName_val = childName.getText().toString().trim();
        final String date_val = Date.getText().toString().trim();
        final String weight_val = weight.getText().toString().trim();
        final String gender_val = gender.trim();
        final String idNum_val = idNum.getText().toString().trim();
        final String birthDay_val = birthDay.getText().toString().trim();

        if (!TextUtils.isEmpty( incNum_val ) && !TextUtils.isEmpty( childName_val ) && !TextUtils.isEmpty( date_val ) && !TextUtils.isEmpty( weight_val ) && !TextUtils.isEmpty( gender_val ) && !TextUtils.isEmpty( idNum_val ) && !TextUtils.isEmpty( birthDay_val )) {
            progress.show();

            final DatabaseReference newPostRef = databaseReference.child( incNum_val );

            databaseReference.addValueEventListener( new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    newPostRef.child( "IncNum" ).setValue( incNum_val );
                    newPostRef.child( "ChildName" ).setValue( childName_val );
                    newPostRef.child( "Date" ).setValue( date_val );
                    newPostRef.child( "Weight" ).setValue( weight_val );
                    newPostRef.child( "Gender" ).setValue( gender_val );
                    newPostRef.child( "IdNum" ).setValue( idNum_val );
                    newPostRef.child( "BirthDay" ).setValue( birthDay_val );

                    startActivity( new Intent( PostActivity.this, TabBar.class ) );
                    finish();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            } );

        } else {
            if (incNum_val.isEmpty()) {
                incNum.setError( "Invalid IncNum" );
            }
            if (childName_val.isEmpty()) {
                childName.setError( "Invalid Name" );
            }
            if (date_val.isEmpty()) {
                Date.setError( "Invalid date" );
            }
            if (weight_val.isEmpty()) {
                weight.setError( "Invalid weight" );
            }
            if (idNum_val.isEmpty()) {
                idNum.setError( "Invalid IdNum" );
            }
            if (birthDay_val.isEmpty()) {
                birthDay.setError( "Invalid BirthDay" );
            }
        }

    }
}
