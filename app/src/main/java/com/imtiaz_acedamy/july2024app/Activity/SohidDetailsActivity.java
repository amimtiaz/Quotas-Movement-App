package com.imtiaz_acedamy.july2024app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.imtiaz_acedamy.july2024app.Fragment.SohidListFragment;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.ActivitySohidDetailsBinding;

import java.io.FileInputStream;

public class SohidDetailsActivity extends AppCompatActivity {

    ActivitySohidDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySohidDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        setVariable();

    }

    private void setVariable(){

        binding.backBtn.setOnClickListener(v -> {

            Intent intent = new Intent(SohidDetailsActivity.this, MainActivity.class);
            intent.putExtra("show_fragment", "sohid_list"); // Pass data to MainActivity
            startActivity(intent);

        });

       String name = getIntent().getStringExtra("name");
       String background = getIntent().getStringExtra("background");
       String schoolName = getIntent().getStringExtra("schoolName");
       String deathDate = getIntent().getStringExtra("deathDate");
       String birthPlace = getIntent().getStringExtra("birthPlace");
       String lifeHistory = getIntent().getStringExtra("lifeHistory");
       String deathHistory = getIntent().getStringExtra("deathHistory");
       String imagePath = getIntent().getStringExtra("image_path");


        // Set title and description
       binding.name.setText(name);
       binding.background.setText(background);
       binding.SchoolName.setText(schoolName);
       binding.DeathDate.setText(deathDate);
       binding.BirthPlace.setText(birthPlace);
       binding.lifeHistory.setText(lifeHistory);
       binding.deathHistory.setText(deathHistory);

        // Load Bitmap from file
        FileInputStream inputStream;
        Bitmap bitmap = null;
        try {
            inputStream = openFileInput(imagePath);
            bitmap = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the Bitmap to ImageView
        if (bitmap != null) {
            binding.img.setImageBitmap(bitmap);
        } else {
            // Handle the case where bitmap is null
            Log.e("NewsDetailsActivity", "Bitmap is null");
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}