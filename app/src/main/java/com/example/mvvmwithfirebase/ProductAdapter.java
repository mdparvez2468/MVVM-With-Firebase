package com.example.mvvmwithfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmwithfirebase.models.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_sample,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Product product = productList.get(position);

        holder.idTV.setText(product.getId());
        holder.nameTV.setText(product.getName());
        holder.priceTV.setText(product.getPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTV,nameTV,priceTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.idTV);
            nameTV = itemView.findViewById(R.id.nameTV);
            priceTV = itemView.findViewById(R.id.priceTV);

        }
    }
}
