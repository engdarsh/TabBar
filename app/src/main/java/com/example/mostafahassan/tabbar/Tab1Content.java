package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab1Content extends Fragment implements View.OnClickListener {
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        TextView incubator1 = (TextView) findViewById(R.id.incubator1);
//
//        incubator1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Tab1Content.this, ViewData.class);
//                startActivity(intent);
//            }
//        });
//        TextView incubator2 = (TextView) findViewById(R.id.incubator2);
//
//        incubator2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Tab1Content.this, ViewData.class);
//                startActivity(intent);
//            }
//        });
//        TextView incubator3 = (TextView) findViewById(R.id.incubator3);
//
//        incubator3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Tab1Content.this, ViewData.class);
//                startActivity(intent);
//            }
//        });
//        TextView incubator4 = (TextView) findViewById(R.id.incubator4);
//
//        incubator4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent = new Intent(Tab1Content.this, ViewData.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//        @Override
//        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        TextView textView = (TextView) getView().findViewById(R.id.incubator1);
//        // or  (ImageView) view.findViewById(R.id.foo);
//            boolean x=textView.setOnClickListener(this);
//        if(){
//        Intent intent = new Intent(getActivity().getApplication(), ViewData.class);
//        startActivity(intent);
//        }
//        }

//
//    public void goToAttract(View v)
//    {
//        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ImageView imageView = (ImageView) getView().findViewById(R.id.incubator1);
//        Intent intent = new Intent(getActivity().getApplication(), ViewData.class);
//        startActivity(intent);
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_incubators, container, false);
        return rootView;

    }

    @Override
    public void onClick(View v) {

    }
}
