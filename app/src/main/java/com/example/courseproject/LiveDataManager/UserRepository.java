package com.example.courseproject.LiveDataManager;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courseproject.Entities.Product;
import com.example.courseproject.Entities.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private FirebaseFirestore db;
    private MutableLiveData<User> userLiveData;
    private FirebaseAuth mAuth;
    private DocumentReference userCartRef;
    public UserRepository() {
        db = FirebaseFirestore.getInstance();
        userLiveData = new MutableLiveData<>();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userCartRef = db.collection("users").document(currentUser.getUid());
        }
    }

    public LiveData<User> getUserLiveData() {
        loadUser();
        return userLiveData;
    }

    private void loadUser() {
        userCartRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = new User();
                user.setName(documentSnapshot.getString("name"));
                user.setSurname(documentSnapshot.getString("surname"));
                user.setRole(documentSnapshot.getString("role"));
                userLiveData.setValue(user);
            }
        });
    }
}