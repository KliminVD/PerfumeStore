package com.example.courseproject.Fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.courseproject.Entities.Product;
import com.example.courseproject.databinding.FragmentConstructBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class ConstructFragment extends Fragment {
    FragmentConstructBinding binding;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    final float initialScale = 0f;
    final float firstScale = 0.33f;
    final float secondScale = 0.67f;
    final float finalScale = 1.0f;
    private int amount = 0;
    private String[] values = {"Выберите ингредиент", "Выберите ингредиент", "Выберите ингредиент"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentConstructBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        FirebaseUser cUser = mAuth.getCurrentUser();
        binding.greenBar.setVisibility(View.INVISIBLE);
        ObjectAnimator firstIncreaseAnimator = ObjectAnimator.ofFloat(binding.greenBar, "scaleX", initialScale, firstScale);
        firstIncreaseAnimator.setDuration(500);
        ObjectAnimator secondIncreaseAnimator = ObjectAnimator.ofFloat(binding.greenBar, "scaleX", firstScale, secondScale);
        secondIncreaseAnimator.setDuration(500);
        ObjectAnimator finalIncreaseAnimator = ObjectAnimator.ofFloat(binding.greenBar, "scaleX", secondScale, finalScale);
        finalIncreaseAnimator.setDuration(500);
        ObjectAnimator firstDecreaseAnimator = ObjectAnimator.ofFloat(binding.greenBar, "scaleX", finalScale, secondScale);
        firstDecreaseAnimator.setDuration(500);
        ObjectAnimator secondDecreaseAnimator = ObjectAnimator.ofFloat(binding.greenBar, "scaleX", secondScale, firstScale);
        secondDecreaseAnimator.setDuration(500);
        ObjectAnimator finalDecreaseAnimator = ObjectAnimator.ofFloat(binding.greenBar, "scaleX", firstScale, initialScale);
        finalDecreaseAnimator.setDuration(500);
        binding.spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    switch (amount) {
                        case 0:
                            if (values[0].equals("Выберите ингредиент")) {
                                amount++;
                                binding.greenBar.setVisibility(View.VISIBLE);
                                firstIncreaseAnimator.start();
                                values[0] = binding.spinner1.getSelectedItem().toString();
                            }
                            break;
                        case 1:
                            if (values[0].equals("Выберите ингредиент")) {
                                amount++;
                                secondIncreaseAnimator.start();
                                values[0] = binding.spinner1.getSelectedItem().toString();
                            }
                            break;
                        case 2:
                            if (values[0].equals("Выберите ингредиент")) {
                                amount++;
                                finalIncreaseAnimator.start();
                                values[0] = binding.spinner1.getSelectedItem().toString();
                            }
                            break;
                    }
                } else {
                    values[0] = "Выберите ингредиент";
                    switch (amount) {
                        case 3:
                            amount--;
                            firstDecreaseAnimator.start();
                            break;
                        case 2:
                            amount--;
                           secondDecreaseAnimator.start();
                            break;
                        case 1:
                            amount--;
                            finalDecreaseAnimator.start();
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    switch (amount) {
                        case 0:
                            if (values[1].equals("Выберите ингредиент")) {
                                amount++;
                                binding.greenBar.setVisibility(View.VISIBLE);
                                firstIncreaseAnimator.start();
                                values[1] = binding.spinner2.getSelectedItem().toString();
                            }
                            break;
                        case 1:
                            if (values[1].equals("Выберите ингредиент")) {
                                amount++;
                                secondIncreaseAnimator.start();
                                values[1] = binding.spinner2.getSelectedItem().toString();
                            }
                            break;
                        case 2:
                            if (values[1].equals("Выберите ингредиент")) {
                                amount++;
                                finalIncreaseAnimator.start();
                                values[1] = binding.spinner2.getSelectedItem().toString();
                            }
                            break;
                    }
                } else {
                    values[1] = "Выберите ингредиент";
                    switch (amount) {
                        case 3:
                            amount--;
                            firstDecreaseAnimator.start();
                            break;
                        case 2:
                            amount--;
                            secondDecreaseAnimator.start();
                            break;
                        case 1:
                            amount--;
                            finalDecreaseAnimator.start();
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        binding.spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    switch (amount) {
                        case 0:
                            if (values[2].equals("Выберите ингредиент")) {
                                amount++;
                                binding.greenBar.setVisibility(View.VISIBLE);
                                firstIncreaseAnimator.start();
                                values[2] = binding.spinner3.getSelectedItem().toString();
                            }
                            break;
                        case 1:
                            if (values[2].equals("Выберите ингредиент")) {
                                amount++;
                                secondIncreaseAnimator.start();
                                values[2] = binding.spinner3.getSelectedItem().toString();
                            }
                            break;
                        case 2:
                            if (values[2].equals("Выберите ингредиент")) {
                                amount++;
                                finalIncreaseAnimator.start();
                                values[2] = binding.spinner3.getSelectedItem().toString();
                            }
                            break;
                    }
                } else {
                    values[2] = "Выберите ингредиент";
                    switch (amount) {
                        case 3:
                            amount--;
                            firstDecreaseAnimator.start();
                            break;
                        case 2:
                            amount--;
                            secondDecreaseAnimator.start();
                            break;
                        case 1:
                            amount--;
                            finalDecreaseAnimator.start();
                            break;
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (amount == 3){
                    DocumentReference newDoc = db.collection("users").document(cUser.getUid()).collection("cart").document();
                    newDoc.set(new Product(newDoc.getId(),
                            binding.spinner1.getSelectedItem().toString() + "/"
                                    + binding.spinner2.getSelectedItem().toString() + "/"
                                    + binding.spinner3.getSelectedItem().toString(),
                            8000, "Icon.png", 1));
                    Toast.makeText(getContext(), "Craft perfume is added to cart", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(getContext(), "Not enough elements", Toast.LENGTH_LONG).show();
                }
        });
        return view;
    }
}