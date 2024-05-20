package com.example.courseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.courseproject.Entities.User;
import com.example.courseproject.R;
import com.example.courseproject.databinding.ActivityEditBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditActivity extends AppCompatActivity {
    ActivityEditBinding binding;
    private User user;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.etName.getText().toString();
                String surname = binding.etSurname.getText().toString();
                Intent intent = new Intent();
                if (!(name.isEmpty() || surname.isEmpty())) {
                    intent.putExtra("name", name);
                    intent.putExtra("surname", surname);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}