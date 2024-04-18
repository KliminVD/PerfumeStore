package com.example.courseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.courseproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InitialScreen extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null)
            Toast.makeText(this, "User is identificated", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "User is not identificated", Toast.LENGTH_SHORT).show();
    }
    public void onClickSignUp(View view) {
        if (!TextUtils.isEmpty(binding.edEmail.getText().toString()) &&
                !TextUtils.isEmpty(binding.edPass.getText().toString()))
            mAuth.createUserWithEmailAndPassword(binding.edEmail.getText().toString(), binding.edPass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful())
                            Toast.makeText(getApplicationContext(), "User sign up successful", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(), "User sign up failed", Toast.LENGTH_LONG).show();
                    });
    }
    public void onClickSignIn(View view){

    }

}