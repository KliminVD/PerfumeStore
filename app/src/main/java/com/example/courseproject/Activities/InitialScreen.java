package com.example.courseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.courseproject.Entities.User;
import com.example.courseproject.LiveDataManager.UserViewModel;
import com.example.courseproject.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class InitialScreen extends AppCompatActivity {
    ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {
            Log.d("ffff", cUser.getUid());
            UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            userViewModel.getUserLiveData().observe(this, user -> {
                if (user.getRole().equals("Пользователь")){
                    Intent intent = new Intent(this, UserActivity.class);
                    startActivity(intent);
                }
                else if (user.getRole().equals("Администратор")){
                    Intent intent = new Intent(this, AdminActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
    public void onClickSignUp(View view) {
        if (!TextUtils.isEmpty(binding.edEmail.getText().toString()) &&
                !TextUtils.isEmpty(binding.edPass.getText().toString()))
            mAuth.createUserWithEmailAndPassword(binding.edEmail.getText().toString(), binding.edPass.getText().toString())
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            db.collection("users").document(mAuth.getCurrentUser().getUid()).set(new User("", "", binding.spinRole.getSelectedItem().toString()));
                            if (binding.spinRole.getSelectedItem().toString().equals("Пользователь")){
                                Intent intent = new Intent(this, UserActivity.class);
                                startActivity(intent);
                            }
                            else if (binding.spinRole.getSelectedItem().toString().equals("Администратор")){
                                Intent intent = new Intent(this, AdminActivity.class);
                                startActivity(intent);
                            }
                            Toast.makeText(getApplicationContext(), "User sign up successful", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "User sign up unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    });
        else
            Toast.makeText(getApplicationContext(), "Enter full information", Toast.LENGTH_SHORT).show();

    }
    public void onClickSignIn(View view){
        if (!TextUtils.isEmpty(binding.edEmail.getText().toString()) &&
                !TextUtils.isEmpty(binding.edPass.getText().toString()))
            mAuth.signInWithEmailAndPassword(binding.edEmail.getText().toString(), binding.edPass.getText().toString())
                    .addOnCompleteListener(task -> {
                       if (task.isSuccessful()) {
                           UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
                           userViewModel.getUserLiveData().observe(this, user -> {
                               if (user.getRole().equals("Пользователь")){
                                   Intent intent = new Intent(this, UserActivity.class);
                                   startActivity(intent);
                               }
                               else if (user.getRole().equals("Администратор")){
                                   Intent intent = new Intent(this, AdminActivity.class);
                                   startActivity(intent);
                               }
                           });
                           Toast.makeText(getApplicationContext(), "User sign in successful", Toast.LENGTH_LONG).show();
                       }
                       else
                           Toast.makeText(getApplicationContext(), "User sign in unsuccessful", Toast.LENGTH_LONG).show();
                    });
        else
            Toast.makeText(getApplicationContext(), "Enter full information", Toast.LENGTH_SHORT).show();
    }

}