package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IncubatorsTable extends Fragment {

    private RecyclerView blogList;
    private DatabaseReference databaseRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_incubators, container, false);


        databaseRef = FirebaseDatabase.getInstance().getReference().child("Incubators");

        databaseRef.keepSynced(true);
        blogList = (RecyclerView) rootView.findViewById(R.id.blogList);
        blogList.setHasFixedSize(true);

        final FragmentActivity c = getActivity();

        LinearLayoutManager layoutManager = new LinearLayoutManager(c);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        blogList.setLayoutManager(layoutManager);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(Blog.class, R.layout.blog_row, BlogViewHolder.class, databaseRef) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                final String postKey = getRef(position).getKey();

                viewHolder.setTitle(model.getIncNum());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(getActivity().getApplication(), BlogSingleActivity.class);
                        singleBlogIntent.putExtra("postKey", postKey);
                        startActivity(singleBlogIntent);
                    }
                });
            }
        };

        blogList.setAdapter(firebaseRecyclerAdapter);
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View view;

        public BlogViewHolder(View itemView) {
            super(itemView);
            view = itemView;

        }

        void setTitle(String Incubator) {
            TextView postDescription = (TextView) view.findViewById(R.id.IncNum);
            postDescription.setText(Incubator);
        }

    }
}
