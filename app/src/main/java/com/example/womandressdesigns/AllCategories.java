package com.example.womandressdesigns;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.womandressdesigns.Activities.CategoryNameActivity;
import com.example.womandressdesigns.databinding.AllCategoriesBinding;

public class AllCategories extends AppCompatActivity {
AllCategoriesBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=AllCategoriesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.mahndiView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AllCategories.this, CategoryNameActivity.class);
                startActivity(intent);
            }
        });


    }
}