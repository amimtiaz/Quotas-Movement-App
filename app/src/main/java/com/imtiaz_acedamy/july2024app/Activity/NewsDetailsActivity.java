package com.imtiaz_acedamy.july2024app.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.imtiaz_acedamy.july2024app.Domain.NewsItems;
import com.imtiaz_acedamy.july2024app.Fragment.HomeFragment;
import com.imtiaz_acedamy.july2024app.Fragment.NewsListFragment;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.ActivityNewsDetailsBinding;

import java.io.FileInputStream;

public class NewsDetailsActivity extends AppCompatActivity {

    ActivityNewsDetailsBinding binding;
    private NewsItems object ;
    public static Bitmap IMG_BITMAP = null;


    public static String title = "";
    public static String description = "";
    public static String caption = "";
    public static String place = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewsDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setTabLayout();
        setVariable();
    }

    private void setTabLayout() {
        binding.backBtn.setOnClickListener(v -> {

            Intent intent = new Intent(NewsDetailsActivity.this, MainActivity.class);
            intent.putExtra("show_fragment", "news_list"); // Pass data to specify which fragment to show
            startActivity(intent);

        });
    }

    private void setVariable(){
        String titleTxt = title;



//        binding.titleTxt.setText(titleTxt);
//        binding.desTxt.setText(description);
//
//        if (IMG_BITMAP != null) binding.img.setImageBitmap(IMG_BITMAP);

        //binding.img.setImageBitmap(bitmap);

//        Glide.with(this)
//                .load(object.getImagePath())
//                .into(binding.img);



// Get the data from Intent
        String title = getIntent().getStringExtra("title");
        String place = getIntent().getStringExtra("place");
        String description = getIntent().getStringExtra("description");
        String imagePath = getIntent().getStringExtra("image_path");

        // Set title and description
        binding.titleTxt.setText(title);
        binding.desTxt.setText(description);
        binding.placeTxt.setText(place);

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
}