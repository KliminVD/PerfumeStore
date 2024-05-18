package com.example.courseproject.LiveDataManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.courseproject.Entities.Product;
import com.example.courseproject.Entities.User;

import java.util.List;

public class UserViewModel extends ViewModel {
    private UserRepository repository;
    private LiveData<User> userLiveData;

    public UserViewModel() {
        repository = new UserRepository();
        userLiveData = repository.getUserLiveData();
    }

    public LiveData<User> getUserLiveData() {
        return userLiveData;
    }
}
