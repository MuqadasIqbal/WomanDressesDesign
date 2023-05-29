package com.example.womandressdesigns.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.womandressdesigns.Adapters.CategoryAdapter;
import com.example.womandressdesigns.ModelClass.categoryModel;
import com.example.womandressdesigns.R;

import java.util.ArrayList;

public class CategoryNameActivity extends AppCompatActivity {
ActivityCategoryNameBinding binding;
    ArrayList<categoryModel>list;
    CategoryAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCategoryNameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list=new ArrayList<>();
        list.add(new categoryModel(R.drawable.mandhidres));
        list.add(new categoryModel(R.drawable.greenmandhidress));
        list.add(new categoryModel(R.drawable.yeloowpinkmandhidress));

        adapter = new CategoryAdapter(list);





        binding.rightArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryNameActivity.this, SavedActivity.class);
                startActivity(intent);
            }
        });
    }
}