package com.example.courseproject.LiveDataManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courseproject.Entities.Product;
import com.example.courseproject.LiveDataManager.ProductRepository;

import java.util.List;

public class ProductViewModel extends ViewModel {
    private ProductRepository repository;
    private LiveData<List<Product>> productsLiveData;

    public ProductViewModel() {
        repository = new ProductRepository();
        productsLiveData = repository.getProductsLiveData();
    }

    public LiveData<List<Product>> getProductsLiveData() {
        return productsLiveData;
    }
}
