package com.example.courseproject.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.courseproject.Adapter.ProductAdapter;
import com.example.courseproject.Entities.Product;
import com.example.courseproject.LiveDataManager.ProductCartViewModel;
import com.example.courseproject.R;
import com.example.courseproject.databinding.FragmentCartBinding;

import java.util.ArrayList;


public class CartFragment extends Fragment {
    private FragmentCartBinding binding;
    private ProductAdapter mAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mAdapter = new ProductAdapter(getContext(), new ArrayList<>());
        binding.productListView.setAdapter(mAdapter);

        ProductCartViewModel productCartViewModel = new ViewModelProvider(this).get(ProductCartViewModel.class);
        productCartViewModel.getProductsLiveData().observe(getViewLifecycleOwner(), productList -> {
            if (productList.isEmpty())
                binding.tvEmpty.setVisibility(View.VISIBLE);
            else{
                int sum = 0;
                for (Product p : productList){
                    sum += p.getPrice() * p.getQuantity();
                }
                update(sum);
            }
            mAdapter.setProducts(productList);
            mAdapter.setFrag(this);
            mAdapter.notifyDataSetChanged();
        });
        return view;
    }
    public void update(int sum){
        binding.totalCostTextView.setText("Общая стоимость: " + sum + " рублей");
    }
}