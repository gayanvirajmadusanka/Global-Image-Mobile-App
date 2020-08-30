package com.example.globalimage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityTutorials extends AppCompatActivity {

    Button btnPhotoshopTutorials;
    Button btnLightroomTutorials;
    Button btnIllusratorTutorials;
    Button btnPremeirproTutorials;
    Button btnAftereffectsTutorials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorials);
        getSupportActionBar().setTitle("Adobe Tutorials");

        btnPhotoshopTutorials = (Button)findViewById(R.id.btnPhotoshopTutorials);
        btnLightroomTutorials = (Button)findViewById(R.id.btnLightroomTutorials);
        btnIllusratorTutorials = (Button)findViewById(R.id.btnIllusratorTutorials);
        btnPremeirproTutorials = (Button)findViewById(R.id.btnPremierTutorials);
        btnAftereffectsTutorials = (Button)findViewById(R.id.btnAfterTutorials);

        btnPhotoshopTutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youtubeIntent = openPhotoshopTutorialsYT(ActivityTutorials.this);
                startActivity(youtubeIntent);

            }
        });

        btnLightroomTutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youtubeIntent = openLightroomTutorialsYT(ActivityTutorials.this);
                startActivity(youtubeIntent);

            }
        });

        btnIllusratorTutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youtubeIntent = openIllusratorTutorialsYT(ActivityTutorials.this);
                startActivity(youtubeIntent);

            }
        });

        btnPremeirproTutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youtubeIntent = openPremierTutorialsYT(ActivityTutorials.this);
                startActivity(youtubeIntent);

            }
        });

        btnAftereffectsTutorials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent youtubeIntent = openAftereffectsTutorialsYT(ActivityTutorials.this);
                startActivity(youtubeIntent);

            }
        });

    }

    public static Intent openPhotoshopTutorialsYT(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.youtube.com/user/NewWorldOps",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/user/NewWorldOps"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/user/NewWorldOps"));
        }

    }

    public static Intent openLightroomTutorialsYT(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.youtube.com/channel/UCBI3mfSJ9nU3rORrYsoY75w",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCBI3mfSJ9nU3rORrYsoY75w"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCBI3mfSJ9nU3rORrYsoY75w"));
        }

    }

    public static Intent openIllusratorTutorialsYT(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.youtube.com/channel/UCnfmky_XJXqYRnb6H5a6pXg",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCnfmky_XJXqYRnb6H5a6pXg"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCnfmky_XJXqYRnb6H5a6pXg"));
        }

    }


    public static Intent openPremierTutorialsYT(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.youtube.com/channel/UCaDZLsDEEBBJESXmmi-9m5w",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCaDZLsDEEBBJESXmmi-9m5w"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCaDZLsDEEBBJESXmmi-9m5w"));
        }

    }

    public static Intent openAftereffectsTutorialsYT(Context context){

        try {
            context.getPackageManager()
                    .getPackageInfo("https://www.youtube.com/channel/UCOi5hBrqjJX0BiK3CqYWXsQ",0);
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCOi5hBrqjJX0BiK3CqYWXsQ"));

        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UCOi5hBrqjJX0BiK3CqYWXsQ"));
        }

    }
}
