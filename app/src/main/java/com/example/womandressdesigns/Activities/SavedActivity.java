package com.example.womandressdesigns.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.womandressdesigns.databinding.ActivitySavedBinding;

public class SavedActivity extends AppCompatActivity {
ActivitySavedBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySavedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}