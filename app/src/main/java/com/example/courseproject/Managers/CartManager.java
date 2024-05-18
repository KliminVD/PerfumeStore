package com.example.courseproject.Managers;
import com.example.courseproject.Entities.Product;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CartManager {

    private FirebaseFirestore db;
    private String userId;
    public CartManager(String userId) {
        this.userId = userId;
        db = FirebaseFirestore.getInstance();
    }
    public void setProductQuantity(Product product, int quantity) {
        DocumentReference userDocRef = db.collection("users").document(userId);
        if (quantity >= 1) {
            product.setQuantity(quantity);
            userDocRef.collection("cart").document(product.getId()).set(product);
        }
        if (quantity == 0) {
            product.setQuantity(quantity);
            userDocRef.collection("cart").document(product.getId()).delete();
        }
    }
}
