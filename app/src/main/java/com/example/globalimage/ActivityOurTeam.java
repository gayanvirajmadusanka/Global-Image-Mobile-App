package com.example.globalimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityOurTeam extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);
        getSupportActionBar().setTitle("Our Team");
    }
}
