package com.example.courseproject.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.courseproject.Activities.InitialScreen;
import com.example.courseproject.Entities.User;
import com.example.courseproject.LiveDataManager.UserViewModel;
import com.example.courseproject.databinding.FragmentSettingsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUserLiveData().observe(getViewLifecycleOwner(), user -> {
            this.user = user;
            if (user.getName().isEmpty())
                binding.nameValue.setText("Введите имя");
            else binding.nameValue.setText(user.getName());
            if (user.getSurname().isEmpty())
                binding.surnameValue.setText("Введите фамилию");
            else binding.surnameValue.setText(user.getSurname());
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.EditActivity");
                startActivityForResult.launch(intent);
            }
        });
        binding.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getContext(), InitialScreen.class);
                startActivity(intent);
            }
        });
    }
    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent intent = result.getData();
                                if (intent != null) {
                                    String name = intent.getStringExtra("name");
                                    binding.nameValue.setText(name);
                                    String surname = intent.getStringExtra("surname");
                                    binding.surnameValue.setText(surname);
                                    user.setName(name);
                                    user.setSurname(surname);
                                    db.collection("users").document(mAuth.getCurrentUser().getUid()).set(user);
                                }
                            }
                            else Toast.makeText(getContext(), "Cannot load user info", Toast.LENGTH_LONG).show();
                        }
                    });
}