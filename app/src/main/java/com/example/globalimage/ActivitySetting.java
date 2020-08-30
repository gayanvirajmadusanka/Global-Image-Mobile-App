package com.example.globalimage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivitySetting extends AppCompatActivity {

    Button facebookButton;
    Button youtubeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getSupportActionBar().setTitle("Setting");


 /////////////////////////////////////////////////////////////////////////
        facebookButton = (Button)findViewById(R.id.btnfacebook);
        youtubeButton = (Button)findViewById(R.id.btnyoutube);

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent facebookIntent = openFacebook(ActivitySetting.this);
                startActivity(facebookIntent);

            }
        });
  /////////////////////////////////////////////////////////////////////////////

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youtubeIntent = openYouTube(ActivitySetting.this);
                startActivity(youtubeIntent);

            }
        });


    }

    /////////////////////////////////////////////////////////////////////////////
    public static Intent openFacebook(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.facebook.com/",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/"));
        }

    }
    ////////////////////////////////////////////////////////////////////////////////////////

    public static Intent openYouTube(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.youtube.com/channel/UC9nFtl-7UCkb2lODweqjFEg",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC9nFtl-7UCkb2lODweqjFEg"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC9nFtl-7UCkb2lODweqjFEg"));
        }

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
