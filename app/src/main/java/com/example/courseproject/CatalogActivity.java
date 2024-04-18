package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.courseproject.databinding.ActivityCatalogBinding;

public class CatalogActivity extends AppCompatActivity {
    ActivityCatalogBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatalogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
}