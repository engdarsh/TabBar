package com.example.mostafahassan.tabbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AddUser extends Fragment {
    private EditText UserName,UserPhone ;
    private Button submitBtn;
    private DatabaseReference databaseReference;

    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_add_user, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        UserName = (EditText) rootView.findViewById(R.id.name_field);
        UserPhone = (EditText) rootView.findViewById(R.id.phone_field);

        submitBtn = (Button) rootView.findViewById(R.id.submitButton);

        final FragmentActivity c = getActivity();
        progress = new ProgressDialog(c);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserName_val = UserName.getText().toString();
                String UserPhone_val = UserPhone.getText().toString();

                if (!TextUtils.isEmpty(UserName_val) && !TextUtils.isEmpty(UserPhone_val)) {
                    checkInc();
                } else {
                    if (UserName_val.isEmpty()) {
                        UserName.setError("Invalid UserName");
                    }
                    if (UserPhone_val.isEmpty()) {
                        UserPhone.setError("Invalid UserPhone");
                    }
                }
            }
        });

        return rootView;
    }
    private void startPosting() {
        progress.setMessage("Uploading Data ...");

        final String UserName_val = UserName.getText().toString().trim();
        final String UserPhone_val = UserPhone.getText().toString().trim();

        progress.show();
        final DatabaseReference newPostRef = databaseReference.child(UserPhone_val);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                newPostRef.child("UserName").setValue(UserName_val);
                newPostRef.child("UserPhone").setValue(UserPhone_val);

                Intent singleBlogIntent = new Intent(getActivity().getApplication(), TabBar.class);
                startActivity(singleBlogIntent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    void checkInc() {

        final String UserPhone_val = UserPhone.getText().toString().trim();

        final Query firebaseSearchQuery = databaseReference.orderByChild("UserPhone").startAt(UserPhone_val).endAt(UserPhone_val + "\uf8ff");

        firebaseSearchQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    UserPhone.setError("This UserPhone is Exists");
                    return;
                } else {
                    startPosting();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
