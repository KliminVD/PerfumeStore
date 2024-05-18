package com.example.courseproject.LiveDataManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courseproject.Entities.Product;
import com.example.courseproject.LiveDataManager.ProductCartRepository;

import java.util.List;

public class ProductCartViewModel extends ViewModel {
    private ProductCartRepository repository;
    private LiveData<List<Product>> productsLiveData;

    public ProductCartViewModel() {
        repository = new ProductCartRepository();
        productsLiveData = repository.getProductsLiveData();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return productsLiveData;
    }
}
