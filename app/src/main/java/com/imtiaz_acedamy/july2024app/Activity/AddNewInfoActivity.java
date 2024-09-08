package com.imtiaz_acedamy.july2024app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.ActivityAddNewInfoBinding;

public class AddNewInfoActivity extends AppCompatActivity {
    ActivityAddNewInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });

        binding.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewInfoActivity.this, MainActivity.class));
                finish();

            }
        });

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNewInfoActivity.this, AddNewInfo2Activity.class));
                finish();
            }
        });

        binding.myView.setElevation(3);

    }
}