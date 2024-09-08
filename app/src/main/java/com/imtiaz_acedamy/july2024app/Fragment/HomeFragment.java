package com.imtiaz_acedamy.july2024app.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.imtiaz_acedamy.july2024app.Activity.AddNewInfoActivity;
import com.imtiaz_acedamy.july2024app.Activity.MainActivity;
import com.imtiaz_acedamy.july2024app.Adapter.EmoAdapter;
import com.imtiaz_acedamy.july2024app.Adapter.EmoAdapterHome;
import com.imtiaz_acedamy.july2024app.Adapter.NewsAdapter;
import com.imtiaz_acedamy.july2024app.Adapter.SliderAdapter;
import com.imtiaz_acedamy.july2024app.Adapter.Sohid_Adapter;
import com.imtiaz_acedamy.july2024app.Domain.EmoItmes;
import com.imtiaz_acedamy.july2024app.Domain.NewsItems;
import com.imtiaz_acedamy.july2024app.Domain.SliderItem;
import com.imtiaz_acedamy.july2024app.Domain.Sohid_items;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    Context context;
    private Handler sliderHandler = new Handler();
    private ViewPager2 viewPager2;
    Resources resources;

    ArrayList<Sohid_items> list;
    ArrayList<EmoItmes> list2;
    ArrayList<NewsItems> list3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        autoSlideImages();
        initNewsInit();
        sohidInit();

//        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
//        if (!preferences.contains("selected_language")) {
//            saveLanguagePreference("bn"); // Set default language to Bengali
//            setLocale("bn"); // Apply default language settings
//        } else {
//            String selectedLanguage = preferences.getString("selected_language", "bn");
//            setLocale(selectedLanguage); // Apply saved language preference
//        }
//
//
//        switchBtn();



        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Safe initialization of SharedPreferences and setting default language
        try {
            initializeLanguageSettings();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize button states and listeners
        switchBtn();
        gallaryIntit();

    }

    private void gallaryIntit() {

        list2 = new ArrayList<>();

        list2.add(new EmoItmes(R.drawable.emo_pic_1, "ছেলের মৃত্যুতে বাবা রতন চন্দ্র তরুয়ার আহাজারি"));
        list2.add(new EmoItmes(R.drawable.emo_pic_2, "ছাত্র আন্দোলনে আহত এক শিক্ষার্থীকে নিয়ে যাচ্ছেন কয়েকজন"));
        list2.add(new EmoItmes(R.drawable.emo_pic_3, "ঢাকা বিশ্ববিদ্যালয়ে কয়েকজন শহীদ ছাত্রের জানাজা"));

        binding.view3.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.view3.setAdapter(new EmoAdapterHome(list2));



        binding.seeAllView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PictureFragment pictureFragment = new PictureFragment();

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout, pictureFragment)
                        .addToBackStack(null)
                        .commit();


                if (getActivity() instanceof MainActivity){
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setBottomNavigationItem(R.id.video_icon);
                }

            }
        });
    }

    private void initializeLanguageSettings() {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);

        // Check if "selected_language" is already set; if not, default to Bengali
        String selectedLanguage = preferences.getString("selected_language", "bn");

        if (!preferences.contains("selected_language")) {
            saveLanguagePreference("bn"); // Set default language to Bengali
            setLocale("bn"); // Apply default language settings
        } else {
            setLocale(selectedLanguage); // Apply saved language preference
        }
    }

    private void switchBtn() {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        String selectedLanguage = preferences.getString("selected_language", "bn");

        if ("bn".equals(selectedLanguage)) {
            // Set styles for Bengali
            binding.bnBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
            binding.bnBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.yellow_color));
            binding.enBtn.setBackgroundResource(R.color.transparent_color);
            binding.enBtn.setBackgroundTintMode(null);
            binding.enBtn.setTextColor(getResources().getColor(R.color.white));
            binding.bnBtn.setTextColor(getResources().getColor(R.color.primary_color));
        } else {
            // Set styles for English
            binding.enBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
            binding.enBtn.setTextColor(getResources().getColor(R.color.primary_color));
            binding.bnBtn.setTextColor(getResources().getColor(R.color.white));
            binding.bnBtn.setBackgroundResource(R.color.transparent_color);
        }

        binding.bnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    binding.bnBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
                    binding.bnBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.yellow_color));
                    binding.enBtn.setBackgroundResource(R.color.transparent_color);
                    binding.enBtn.setBackgroundTintMode(null);
                    binding.enBtn.setTextColor(getResources().getColor(R.color.white));
                    binding.bnBtn.setTextColor(getResources().getColor(R.color.primary_color));

                    saveLanguagePreference("bn");
                    setLocale("bn");
                    requireActivity().recreate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.enBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    binding.enBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
                    binding.enBtn.setTextColor(getResources().getColor(R.color.primary_color));
                    binding.bnBtn.setTextColor(getResources().getColor(R.color.white));
                    binding.bnBtn.setBackgroundResource(R.color.transparent_color);

                    saveLanguagePreference("en");
                    setLocale("en");
                    requireActivity().recreate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setLocale(String langCode) {
        Locale locale = new Locale(langCode);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);

        // Apply the new locale to the current context
        Context context = requireContext();
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        resources.updateConfiguration(config, displayMetrics);

        // No need to recreate the activity or fragment for UI update here
    }

    private void saveLanguagePreference(String langCode) {
        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("selected_language", langCode);
        editor.apply();
    }

//    private void switchBtn(){
//
//        // Restore button state
//        SharedPreferences preferences = requireContext().getSharedPreferences("app_prefs", MODE_PRIVATE);
//        String selectedLanguage = preferences.getString("selected_language", "bn");
//
//
//        if ("bn".equals(selectedLanguage)) {
//            binding.bnBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
//            binding.bnBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.yellow_color));
//            binding.enBtn.setBackgroundResource(R.color.transparent_color);
//            binding.enBtn.setBackgroundTintMode(null);
//            binding.enBtn.setTextColor(getResources().getColor(R.color.white));
//            binding.bnBtn.setTextColor(getResources().getColor(R.color.primary_color));
//
//            //setLocale("bn");
//
//        } else {
//            binding.enBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
//            binding.enBtn.setTextColor(getResources().getColor(R.color.primary_color));
//            binding.bnBtn.setTextColor(getResources().getColor(R.color.white));
//            binding.bnBtn.setBackgroundResource(R.color.transparent_color);
//
//            //setLocale("en");
//        }
//
//        binding.bnBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    binding.bnBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
//                    binding.bnBtn.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.yellow_color));
//                    binding.enBtn.setBackgroundResource(R.color.transparent_color);
//                    binding.enBtn.setBackgroundTintMode(null);
//                    binding.enBtn.setTextColor(getResources().getColor(R.color.white));
//                    binding.bnBtn.setTextColor(getResources().getColor(R.color.primary_color));
//
//                    saveLanguagePreference("bn");
//                    setLocale("bn");
//                    requireActivity().recreate();
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        binding.enBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    binding.enBtn.setBackgroundResource(R.drawable.flip_en_bn_bg);
//                    binding.enBtn.setTextColor(getResources().getColor(R.color.primary_color));
//                    binding.bnBtn.setTextColor(getResources().getColor(R.color.white));
//                    binding.bnBtn.setBackgroundResource(R.color.transparent_color);
//
//                    saveLanguagePreference("en");
//
//                    setLocale("en");
//                    requireActivity().recreate();
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private void setLocale(String langCode) {
//        Locale locale = new Locale(langCode);
//        Locale.setDefault(locale);
//
//        Configuration config = new Configuration();
//        config.setLocale(locale);
//
//        // Update resources with new locale
//        Context context = requireContext(); // Use the fragment's context
//        Resources resources = context.getResources();
//        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
//        resources.updateConfiguration(config, displayMetrics);
//
//        if (getActivity() != null) {
//            getActivity().recreate();
//
//        }
//    }
//
//    private void saveLanguagePreference(String langCode) {
//        SharedPreferences preferences = getActivity().getSharedPreferences("app_prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("selected_language", langCode);
//        editor.apply();
//    }




    private void sohidInit() {
        list = new ArrayList<>();

        list.add(new Sohid_items("আবু সাইদ","শিক্ষার্থী","বেগম রোকেয়া বিশ্ববিদ্যালয়, রংপুর","১৬ জুলাই, ২০২৪","বাবনপুর, পীরগঞ্জ, রংপুর","রোকেয়া বিশ্ববিদ্যালয়ের ইংরেজি বিভাগের ১২তম ব্যাচের শিক্ষার্থী আবু সাঈদ ২০০১ সালে রংপুর জেলার পীরগঞ্জ উপজেলার বাবনপুর গ্রামে জন্মগ্রহণ করেন। মকবুল হোসেন এবং মনোয়ারা বেগমের ছয় ছেলে ও তিন মেয়ের মধ্যে আবু সাঈদ ছিলেন সবার ছোট। তিনি স্থানীয় জাফর পাড়া সরকারি প্রাথমিক বিদ্যালয় থেকে পঞ্চম শ্রেণিতে ট্যালেন্টপুলে বৃত্তি পেয়ে স্থানীয় খালাশপীর দ্বিমুখী উচ্চ বিদ্যালয় ভর্তি হন এবং এখান থেকে জি\u200Cপিএ-৫ পে\u200Cয়ে এসএসসি পাশ করে ২০১৮ সালে রংপুর সরকা\u200Cরি কলেজে ভর্তি হন। এখান থেকে জি\u200Cপিএ-৫ পেয়ে ইন্টারমিডিয়েট পাশ করে ২০২০ সালে বেগম রো\u200Cকেয়া\u200C বিশ্ব\u200Cবিদ্যাল\u200Cয়ে ইং\u200Cরে\u200Cজি বিভাগে ভ\u200Cর্তি হন।",
                "১৬ জুলাই দুপুর আড়াইটা থেকে তিনটার দিকে রোকেয়া বিশ্ববিদ্যালয়ের সামনে কোটা সংস্কার আন্দোলনের শিক্ষার্থীদের শান্তিপূর্ণ সমাবেশে ছাত্রদের ছত্রভঙ্গ করার জন্য পুলিশ টিয়ার গ্যাস নিক্ষেপ ও লাঠিচার্জ করলে, ছাত্রদের সবাই স্থান ত্যাগ করলেও আবু সাঈদ হাতে একটি লাঠি নিয়ে দাঁড়িয়ে থাকেন। পুলিশ এই অবস্থায় তার উপরে গুলি ছুড়লে, হাতে থাকা লাঠি দিয়ে রাবার বুলেট ঠেকানোর চেষ্টা করছিলেন। একপর্যায়ে শরীরে একের পর রাবার বুলেটে ক্ষতবিক্ষত হওয়ার পর মাটিতে লুটিয়ে পড়েন আবু সাঈদ। হাসপাতালে নেয়ার আগেই তিনি মৃত্যুবরণ করেন।", R.drawable.abu_said));
        list.add(new Sohid_items("ফয়সাল আহমেদ শান্ত","শিক্ষার্থী","ওমরগণি এম.ই.এস. কলেজ, চট্টগ্রাম","১৬ জুলাই, ২০২৪","পতেঙ্গা, চট্টগ্রাম","ব্যক্তিগত জীবন :\n" +
                "ফয়সাল আহমেদ শান্ত এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","যেভাবে শহীদ হয়েছেন :\n" +
                "ফয়সাল আহমেদ শান্ত কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sohid_2));
        list.add(new Sohid_items("মোঃ ওয়াসিম আকরুম","শিক্ষার্থী","চট্টগ্রাম কলেজ, চট্টগ্রাম","১৭ জুলাই, ২০২৪","_____","মোঃ ওয়াসিম আকরুম এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","মোঃ ওয়াসিম আকরুম কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sohid_3));
//        list.add(new Sohid_items("সাকিব হাসান","শিক্ষার্থী","কাদির মোল্লা হাই স্কুল, নরসিংদী","১৮ জুলাই, ২০২৪","______","সাকিব হাসান এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","সাকিব হাসান কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।","https://shohid.info/shohid/sakib-hasan.jpg"));
        list.add(new Sohid_items("তাহমিদ তামিম","শিক্ষার্থী","চট্টগ্রাম কলেজ, চট্টগ্রাম","১৬ জুলাই, ২০২৪","_____","তাহমিদ তামিম এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","তাহমিদ তামিম কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.tahmid_tamim));
//        list.add(new Sohid_items("শাইখ আশহাবুল ইয়ামিন","শিক্ষার্থী","মিলিটারি ইনস্টিটিউট অফ সায়েন্স অ্যান্ড টেকনোলজি, ঢাকা","১৮ জুলাই, ২০২৪","কুমারখালী, কুষ্টিয়া, খুলনা","ইয়ামিন সাভার ক্যান্টনমেন্ট স্কুল অ্যান্ড কলেজ থেকে এসএসসি ও এইচএসসি পাস করেন। তিনি রাজধানীর মিরপুরের মিলিটারি ইনস্টিটিউট অব সায়েন্স অ্যান্ড টেকনোলজির (এমআইএসটি) কম্পিউটারবিজ্ঞান ও প্রকৌশল বিভাগের চতুর্থ বর্ষের শিক্ষার্থী ছিলেন, থাকতেন এমআইএসটির ওসমানী হলে। ইয়ামিন সাহসী ছিলেন। অন্যায় দেখলেই প্রতিবাদ করতেন। ইয়ামিন অত্যন্ত মেধাবী ছিলেন। বন্ধুরা কোনো বিষয় বুঝতে না পারলে ইয়ামিন বুঝিয়ে দিতেন। ইয়ামিন বিতর্ক করতেন। এমআইএসটিতে বিতর্ক ক্লাবের ভাইস প্রেসিডেন্ট ছিলেন ইয়ামিন। ছেলে বড় হলেও অবসরে ছোট বাচ্চাদের মতো ‘টম অ্যান্ড জেরি’ থেকে শুরু করে বিভিন্ন কার্টুন দেখতেন বলে জানান তার বাবা।","পুলিশের গুলিতে নিহত","https://shohid.info/shohid/shykh-aashhabul-yamin.jpg"));
//        list.add(new Sohid_items("মীর মুগ্ধ","শিক্ষার্থী","বাংলাদেশ ইউনিভার্সিটি অফ প্রফেশনালস"," ১৮ জুলাই, ২০২৪","----------","মীর মুগ্ধ এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","মীর মুগ্ধ কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।","https://shohid.info/shohid/mir-mugdho.jpg"));
//        list.add(new Sohid_items("আব্দুল্লাহ আল তাহির","গ্রাফিক্স ডিজাইনার","সৃজন গ্রাফিক্স প্রিন্টার্স, সিরাজ মার্কেট রোড, উত্তরা, ঢাকা","১৮ জুলাই, ২০২৪","","আব্দুল্লাহ আল তাহির এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","গত ১৮ জুলাই বৈষম্যবিরোধী আন্দোলনে গুলিবিদ্ধ হয়ে শাহাদাত বরণ করেছেন","https://shohid.info/shohid/abdullah-al-tahir.jpg"));
//        list.add(new Sohid_items("খালিদ হাসান সাইফুল্লাহ","শিক্ষার্থী","ইম্পেরিয়াল কলেজ, ঢাকা","১৮ জুলাই, ২০২৪","লালবাগ, ঢাকা","হাফেজ আব্দুর রাজ্জাক মাদ্রাসার শিক্ষক কামরুল হাসানের সন্তান।","খালিদ হাসান সাইফুল্লাহ কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।","https://shohid.info/shohid/kalid-hasan-saifullah.jpg"));
//        list.add(new Sohid_items("রুদ্র সেন","শিক্ষার্থী","শাহজালাল বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়, সিলেট","১৮ জুলাই, ২০২৪","দিনাজপুর, রাজশাহী","রুদ্র সেন এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","রুদ্র সেন কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।","https://shohid.info/shohid/rudro-sen.jpg"));
        //list.add(new Sohid_items("","","","","","","",""));


        binding.view2.post(() -> {
            // Get the height of one item
            RecyclerView.ViewHolder viewHolder = binding.view2.findViewHolderForAdapterPosition(0);
            if (viewHolder != null) {
                View itemView = viewHolder.itemView;
                int itemHeight = itemView.getHeight();

                // Set RecyclerView height to display exactly 4 items
                ViewGroup.LayoutParams params = binding.view2.getLayoutParams();
                params.height = (int) (itemHeight * 4.3);

                binding.view2.setLayoutParams(params);
            }
        });

        List<Sohid_items> sublist = list.subList(0, Math.min(4, list.size()));
        ArrayList<Sohid_items> limitedList = new ArrayList<>(sublist);


        binding.view2.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false){
            @Override
            public boolean canScrollVertically() {
                return false; // Disable scrolling
            }
        });
        binding.view2.setAdapter(new Sohid_Adapter(limitedList));


        binding.seeAllView.setOnClickListener(v -> {

//            Intent intent = new Intent(context, SohidListFragment.class);
//            intent.putExtra("data_list", list); // 'list' is your ArrayList
//            startActivity(intent);

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new SohidListFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();


            if (getActivity() instanceof MainActivity){
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setBottomNavigationItem(R.id.list_icon);
            }

        });


        binding.seeAllView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, new NewsListFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


                if (getActivity() instanceof MainActivity){
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setBottomNavigationItem(R.id.search_icon);
                }

            }
        });

    }



    private void initNewsInit(){
        list3 = new ArrayList<>();

        list3.add(new NewsItems("বৈষম্যবিরোধী ছাত্র আন্দোলনের তিন দাবি","২ ঘন্টা আগে", "সোমবার বিকালে ঢাকা বিশ্ববিদ্যালয়ের সন্ত্রাসবিরোধী রাজু ভাস্কর্যের পাদদেশে বৈষম্যবিরোধী ছাত্র আন্দোলনের উদ্যোগে " +
                "‘খুনি শেখ হাসিনার বিচার দাবি ও ক্যাম্পাসে দখলদারিত্বের রাজনীতি বন্ধের’ দাবিতে সমাবেশ হয়। এ সময় খুনিদের পুনর্বাসন করার ষড়যন্ত্র হচ্ছে জানিয়ে সমন্বয়ক হাসনাত" +
                " আব্দুল্লাহ উপদেষ্টাদের বক্তব্যের সমালোচনা করেন এবং তিন দফা দাবি তুলে ধরেন। কোটা সংস্কার আন্দোলনকারীদের প্লাটফর্ম বৈষম্যবিরোধী ছাত্র আন্দোলনের কমিটি ৬৫ সদস্য থেকে বাড়িয়ে ১৫৮ সদস্য করা হয়েছে।\n" +
                "\n" +
                "শনিবার বৈষম্যবিরোধী ছাত্র আন্দোলনের প্যাডে এক বিজ্ঞপ্তিতে এ ঘোষণা দেন আন্দোলনের অন্যতম সমন্বয়ক রিফাত রশিদ।\n" +
                "\n" +
                "বিজ্ঞপ্তিতে বলা হয়, “বৈষম্যবিরোধী ছাত্র আন্দোলনে নির্মমভাবে চালানো গণহত্যার বিচার, গণহত্যায় দায়ীদের পদত্যাগ এবং গ্রেপ্তারদের মুক্তির দাবিতে সারা দেশে আন্দোলনরত শিক্ষার্থী প্রতিনিধিদের নিয়ে ১৫৮ সদস্য বিশিষ্ট সমন্বয়ক টিম গঠন করা হল।”\n" +
                "\n" +
                "আন্দোলনের সুবিধার্থে সামনের দিনে দেশের বিভিন্ন প্রতিষ্ঠানের সমন্বয়কদের নিয়ে সমন্বয়ক টিম বর্ধিত করা হবে বলেও বিজ্ঞপ্তিতে জানানো হয়।\n" +
                "\n" +
                "বর্ধিত কমিটিতে সমন্বয়ক হিসেবে ৪৯ জন এবং সহ-সমন্বয়ক হিসেবে ১০৯ জন শিক্ষার্থীকে রাখা হয়েছে।\n" +
                "\n" +
                "চাকরিতে কোটা বাতিল করে ২০১৮ সালে জারি করা পরিপত্রটি হাই কোর্ট বাতিল করার প্রতিক্রিয়ায় আন্দোলনে নামে শিক্ষার্থীরা। কোটা আন্দোলনের কর্মসূচি বাস্তবায়নে গত ৮ জুলাই ৬৫ সদস্য বিশিষ্ট সমন্বয়ক টিম গঠন করে বৈষম্যবিরোধী ছাত্র আন্দোলন।\n" +
                "\n" +
                "ওই কমিটিতে সমন্বয়ক হিসেবে ২৩ জন এবং ৪২ জনকে সহ-সমন্বয়ক হিসেবে রাখা হয়েছিল। এবার কমিটি বর্ধিত করে এখন ১৫৮ সদস্যের করা হল।", R.drawable.news_img_1, "জামাল খান, চট্টগ্রাম"));
        list3.add(new NewsItems("আজ যে কর্মসূচি পালন করবে বৈষম্যবিরোধী ছাত্র আন্দোলন","৪ ঘন্টা আগে", "শেখ হাসিনার বিচারসহ চার দফা দাবিতে সপ্তাহব্যাপী কর্মসূচির ডাক দিয়েছে বৈষম্যবিরোধী ছাত্র আন্দোলন। ‘রেজিস্টান্স উইক’-এর অংশ হিসেবে আজ বুধবার (১৪ আগস্ট) বিকেল সাড়ে ৪টায় শাহবাগে অবস্থান কর্মসূচি এবং শহীদদের স্মরণে শাহবাগ থেকে রাফা প্লাজা অভিমুখে পদযাত্রা, মোমবাতি প্রজ্জ্বলন ও প্রার্থনা কর্মসূচির আয়োজন করা হয়েছে।\n" +
                "\n" +
                "বৈষম্যবিরোধী ছাত্র আন্দোলনের সমন্বয়ক হাসনাত আব্দুল্লাহ নিজের ফেসবুক লাইভে বলেন, শিক্ষার্থী মুগ্ধ-ফাইয়াজসহ যেসব সহযোদ্ধারা রাজপথে রক্ত ঝড়িয়ে নিজের জীবন দিয়ে শহীদ হয়েছেন, তাদের স্মরণে আজ বিকেল সাড়ে ৪টায় জমায়েত হওয়ার আহ্বান জানানো হচ্ছে শিক্ষার্থীদের।\n" +
                "\n" +
                "তিনি আরও বলেন, সেখান থেকে মিছিল শুরু হবে। মিছিল প্রদক্ষিণ করে ধানমন্ডি ৩২-এর রাফা প্লাজা ভবনের সামনে গিয়ে মুগ্ধ-ফাইয়াজসহ অন্যান্য শহীদকে স্মরণ করা হবে। সেখানে আমরা মোমবাতি প্রজ্বলন করব এবং ধর্মীয় বিধান অনুযায়ী প্রার্থনা করব।\n" +
                "\n" +
                "সমন্বয়ক হাসনাত আব্দুল্লাহ বলেন, ধানমন্ডির ওই এলাকার স্কুল-কলেজের সকল শিক্ষার্থী রাফা প্লাজা অভিমুখে যাত্রা করবেন এবং তাদের এই কর্মসূচিতে অংশগ্রহণ করার আহ্বান জানানো হচ্ছে।\n" +
                "\n" +
                "বৈষম্যবিরোধী ছাত্র আন্দোলনের চার দফা দাবিগুলো হলো–\n" +
                "\n" +
                "১ ) ফ্যাসিবাদী কাঠামোকে ব্যবহার করে ফ্যাসিস্ট হাসিনা এবং তার দল ও সরকার যে হত্যাযজ্ঞ চালিয়েছে, সেগুলোর দ্রুত বিচার নিশ্চিত করতে বিশেষ ট্রাইব্যুনাল গঠন করতে হবে।\n" +
                "\n" +
                "২ ) সংখ্যালঘুদের ওপর আওয়ামী লীগ ও এর সহযোগী মহাজোটের শরিক দলগুলোর পরিকল্পিত হত্যা, ডাকাতি ও লুণ্ঠনের মাধ্যমে গণ-অভ্যুত্থানকে বিতর্কিত করার প্রচেষ্টায় অংশগ্রহণকারীদের দ্রুত বিচারের আওতায় আনতে হবে এবং সংখ্যালঘুদের ন্যায্য দাবি মেনে নিতে হবে।\n" +
                "\n" +
                "৩ ) প্রশাসন ও বিচার বিভাগে যারা ছাত্র-জনতার অভ্যুত্থানে হামলা, মামলা এবং হত্যাযজ্ঞকে বৈধতা দিয়েছে এবং ফ্যাসিবাদী ব্যবস্থা বারবার কায়েমের চেষ্টা করছে, তাদের দ্রুততম সময়ে অপসারণ ও নতুন সরকারে তাদের নিয়োগ বাতিল করে বিচারের আওতায় আনতে হবে।\n" +
                "\n" +
                "৪ ) প্রশাসন ও বিচার বিভাগে যারা এতদিন বৈষম্যের শিকার হয়েছে, তাদের জন্য দ্রুততম সময়ে সুযোগের সমতা নিশ্চিত করতে হবে।", R.drawable.news_img_2, "তিতুমির হল,রাজশাহী"));

        list3.add(new NewsItems("এবার ‘কমপ্লিট শাটডাউন’ ঘোষণা বৈষম্যবিরোধী ছাত্র আন্দোলনের","১ ঘন্টা আগে", "সারা দেশে বৃহস্পতিবার থেকে 'কমপ্লিট শাটডাউন' কর্মসূচি ঘোষণা করেছে বৈষম্যবিরোধী ছাত্র আন্দোলন।\n" +
                "\n" +
                "বুধবার সন্ধ্যায় এ ঘোষণা দেন বৈষম্যবিরোধী ছাত্র আন্দোলনের অন্যতম সমন্বয়ক আসিফ মাহমুদ।\n" +
                "\n" +
                "কর্মসূচির বিস্তারিত জানিয়ে রাত পৌনে ৮টায় ফেসবুকে একটি পোস্ট দিয়েছেন আসিফ মাহমুদ।\n" +
                "\n" +
                "‘যা বাবা, ভালো থাকিস চিন্তা করিস না’, সাঈদকে চিরবিদায় কালে মা\n" +
                "\n" +
                "তিনি লেখেন, 'শিক্ষার্থীদের শান্তিপূর্ণ আন্দোলনে ওপর পুলিশ, বিজিবি, র\u200D্যাব, সোয়াটের ন্যাক্কারজনক হামলা, খুনের প্রতিবাদ, খুনিদের বিচার, সন্ত্রাসমুক্ত ক্যাম্পাস নিশ্চিত ও এক দফা দাবিতে আগামীকাল ১৮ই জুলাই সারা দেশে ‘কমপ্লিট শাটডাউন’ ঘোষণা করছি।'\n" +
                "\n" +
                "'শুধুমাত্র হাসপাতাল ও জরুরি সেবা ব্যতীত কোনো প্রতিষ্ঠানের দরজা খুলবে না, অ্যাম্বুলেন্স ব্যতীত সড়কে কোনো গাড়ি চলবে না। সারা দেশের প্রতিটি স্কুল, কলেজ, বিশ্ববিদ্যালয়, প্রাইভেট বিশ্ববিদ্যালয়, মাদরাসা শিক্ষার্থীদের আহ্বান জানাচ্ছি আগামীকালকের কর্মসূচি সফল করুন।\n" +
                "\n" +
                "তিনি আরও লেখেন, 'আমাদের অভিভাবকদের বলছি, আমরা আপনাদেরই সন্তান। আমাদের পাশে দাঁড়ান, রক্ষা করুন। এই লড়াইটা শুধু ছাত্রদের না, দলমত নির্বিশেষে এদেশের আপামর জনসাধারণের'।", R.drawable.news_img_3, "২ নং গেইট, চট্টগ্রাম"));


        binding.view1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.view1.setAdapter(new NewsAdapter(list3));


    }


    private void autoSlideImages() {

        ViewPager2 viewPager2 = binding.viewPagerImageSlider; // Use binding to get the reference
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.slider_imgeview));
        sliderItems.add(new SliderItem(R.drawable.slider_2));
        sliderItems.add(new SliderItem(R.drawable.slider_3));
        sliderItems.add(new SliderItem(R.drawable.slider_4));
        sliderItems.add(new SliderItem(R.drawable.slider_imgeview));

        viewPager2.setAdapter(new SliderAdapter(sliderItems, viewPager2));
        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(3);

        // Check if ViewPager2's child exists to prevent NullPointerException
        if (viewPager2.getChildCount() > 0) {
            viewPager2.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
        }

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        binding.dotIndicator.setViewPager2(viewPager2);
        viewPager2.setPageTransformer(compositePageTransformer);

        // Setting up auto-slide using a valid reference of ViewPager2
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        // Define the Runnable properly using the initialized ViewPager2 reference
        sliderRunnable = new Runnable() {
            @Override
            public void run() {
                if (viewPager2 != null) { // Ensure ViewPager2 is not null
                    int nextItem = (viewPager2.getCurrentItem() + 1) % sliderItems.size();
                    viewPager2.setCurrentItem(nextItem);
                    sliderHandler.postDelayed(this, 3000);
                }
            }
        };

        // Start the auto-slide
        sliderHandler.postDelayed(sliderRunnable, 3000);
    }



    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    @Override
    public void onPause() {
        super.onPause();
        sliderHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        sliderHandler.postDelayed(sliderRunnable, 2000);

    }

}