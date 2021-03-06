package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class RemoveUser extends AppCompatActivity {

    private EditText DeletedNumber;
    private Button submitBtn;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_remove_user );

        databaseReference = FirebaseDatabase.getInstance().getReference().child( "Users" );

        DeletedNumber = (EditText) findViewById( R.id.deletedNumber );

        submitBtn = (Button) findViewById( R.id.deleteBtn );

        submitBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String incNum_val = DeletedNumber.getText().toString();

                if (!TextUtils.isEmpty( incNum_val )) {
                    checkInc();
                } else {
                    if (incNum_val.isEmpty()) {
                        DeletedNumber.setError( "Invalid IncNum" );
                    }
                }
            }
        } );

    }

    private void startPosting() {
        final String incNum_val = DeletedNumber.getText().toString().trim();

        databaseReference.child( incNum_val ).removeValue();
        Toast.makeText( RemoveUser.this, "Removed", Toast.LENGTH_SHORT ).show();
        Intent singleBlogIntent = new Intent( RemoveUser.this, TabBar.class );
        startActivity( singleBlogIntent );
        finish();
    }

    void checkInc() {

        final String incNum_val = DeletedNumber.getText().toString().trim();

        final Query firebaseSearchQuery = databaseReference.orderByChild( "UserPhone" ).startAt( incNum_val ).endAt( incNum_val + "\uf8ff" );

        firebaseSearchQuery.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    startPosting();
                    finish();
                } else {
                    DeletedNumber.setError( "This Inc Not Found" );
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        } );
    }
}
