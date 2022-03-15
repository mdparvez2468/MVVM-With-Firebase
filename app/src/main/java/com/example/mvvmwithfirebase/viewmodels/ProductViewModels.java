package com.example.mvvmwithfirebase.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmwithfirebase.models.Product;
import com.example.mvvmwithfirebase.repository.ProductRepository;

import java.util.List;

public class ProductViewModels extends ViewModel {
    MutableLiveData<List<Product>> products;

    public void init(Context context){

        if (products!=null){
            return;
        }
        products = ProductRepository.getInstance(context).getProducts();

    }
    public LiveData<List<Product>> getProducts(){
        return products;

    }
}
