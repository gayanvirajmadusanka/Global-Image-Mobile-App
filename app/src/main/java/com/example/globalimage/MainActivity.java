package com.example.globalimage;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Action bar and title
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Home");

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setMessage("Do you need to exit from the app");
        dialog.setTitle("Exit");
        dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {
                        Intent exit = new Intent(Intent.ACTION_MAIN);
                        exit.addCategory(Intent.CATEGORY_HOME);
                        exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(exit);

                        Toast.makeText(getApplicationContext(), "Exit the app", Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();




        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(),ActivitySetting.class));
            return true;
        }

//      Log Out
        if (id == R.id.action_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent loginActivity = new Intent(getApplicationContext(),ActivityLogIN.class);
            startActivity(loginActivity);

            Toast.makeText(MainActivity.this,"Your account is successfully logout",Toast.LENGTH_SHORT).show();

            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {

            Intent login = new Intent(MainActivity.this, ActivityLogIN.class);
            startActivity(login);
            Toast.makeText(MainActivity.this,"Log In",Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.nav_my_profile) {

            Intent myprofile = new Intent(MainActivity.this, Main2Activity.class);
            startActivity(myprofile);
            Toast.makeText(MainActivity.this,"My Profile",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_gi_wall) {

            Intent giwall = new Intent(MainActivity.this, ActivityGiWall.class);
            startActivity(giwall);
            Toast.makeText(MainActivity.this,"Global Image wall",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_plan_event) {

            Intent packages = new Intent(MainActivity.this, ActivityPlanEvent.class);
            startActivity(packages);
            Toast.makeText(MainActivity.this,"Plan event",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_packages) {

            Intent packages = new Intent(MainActivity.this, ActivityPackage.class);
            startActivity(packages);
            Toast.makeText(MainActivity.this,"Packages",Toast.LENGTH_SHORT).show();

           /* final ProgressDialog pd = new ProgressDialog(MainActivity.this);
            pd.setMessage("Loading...");
            pd.show();*/

        } else if (id == R.id.nav_tutorials) {

            Intent packages = new Intent(MainActivity.this, ActivityTutorials.class);
            startActivity(packages);
            Toast.makeText(MainActivity.this,"Tutorials",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_our_team) {

            Intent packages = new Intent(MainActivity.this, ActivityOurTeam.class);
            startActivity(packages);
            Toast.makeText(MainActivity.this,"Our Team",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_contact_us) {

            Intent packages = new Intent(MainActivity.this, ActivityContactUs.class);
            startActivity(packages);
            Toast.makeText(MainActivity.this,"Contact us",Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_setting) {

            Intent setting = new Intent(MainActivity.this, ActivitySetting.class);
            startActivity(setting);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
