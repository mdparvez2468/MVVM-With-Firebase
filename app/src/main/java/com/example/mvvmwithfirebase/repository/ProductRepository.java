package com.example.mvvmwithfirebase.repository;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.mvvmwithfirebase.DataLoadListener;
import com.example.mvvmwithfirebase.models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public static ProductRepository productRepository;
    private List<Product> productList = new ArrayList<>();

    private DatabaseReference databaseReference;

    private static Context rContext;

    public static DataLoadListener dataLoadListener;

    public static ProductRepository getInstance(Context context){
        rContext = context;
        if (productRepository==null){
            productRepository = new ProductRepository();
        }
        dataLoadListener = (DataLoadListener) rContext;
        return productRepository;
    }

    public MutableLiveData<List<Product>>getProducts(){


        loadProducts();



        MutableLiveData<List<Product>> products = new MutableLiveData<>();
        products.setValue(productList);



        return  products;

    }

    private void loadProducts() {

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Query query = databaseReference.child("ProductInfo");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    productList.clear();
                    for (DataSnapshot dataSnapshot: snapshot.getChildren()){

                        Product product = dataSnapshot.getValue(Product.class);

                        productList.add(product);



                    }
                    dataLoadListener.onProductLoaded();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}
