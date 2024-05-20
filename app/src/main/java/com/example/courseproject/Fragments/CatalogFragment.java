package com.example.courseproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.courseproject.Adapter.ProductAdapter;
import com.example.courseproject.LiveDataManager.ProductViewModel;
import com.example.courseproject.databinding.FragmentCatalogBinding;

import java.util.ArrayList;


public class CatalogFragment extends Fragment {
    private FragmentCatalogBinding binding;
    private ProductAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCatalogBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mAdapter = new ProductAdapter(getContext(), new ArrayList<>());
        binding.productListView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProductsLiveData().observe(getViewLifecycleOwner(), productList -> {
            mAdapter.setProducts(productList);
            mAdapter.notifyDataSetChanged();
        });
    }
}