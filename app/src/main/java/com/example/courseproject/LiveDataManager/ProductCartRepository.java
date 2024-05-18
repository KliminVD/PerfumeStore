package com.example.courseproject.LiveDataManager;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.courseproject.Entities.Product;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProductCartRepository {
    private FirebaseFirestore db;
    private MutableLiveData<List<Product>> productsLiveData;
    private FirebaseAuth mAuth;
    private CollectionReference userCartRef;
    public ProductCartRepository() {
        db = FirebaseFirestore.getInstance();
        productsLiveData = new MutableLiveData<>();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            userCartRef = db.collection("users").document(currentUser.getUid()).collection("cart");
        }
    }

    public LiveData<List<Product>> getProductsLiveData() {
        loadProducts();
        return productsLiveData;
    }

    private void loadProducts() {
        userCartRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Product> productList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Product product = document.toObject(Product.class);
                    productList.add(product);
                }
                Log.d("MyLogs", productList.size() + "");
                productsLiveData.setValue(productList);
            }
        });
    }
}
