package com.example.courseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.courseproject.Entities.Product;
import com.example.courseproject.Managers.CartManager;
import com.example.courseproject.R;
import com.example.courseproject.databinding.ActivityPageBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class PageActivity extends AppCompatActivity {
    private ActivityPageBinding binding;
    private Product product;
    private CartManager manager;
    private FirebaseAuth mAuth;
    private StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser cUser = mAuth.getCurrentUser();
        manager = new CartManager(cUser.getUid());
        product = (Product) getIntent().getSerializableExtra("product");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference().child("images");
        StorageReference imageRef = storageRef.child(product.getImage());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imageRef.getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Glide.with(PageActivity.this)
                                .load(uri)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(binding.imageView);
                    }
                });
        binding.namePage.setText(product.getName());
        binding.costPage.setText(product.getPrice() + "");
    }
}