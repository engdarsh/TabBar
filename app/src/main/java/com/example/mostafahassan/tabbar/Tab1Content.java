package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Tab1Content extends Fragment implements View.OnClickListener {

    public TextView inc1,inc2,inc3,inc4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_incubators, container, false);

        inc1 = (TextView) rootView.findViewById(R.id.incubator1);
        inc1.setOnClickListener(this);
        inc2 = (TextView) rootView.findViewById(R.id.incubator2);
        inc2.setOnClickListener(this);
        inc3 = (TextView) rootView.findViewById(R.id.incubator3);
        inc3.setOnClickListener(this);
        inc4 = (TextView) rootView.findViewById(R.id.incubator4);
        inc4.setOnClickListener(this);

        return rootView;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.incubator1:
                Intent intent1 = new Intent(getActivity().getApplication(), ViewData.class);
                startActivity(intent1);
                break;
            case R.id.incubator2:
                Intent intent2 = new Intent(getActivity().getApplication(), ViewData.class);
                startActivity(intent2);
                break;
            case R.id.incubator3:
                Intent intent3 = new Intent(getActivity().getApplication(), ViewData.class);
                startActivity(intent3);
                break;
            case R.id.incubator4:
                Intent intent4 = new Intent(getActivity().getApplication(), ViewData.class);
                startActivity(intent4);
                break;
        }
    }

}
