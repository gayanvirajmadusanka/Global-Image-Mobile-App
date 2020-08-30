package com.example.globalimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivitySignUp extends AppCompatActivity {

    //Views
    EditText userEmail, userPassword, userPasswordConfirm, userName, userContactNumber;
    Button regButtonConfirm;
    RadioButton radioregmale, radioregfemale;
    ProgressBar progressBar;
    CircleImageView userPhoto;

    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    String gender="";

    final static int PICK_IMAGE = 1;
    Uri imageUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Action bar and title
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Sign Up");

        userEmail = (EditText) findViewById(R.id.regemail);
        userName = (EditText) findViewById(R.id.regname);
        userPassword = (EditText) findViewById(R.id.regpassword);
        userPasswordConfirm = (EditText) findViewById(R.id.regconfirmpassword);
        userContactNumber = (EditText) findViewById(R.id.regcontactnum);
        userPhoto = (CircleImageView) findViewById(R.id.profile_image);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);


        regButtonConfirm = (Button) findViewById(R.id.registrationformconfirmbtn);

        radioregmale = (RadioButton) findViewById(R.id.regmale);
        radioregfemale = (RadioButton) findViewById(R.id.regfemale);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Customer");


        ///////////////When touch the add photo icon open the gallery and display gallery images////******
        userPhoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, PICK_IMAGE);

            }
        });

        //////////////////////////////////////////////////////////////////////////////////////////******



        regButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = userEmail.getText().toString();
                final String name = userName.getText().toString();
                final String password = userPassword.getText().toString();
                final String confirmpassword= userPasswordConfirm.getText().toString();
                final String number = userContactNumber.getText().toString();

                if (radioregmale.isChecked()) {
                    gender = "Male";
                }

                if (radioregfemale.isChecked()) {
                    gender = "Female";
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(ActivitySignUp.this, "Please your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(ActivitySignUp.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(number)) {
                    Toast.makeText(ActivitySignUp.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(ActivitySignUp.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(ActivitySignUp.this, "Please confirm password", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (password.length()<6){

                    Toast.makeText(ActivitySignUp.this, "Password too short", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.VISIBLE);

                if (password.equals(confirmpassword)){

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(ActivitySignUp.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()){

                                        Customer information = new Customer(
                                                email,
                                                name,
                                                password,
                                                confirmpassword,
                                                number,
                                                gender
                                        );

                                        FirebaseDatabase.getInstance().getReference("Customer")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                startActivity(new Intent(getApplicationContext(),ActivityLogIN.class));
                                                Toast.makeText(ActivitySignUp.this, "Registration successfully, Please check your email for verification", Toast.LENGTH_SHORT).show();


                                            }
                                        });

                                        firebaseAuth.getCurrentUser().sendEmailVerification()
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()){

                                                }else {

                                                    Toast.makeText(ActivitySignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                                }



                                            }
                                        });

                                    }else {



                                    }

                                }
                            });

                }

            }
        });

    }


//////////////////////////////////////Select the photo from gallery////////////////////////////***
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {


            imageUri = data.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                userPhoto.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

//////////////////////////////////////Select the photo from gallery////////////////////////////***


    public void btn_register_cancel (View view){
        startActivity(new Intent(getApplicationContext(), ActivityLogIN.class));
        Toast.makeText(ActivitySignUp.this, "Cancel registration", Toast.LENGTH_SHORT).show();
    }
}

