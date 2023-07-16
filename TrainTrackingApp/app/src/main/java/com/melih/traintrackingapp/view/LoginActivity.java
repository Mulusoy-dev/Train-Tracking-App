package com.melih.traintrackingapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.melih.traintrackingapp.R;
import com.melih.traintrackingapp.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {


    // View binding
    private ActivityLoginBinding binding;

    // Authentication
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();



        // Login with without email and password
        /*
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        */

    }




    // Login Button Operations
    public void loginClicked(View view) {
        String email = binding.editTextTextPersonName.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();


        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }


    }

    // Sign up Text Operations
    public void signClicked(View view) {

        String email = binding.editTextTextPersonName.getText().toString();
        String password = binding.editTextTextPassword.getText().toString();


        if (email.equals("") || password.equals("")) {
            Toast.makeText(this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {                   // Login is success
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {                    // Login is failure
                    Toast.makeText(LoginActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }







    }
}