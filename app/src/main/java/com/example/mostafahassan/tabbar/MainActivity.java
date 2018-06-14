package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText UserNumber;
    Button Confirm;
    String adminNumber = "1234";

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        UserNumber = (EditText) findViewById( R.id.Mobile_Number );
        Confirm = (Button) findViewById( R.id.Confirm_Button );

        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Users" );

        Confirm.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incNum_val = UserNumber.getText().toString();

                if (!TextUtils.isEmpty( incNum_val )) {
                    checkInc();
                } else {
                    if (incNum_val.isEmpty()) {
                        UserNumber.setError( "Please Enter your UserNumber" );
                    }
                }
            }
        } );
    }

    void checkInc() {

        final String Number_val = UserNumber.getText().toString().trim();

        final Query firebaseSearchQuery = databaseReference.orderByChild( "UserPhone" ).startAt( Number_val ).endAt( Number_val + "\uf8ff" );

        firebaseSearchQuery.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    if (Number_val.equals( adminNumber )) {
                        Intent singleBlogIntent = new Intent( MainActivity.this, TabBar.class );
                        startActivity( singleBlogIntent );
                        finish();
                    } else {
                        startPosting();
                        finish();
                    }
                } else {
                    UserNumber.setError( "This Number Not Found" );
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        } );
    }

    private void startPosting() {

        Intent singleBlogIntent = new Intent( MainActivity.this, ViewIncubators.class );
        startActivity( singleBlogIntent );
        finish();
    }
}
