package com.example.globalimage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityPackage extends AppCompatActivity {

    RecyclerView mRecyclearView;
    FirebaseDatabase mfirebaseDatabase;
    DatabaseReference mRef;

    //ProgressBar pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);


        //Action bar and title
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Packages");

        //RecyclerView
        mRecyclearView = findViewById(R.id.recyclearViewPackage);
        mRecyclearView.setHasFixedSize(true);

        //progressBar = (ProgressBar) findViewById(R.id.progressBarPackage);

        //set liner layout
        mRecyclearView.setLayoutManager(new LinearLayoutManager(this));

        //send Query to firebaseDatabase
        mfirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mfirebaseDatabase.getReference("Data");

    }

    //load daa into recycler view onStart


/////////////////////////////////////////////////////////////////////////////////////////////////////
/*    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ColorSpace.Model, RecyclerView.ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ColorSpace.Model, RecyclerView.ViewHolder>(
                        ModelPackage.class,
                        R.layout.row_package,
                        ViewHolderPackage.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(RecyclerView.ViewHolder viewHolder, ColorSpace.Model model, int position) {



                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getStatus(), model.getImage());

                    }
                };

        mRecyclearView.setAdapter(firebaseRecyclerAdapter);
    }*/
/////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    protected void onStart() {

        final ProgressDialog pd = new ProgressDialog(ActivityPackage.this);
        pd.setMessage("Loading...");
        pd.show();

        super.onStart();
        FirebaseRecyclerAdapter<ModelPackage, ViewHolderPackage> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ModelPackage, ViewHolderPackage>(
                        ModelPackage.class,
                        R.layout.row_package,
                        ViewHolderPackage.class,
                        mRef

                ) {
                    @Override
                    protected void populateViewHolder(ViewHolderPackage viewHolder, ModelPackage model, int position) {

                        //progressBar.setVisibility(View.VISIBLE);

                        pd.cancel();

                        viewHolder.setDetails(getApplicationContext(), model.getTitle(), model.getDescription(), model.getImage());

                    }
                };

        mRecyclearView.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main2,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_home){

            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
