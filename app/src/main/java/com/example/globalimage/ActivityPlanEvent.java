package com.example.globalimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActivityPlanEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_event);
        getSupportActionBar().setTitle("Plan Event");
    }
}
