package com.atulbahniwal.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Signup_Form extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button signin;
    private Button signup;

    private FirebaseAuth mAuth;

    private ProgressDialog mDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);


        mAuth = FirebaseAuth.getInstance();

        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.email_reg);
        pass = findViewById(R.id.password_reg);
        signin = findViewById(R.id.signin_txt);
        signup = findViewById(R.id.btn_reg);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mEmail = email.getText().toString().trim();
                String mPass = pass.getText().toString().trim();

                if(TextUtils.isEmpty(mEmail)){
                    email.setError("Required Field...");
                    return;
                }
                if(TextUtils.isEmpty(mPass)){
                    pass.setError("Required Field...");
                    return;
                }

                mDialog.setMessage("Processing...");
                mDialog.show();

                mAuth.createUserWithEmailAndPassword(mEmail, mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();

                            mDialog.dismiss();
//                            finish();

                        } else {
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

                            mDialog.dismiss();
                        }
                    }
                });

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }
}
