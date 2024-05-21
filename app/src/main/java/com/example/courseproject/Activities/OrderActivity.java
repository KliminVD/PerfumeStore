package com.example.courseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.courseproject.R;
import com.example.courseproject.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        binding.totalCostTextView.setText(intent.getStringExtra("cost"));
        binding.makeAnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(binding.edName.getText().toString().isEmpty() || binding.edSurname.getText().toString().isEmpty() ||
                        binding.telephone.getText().toString().isEmpty() || binding.adress.getText().toString().isEmpty())) {
                    Toast.makeText(OrderActivity.this, "Order created", Toast.LENGTH_LONG).show();
                    finish();
                }
                else Toast.makeText(OrderActivity.this, "Please, enter all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}