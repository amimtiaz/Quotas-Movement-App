package com.imtiaz_acedamy.july2024app.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.imtiaz_acedamy.july2024app.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {

    ActivityIntroBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        binding.startBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
      
    }
}