package com.example.courseproject.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import com.example.courseproject.R;
import com.example.courseproject.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {

    ActivityAdminBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment2)).getNavController();
        NavigationUI.setupWithNavController(binding.bottomNavigation2, navController);
        binding.bottomNavigation2.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navigation_add)
                navController.navigate(R.id.firstFragment2);
            else if (item.getItemId() == R.id.navigation_settings)
                navController.navigate(R.id.secondFragment2);
            else if (item.getItemId() == R.id.navigation_author)
                navController.navigate(R.id.thirdFragment2);
            return true;
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp() || super.onSupportNavigateUp();
    }
}