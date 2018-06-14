package com.example.mostafahassan.tabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewIncubators extends AppCompatActivity{
    private RecyclerView blogList;
    private DatabaseReference databaseRef;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incubators);

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Incubators");

        databaseRef.keepSynced(true);
        blogList = (RecyclerView) findViewById(R.id.blogList);
        blogList.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        blogList.setLayoutManager(layoutManager);

    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, IncubatorsTable.BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, IncubatorsTable.BlogViewHolder>(Blog.class, R.layout.blog_row, IncubatorsTable.BlogViewHolder.class, databaseRef) {
            @Override
            protected void populateViewHolder(IncubatorsTable.BlogViewHolder viewHolder, Blog model, int position) {

                final String postKey = getRef(position).getKey();

                viewHolder.setTitle(model.getIncNum());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent singleBlogIntent = new Intent(ViewIncubators.this, BlogSingleActivity.class);
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

    } @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.Log_Out) {
            Intent intent = new Intent(ViewIncubators.this, MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
