package com.example.mvvmwithfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.example.mvvmwithfirebase.models.Product;
import com.example.mvvmwithfirebase.viewmodels.ProductViewModels;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataLoadListener{


    RecyclerView recyclerView;
    ProductAdapter productAdapter;

    private ProductViewModels productViewModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        productViewModels = ViewModelProviders.of(MainActivity.this).get(ProductViewModels.class);

        productViewModels.init(MainActivity.this);

        productAdapter = new ProductAdapter(productViewModels.getProducts().getValue());

        recyclerView.setAdapter(productAdapter);


    }

    @Override
    public void onProductLoaded() {
        productViewModels.getProducts().observe(MainActivity.this, new Observer<List<Product>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<Product> products) {
                productAdapter.notifyDataSetChanged();

            }
        });
    }
}