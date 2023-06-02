package com.example.womandressdesigns.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.womandressdesigns.Adapters.CategoryAdapter;
import com.example.womandressdesigns.ModelClass.categoryModel;
import com.example.womandressdesigns.R;
import com.example.womandressdesigns.databinding.AllCategoriesBinding;

import java.util.ArrayList;

public class AllCategories extends AppCompatActivity {
AllCategoriesBinding binding;
ArrayList<categoryModel>categoryList;
CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=AllCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        categoryList=new ArrayList<>();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.categoryRecyler.setLayoutManager(layoutManager);

        categoryList.add(new categoryModel(R.drawable.mndhi,"Mandhi Dresses Designs"));
        categoryList.add(new categoryModel(R.drawable.bride,"Bridal Dresses Designs"));
        categoryList.add(new categoryModel(R.drawable.wilmapic,"Walima Dresses Designs"));
        categoryList.add(new categoryModel(R.drawable.partypic,"Party Dresses Designs"));
        categoryList.add(new categoryModel(R.drawable.party,"BirthDayParty Dresses Designs"));

        adapter=new CategoryAdapter(categoryList,this);
        binding.categoryRecyler.setAdapter(adapter);



    }
}