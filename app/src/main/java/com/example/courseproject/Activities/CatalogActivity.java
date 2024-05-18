package com.example.courseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.courseproject.R;
import com.example.courseproject.Entities.User;
import com.example.courseproject.databinding.ActivityCatalogBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;


public class CatalogActivity extends AppCompatActivity {
    ActivityCatalogBinding binding;
    private FirebaseAuth mAuth;
    private NavController navController;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCatalogBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mAuth = FirebaseAuth.getInstance();
        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController);
        binding.bottomNavigation.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_catalogue)
                navController.navigate(R.id.firstFragment);
            else if (item.getItemId() == R.id.navigation_cart)
                navController.navigate(R.id.secondFragment);
            else if (item.getItemId() == R.id.navigation_constructor)
                navController.navigate(R.id.thirdFragment);
            else if (item.getItemId() == R.id.navigation_settings)
                navController.navigate(R.id.forthFragment);
            return true;
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}