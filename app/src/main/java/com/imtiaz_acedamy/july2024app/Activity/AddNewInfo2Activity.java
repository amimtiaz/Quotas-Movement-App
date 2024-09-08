package com.imtiaz_acedamy.july2024app.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.ActivityAddNewInfo2Binding;

public class AddNewInfo2Activity extends AppCompatActivity {

    ActivityAddNewInfo2Binding binding;
    AlertDialog customProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNewInfo2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showProgressDialog2();

            }
        });

        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddNewInfo2Activity.this, AddNewInfoActivity.class));
                finish();
            }
        });

        binding.beforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddNewInfo2Activity.this, AddNewInfoActivity.class));
                finish();

            }
        });

    }

    public void showProgressDialog2() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AddNewInfo2Activity.this);

        // Inflate the custom layout
        View dialogView = getLayoutInflater().inflate(R.layout.succes_progress_dialog, null);

        // Set the custom layout to the dialog builder
        builder.setView(dialogView);
        builder.setCancelable(false);

        LinearLayout backHomeBtn = dialogView.findViewById(R.id.backHomeBtn);
        backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AddNewInfo2Activity.this, MainActivity.class));

            }
        });

        // Create the dialog
        customProgressDialog = builder.create();

        if (customProgressDialog.getWindow() != null) {

            customProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        }

        // Show the dialog
        customProgressDialog.show();
    }
}