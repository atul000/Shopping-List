package com.atulbahniwal.myapplication;

import android.app.Application;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PreventSignOut extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(PreventSignOut.this, HomeActivity.class));
        } else {
            startActivity(new Intent(PreventSignOut.this,MainActivity.class));
        }

    }
}
