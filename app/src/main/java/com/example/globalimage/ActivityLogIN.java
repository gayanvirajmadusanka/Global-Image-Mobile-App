package com.example.globalimage;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityLogIN extends AppCompatActivity {

    EditText userEmail,userPassword;
    Button btnLogIn;
    CheckBox rememberPassword;
    ProgressBar progressBar;
    private String email,password;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().setTitle("Log In");

        userEmail = (EditText)findViewById(R.id.loginemail);
        userPassword = (EditText)findViewById(R.id.loginpassword);
        btnLogIn = (Button) findViewById(R.id.signinbtn);
        rememberPassword = (CheckBox) findViewById(R.id.checkboxrememberpassword);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

//remember password check box
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin",false);

        if (saveLogin == true){

            userEmail.setText(loginPreferences.getString("email",""));
            userPassword.setText(loginPreferences.getString("password",""));
            rememberPassword.setChecked(true);
        }

        firebaseAuth = FirebaseAuth.getInstance();

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog pd = new ProgressDialog(ActivityLogIN.this);
                pd.setMessage("Loading...");
                pd.show();

                String email = userEmail.getText().toString().trim();
                String password = userPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(ActivityLogIN.this,"Please enter email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    Toast.makeText(ActivityLogIN.this,"Please enter password",Toast.LENGTH_SHORT).show();
                    return;
                }

                if (rememberPassword.isChecked()){

                    Toast.makeText(ActivityLogIN.this,"Remember email & password",Toast.LENGTH_SHORT).show();
                    loginPrefsEditor.putBoolean("saveLogin",true);
                    loginPrefsEditor.putString("email",email);
                    loginPrefsEditor.putString("password",password);
                    loginPrefsEditor.commit();

                }else {
                    loginPrefsEditor.clear();
                    loginPrefsEditor.commit();

                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(ActivityLogIN.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()){
                                    if (firebaseAuth.getCurrentUser().isEmailVerified()){

                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        Toast.makeText(ActivityLogIN.this,"Log In successful",Toast.LENGTH_LONG).show();
                                        pd.cancel();

                                    }else {

                                        Toast.makeText(ActivityLogIN.this,"Please verify your Email",Toast.LENGTH_LONG).show();
                                    }


                                }else {

                                    Toast.makeText(ActivityLogIN.this,"Log In failed",Toast.LENGTH_SHORT).show();

                                }

                            }
                        });



            }
        });

    }

// Home icon

/*    @Override
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
    }*/

    @Override
    public void onBackPressed() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(ActivityLogIN.this);
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

    public void btn_signupForm(View view) {
        startActivity(new Intent(getApplicationContext(),ActivitySignUp.class));
    }
}
