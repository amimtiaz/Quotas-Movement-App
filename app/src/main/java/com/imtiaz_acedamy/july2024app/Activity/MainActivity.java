package com.imtiaz_acedamy.july2024app.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.imtiaz_acedamy.july2024app.Fragment.HomeFragment;
import com.imtiaz_acedamy.july2024app.Fragment.NewsListFragment;
import com.imtiaz_acedamy.july2024app.Fragment.PictureFragment;
import com.imtiaz_acedamy.july2024app.Fragment.SohidListFragment;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private Handler sliderHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        bottomNavigationView();
//        autoSlideImages();
//        initNewsInit();
//        sohidInit();


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new HomeFragment());
        fragmentTransaction.commit();


        // Example of adding a fragment to the container
        if (savedInstanceState == null) {
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentTransaction2.add(R.id.frameLayout, new HomeFragment()); // Add your initial fragment
            fragmentTransaction2.commit();
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("show_fragment")) {
            String fragmentToShow = intent.getStringExtra("show_fragment");
            if ("news_list".equals(fragmentToShow)) {
                showNewsListFragment(); // Show the NewsListFragment if the intent specifies it
            }
        }

        //sohid
        // Example of adding a fragment to the container
        if (savedInstanceState == null) {
            FragmentManager fragmentManager2 = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            fragmentTransaction2.add(R.id.frameLayout, new HomeFragment()); // Add your initial fragment
            fragmentTransaction2.commit();
        }

        Intent intent2 = getIntent();
        if (intent2 != null && intent2.hasExtra("show_fragment")) {
            String fragmentToShow = intent2.getStringExtra("show_fragment");
            if ("sohid_list".equals(fragmentToShow)) {
                showSohidListFragment(); // Show the NewsListFragment if the intent specifies it
            }
        }

    }

    public void showSohidListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new SohidListFragment()); // Replace with your desired fragment
        fragmentTransaction.addToBackStack(null); // Optional: Add to back stack if you want to be able to navigate back
        fragmentTransaction.commit();
    }

    public void showNewsListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, new NewsListFragment()); // Replace with your desired fragment
        fragmentTransaction.addToBackStack(null); // Optional: Add to back stack if you want to be able to navigate back
        fragmentTransaction.commit();
    }


    @SuppressLint("NonConstantResourceId")
    private void bottomNavigationView() {
        // Set home as the default selected item
        binding.bottomNavigationView.setSelectedItemId(R.id.menu_home);
        // Set the default icon for the home item to be active
        binding.bottomNavigationView.getMenu().findItem(R.id.menu_home).setIcon(R.drawable.home_active);
        binding.bottomNavigationView.getMenu().findItem(R.id.menu_home).getIcon()
                .setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));

        // Load the HomeFragment by default
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new HomeFragment())
                .commit();

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Reset all icons to their default state
                resetMenuIcons();

                // Handle navigation and set the selected icon and tint
                switch (item.getItemId()) {
                    case R.id.menu_home:
                        item.setIcon(R.drawable.home_active);
                        item.getIcon().setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));

                        // Replace with HomeFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new HomeFragment())
                                .commit();
                        break;

                    case R.id.video_icon:
                        item.setIcon(R.drawable.active_video_icon);
                        item.getIcon().setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));

                        // Replace with HomeFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new PictureFragment())
                                .commit();
                        break;


                    case R.id.add_icon:
                        item.setIcon(R.drawable.add_active);
                        item.getIcon().setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));

                        // Show the bottom sheet dialog
                        showBottomSheetDialog();
                        break;

                    case R.id.search_icon:
                        item.setIcon(R.drawable.news_acitve);
                        item.getIcon().setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));

                        // Replace with NewsListFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new NewsListFragment())
                                .commit();
                        break;

                    case R.id.list_icon:
                        item.setIcon(R.drawable.list_activie); // Ensure this ID is correct
                        item.getIcon().setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));

                        // Replace with SohidListFragment
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frameLayout, new SohidListFragment())
                                .commit();
                        break;

                    default:
                        item.setIcon(R.drawable.home_active);
                        item.getIcon().setTint(ContextCompat.getColor(getApplicationContext(), R.color.yellow_color));
                        break;
                }
                return true;
            }
        });
    }

    public void setBottomNavigationItem(int itemId) {
        binding.bottomNavigationView.setSelectedItemId(itemId);
        // Optionally update icons or perform additional actions here
    }


    // Helper method to reset all menu icons to their default state
    private void resetMenuIcons() {
        binding.bottomNavigationView.getMenu().findItem(R.id.menu_home).setIcon(R.drawable.home_inactive);
        binding.bottomNavigationView.getMenu().findItem(R.id.add_icon).setIcon(R.drawable.add_inacitive);
        binding.bottomNavigationView.getMenu().findItem(R.id.search_icon).setIcon(R.drawable.news_icon);
        binding.bottomNavigationView.getMenu().findItem(R.id.list_icon).setIcon(R.drawable.list_inactive);
    }



    private void showBottomSheetDialog() {

        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, null);

        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(view);


        LinearLayout addNewInfo = view.findViewById(R.id.addNewInfo);
        LinearLayout addNewInfo2 = view.findViewById(R.id.addNewInfo2);
        LinearLayout addNewInfo3 = view.findViewById(R.id.addNewInfo3);

        addNewInfo.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNewInfoActivity.class));
            finish();
        });

        addNewInfo2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNewInfoActivity.class));
            finish();
        });

        addNewInfo3.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddNewInfoActivity.class));
            finish();
        });

        bottomSheetDialog.show();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            //viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        //sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //sliderHandler.postDelayed(sliderRunnable, 2000);

    }

}