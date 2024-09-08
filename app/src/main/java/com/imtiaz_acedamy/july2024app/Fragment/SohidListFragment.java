package com.imtiaz_acedamy.july2024app.Fragment;

import static java.util.Locale.filter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;

import com.imtiaz_acedamy.july2024app.Adapter.Sohid_Adapter;
import com.imtiaz_acedamy.july2024app.Domain.Sohid_items;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.FragmentSohidListBinding;

import java.util.ArrayList;


public class SohidListFragment extends Fragment {

    FragmentSohidListBinding binding;
    Context context;
    ArrayList<Sohid_items> list;
    ArrayList<Sohid_items> filteredList;
    Sohid_Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSohidListBinding.inflate(getLayoutInflater());
        context = getContext();

        init();
        scrollAnimation();

        return binding.getRoot();
    }

    private void init(){
        list = new ArrayList<>();
        filteredList = new ArrayList<>();
        
        populateList();

        binding.view3.setLayoutManager(new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false));

        // Initialize adapter with the full list
        adapter = new Sohid_Adapter(filteredList);
        binding.view3.setAdapter(adapter);

        // Set up the EditText search functionality
        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No action needed before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No action needed after text changes
            }
        });

        // Initially display the full list
        filter("");
    }

    private void populateList() {
        list.add(new Sohid_items("আবু সাইদ","শিক্ষার্থী","বেগম রোকেয়া বিশ্ববিদ্যালয়, রংপুর","১৬ জুলাই, ২০২৪","বাবনপুর, পীরগঞ্জ, রংপুর","রোকেয়া বিশ্ববিদ্যালয়ের ইংরেজি বিভাগের ১২তম ব্যাচের শিক্ষার্থী আবু সাঈদ ২০০১ সালে রংপুর জেলার পীরগঞ্জ উপজেলার বাবনপুর গ্রামে জন্মগ্রহণ করেন। মকবুল হোসেন এবং মনোয়ারা বেগমের ছয় ছেলে ও তিন মেয়ের মধ্যে আবু সাঈদ ছিলেন সবার ছোট। তিনি স্থানীয় জাফর পাড়া সরকারি প্রাথমিক বিদ্যালয় থেকে পঞ্চম শ্রেণিতে ট্যালেন্টপুলে বৃত্তি পেয়ে স্থানীয় খালাশপীর দ্বিমুখী উচ্চ বিদ্যালয় ভর্তি হন এবং এখান থেকে জি\u200Cপিএ-৫ পে\u200Cয়ে এসএসসি পাশ করে ২০১৮ সালে রংপুর সরকা\u200Cরি কলেজে ভর্তি হন। এখান থেকে জি\u200Cপিএ-৫ পেয়ে ইন্টারমিডিয়েট পাশ করে ২০২০ সালে বেগম রো\u200Cকেয়া\u200C বিশ্ব\u200Cবিদ্যাল\u200Cয়ে ইং\u200Cরে\u200Cজি বিভাগে ভ\u200Cর্তি হন।",
                "১৬ জুলাই দুপুর আড়াইটা থেকে তিনটার দিকে রোকেয়া বিশ্ববিদ্যালয়ের সামনে কোটা সংস্কার আন্দোলনের শিক্ষার্থীদের শান্তিপূর্ণ সমাবেশে ছাত্রদের ছত্রভঙ্গ করার জন্য পুলিশ টিয়ার গ্যাস নিক্ষেপ ও লাঠিচার্জ করলে, ছাত্রদের সবাই স্থান ত্যাগ করলেও আবু সাঈদ হাতে একটি লাঠি নিয়ে দাঁড়িয়ে থাকেন। পুলিশ এই অবস্থায় তার উপরে গুলি ছুড়লে, হাতে থাকা লাঠি দিয়ে রাবার বুলেট ঠেকানোর চেষ্টা করছিলেন। একপর্যায়ে শরীরে একের পর রাবার বুলেটে ক্ষতবিক্ষত হওয়ার পর মাটিতে লুটিয়ে পড়েন আবু সাঈদ। হাসপাতালে নেয়ার আগেই তিনি মৃত্যুবরণ করেন।", R.drawable.sohid_1));
        list.add(new Sohid_items("ফয়সাল আহমেদ শান্ত","শিক্ষার্থী","ওমরগণি এম.ই.এস. কলেজ, চট্টগ্রাম","১৬ জুলাই, ২০২৪","পতেঙ্গা, চট্টগ্রাম","ব্যক্তিগত জীবন :\n" +
                "ফয়সাল আহমেদ শান্ত এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","যেভাবে শহীদ হয়েছেন :\n" +
                "ফয়সাল আহমেদ শান্ত কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sohid_2));
        list.add(new Sohid_items("মোঃ ওয়াসিম আকরুম","শিক্ষার্থী","চট্টগ্রাম কলেজ, চট্টগ্রাম","১৭ জুলাই, ২০২৪","_____","মোঃ ওয়াসিম আকরুম এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","মোঃ ওয়াসিম আকরুম কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sohid_3));
        list.add(new Sohid_items("সাকিব হাসান","শিক্ষার্থী","কাদির মোল্লা হাই স্কুল, নরসিংদী","১৮ জুলাই, ২০২৪","______","সাকিব হাসান এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","সাকিব হাসান কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sakib_hasan));
        list.add(new Sohid_items("তাহমিদ তামিম","শিক্ষার্থী","চট্টগ্রাম কলেজ, চট্টগ্রাম","১৬ জুলাই, ২০২৪","_____","তাহমিদ তামিম এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","তাহমিদ তামিম কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.tahmid_tamim));
        list.add(new Sohid_items("শাইখ আশহাবুল ইয়ামিন","শিক্ষার্থী","মিলিটারি ইনস্টিটিউট অফ সায়েন্স অ্যান্ড টেকনোলজি, ঢাকা","১৮ জুলাই, ২০২৪","কুমারখালী, কুষ্টিয়া, খুলনা","ইয়ামিন সাভার ক্যান্টনমেন্ট স্কুল অ্যান্ড কলেজ থেকে এসএসসি ও এইচএসসি পাস করেন। তিনি রাজধানীর মিরপুরের মিলিটারি ইনস্টিটিউট অব সায়েন্স অ্যান্ড টেকনোলজির (এমআইএসটি) কম্পিউটারবিজ্ঞান ও প্রকৌশল বিভাগের চতুর্থ বর্ষের শিক্ষার্থী ছিলেন, থাকতেন এমআইএসটির ওসমানী হলে। ইয়ামিন সাহসী ছিলেন। অন্যায় দেখলেই প্রতিবাদ করতেন। ইয়ামিন অত্যন্ত মেধাবী ছিলেন। বন্ধুরা কোনো বিষয় বুঝতে না পারলে ইয়ামিন বুঝিয়ে দিতেন। ইয়ামিন বিতর্ক করতেন। এমআইএসটিতে বিতর্ক ক্লাবের ভাইস প্রেসিডেন্ট ছিলেন ইয়ামিন। ছেলে বড় হলেও অবসরে ছোট বাচ্চাদের মতো ‘টম অ্যান্ড জেরি’ থেকে শুরু করে বিভিন্ন কার্টুন দেখতেন বলে জানান তার বাবা।","পুলিশের গুলিতে নিহত", R.drawable.wasim));
        list.add(new Sohid_items("মীর মুগ্ধ","শিক্ষার্থী","বাংলাদেশ ইউনিভার্সিটি অফ প্রফেশনালস"," ১৮ জুলাই, ২০২৪","----------","মীর মুগ্ধ এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","মীর মুগ্ধ কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.mir_mogdo));
        list.add(new Sohid_items("আব্দুল্লাহ আল তাহির","গ্রাফিক্স ডিজাইনার","সৃজন গ্রাফিক্স প্রিন্টার্স, সিরাজ মার্কেট রোড, উত্তরা, ঢাকা","১৮ জুলাই, ২০২৪","","আব্দুল্লাহ আল তাহির এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","গত ১৮ জুলাই বৈষম্যবিরোধী আন্দোলনে গুলিবিদ্ধ হয়ে শাহাদাত বরণ করেছেন",R.drawable.abdullah_al_tahir));
        list.add(new Sohid_items("খালিদ হাসান সাইফুল্লাহ","শিক্ষার্থী","ইম্পেরিয়াল কলেজ, ঢাকা","১৮ জুলাই, ২০২৪","লালবাগ, ঢাকা","হাফেজ আব্দুর রাজ্জাক মাদ্রাসার শিক্ষক কামরুল হাসানের সন্তান।","খালিদ হাসান সাইফুল্লাহ কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sohid_10));
        list.add(new Sohid_items("রুদ্র সেন","শিক্ষার্থী","শাহজালাল বিজ্ঞান ও প্রযুক্তি বিশ্ববিদ্যালয়, সিলেট","১৮ জুলাই, ২০২৪","দিনাজপুর, রাজশাহী","রুদ্র সেন এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","রুদ্র সেন কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.sohid_11));
        list.add(new Sohid_items("ইরফান ভূঁইয়া","শিক্ষার্থী","ইউনাইটেড ইন্টারন্যাশনাল ইউনিভার্সিটি, ঢাকা","১৯ জুলাই, ২০২৪","----------","ইরফান ভূঁইয়া এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।",
                "ইরফান ভূঁইয়া কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।",R.drawable.irfan_boiya));

        list.add(new Sohid_items("আবু জাফর","বাস চালক","শ্যামলী পরিবহন","১৮ জুলাই, ২০২৪","ছোট মাছুয়া, মঠবাড়িয়া, পিরোজপুর","আবু জাফর এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","- সূত্র: প্রথম আলো",R.drawable.abu_jafor));
        list.add(new Sohid_items("আমজাদ হোসেন","শিক্ষার্থী","সরকারি শহীদ আসাদ কলেজ, শিবপুর, নরসিংদী","১৮ জুলাই, ২০২৪","নরসিংদী","\n" +
                "আমজাদ হোসেন এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","পুলিশের ছোড়া গুলি বিদ্ধ হয়ে মারা যান",R.drawable.amjad_hosen));
        list.add(new Sohid_items("হাসান হোসেন","শিক্ষার্থী","শেখ মুজিবুর রহমান ডিগ্রি কলেজ","১৮ জুলাই, ২০২৪","তুলাতলী, মনোহরপুর, কচুয়া, চাঁদপুর","হাসান হোসেন এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","ঢাকার বাড্ডায় অবস্থিত কানাডিয়ান বিশ্ববিদ্যালয়ের সামনে শটগানের গুলিতে তিনি মাথায় ও ডান চোখে গুলিবিদ্ধ হন। তবে কে বা কারা গুলি করেছে পুলিশ বা আওয়ামী লীগ, তা স্পষ্ট নয়।",R.drawable.hasan_hossain));
        list.add(new Sohid_items("রেদওয়ান শরীফ রিয়াদ","শিক্ষার্থী","টংগী সরকারি কলেজ","১৯ জুলাই, ২০২৪","নওগাঁ, রাজশাহী","রেদওয়ান শরীফ রিয়াদ এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","রেদওয়ান শরীফ রিয়াদ কি ভাবে মারা গিয়েছেন এই বিষয়ে পর্যাপ্ত তথ্য পাওয়া যায় নি, তথ্য খোঁজার কাজ চলমান।", R.drawable.ridowan_sharif_riyad));
        list.add(new Sohid_items("মাহামুদুর রহমান সৈকত","শিক্ষার্থী","সরকারী মোহাম্মদপুর মডেল স্কুল এন্ড কলেজ","১৯ জুলাই, ২০২৪","সন্দ্বীপ, চট্টগ্রাম","মাহামুদুর রহমান সৈকত ২০০৪ সালের ১১ সেপ্টেম্বর জানা জন্মগ্রহণ করেন।তিনি ২০২৩ এ সরকারি মোহাম্মদপুর মডেল স্কুল এন্ড কলেজ থেকে Hsc পাস করেন।মাহাবুবের রহমান ও আফরোজা রহমানের ৩ ছেলেমেয়ের মাঝে তিনিই একমাত্র এবং সবচেয়ে ছোট ছেলে।তার গ্রামের বাড়ি চট্টগ্রাম জেলার সন্দীপ থানায়।","১৯ জুলাই দুপুর ৩:৩৭ এ তাকে রাজিয়া সুলতানা রোড এর মাথায় স্টেপ জুতার দোকানের পাশের গলিতে গুলি করা হয়।তিনি কোন ধরনের রাজনৈতিক বিষয়ের সাথে জড়িত ছিলেন না।তিনি তার বাবার দোকানের শাটার বন্ধ করার উদ্দেশ্যে বের হয়েছিলেন এবং এক বন্ধুর গায়ে গুলি লাগায় তাকে বাঁচাতে গিয়েছিলেন।হাসপাতালে নেওয়ার আগেই তিনি মৃত্যুবরণ করেন।",R.drawable.rahman_soikot ));

        list.add(new Sohid_items("নাইমা সুলতানা","শিক্ষার্থী","মাইলস্টোন স্কুল","১৯ জুলাই, ২০২৪","আমুয়াকান্দা, মতলব উত্তর, চাঁদপুর","নাইমা সুলতানা এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।","উত্তরার ৫ নম্বর সড়কে ভাড়া বাসার চারতলার বারান্দায় গত শুক্রবার গুলিবিদ্ধ হয়ে মারা যায়। - সূত্র: প্রথম আলো", R.drawable.naima_sultana));
        list.add(new Sohid_items("আসিফ হাসান","শিক্ষার্থী","নর্দান ইউনিভার্সিটি বাংলাদেশ","১৮ জুলাই, ২০২৪","আস্কারপুর, দেবহাটা, সাতক্ষীরা","১৮ জুলাই ঢাকার উত্তরায় বিক্ষোভকারীদের সঙ্গে পুলিশের সংঘর্ষে গুলিতে মারাত্মক আহত হন আসিফ হাসান। দুপুর ১২টার দিকে তাঁকে উত্তরা আধুনিক মেডিকেলে নিয়ে গেলে কর্তব্যরত চিকিৎসক মৃত ঘোষণা করেন।","আসিফ হাসান এর ব্যক্তিগত তথ্য খোঁজার কাজ চলমান। আপনার কাছে উনার সম্পর্কে তথ্য থাকলে, আমাদের দিয়ে সাহায্য করুন।", R.drawable.asif_hasan));



        filteredList.addAll(list);

    }

    private void filter(String text) {

        filteredList.clear();

        if (text.isEmpty()) {

            filteredList.addAll(list);
        } else {

            for (Sohid_items item : list) {

                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        // Notify the adapter of the data change
        adapter.notifyDataSetChanged();
    }


    private void scrollAnimation() {
        binding.view3.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (recyclerView.canScrollVertically(-1)) {

                    if (dy > 0 && binding.helloTv.getVisibility() == View.VISIBLE) {
                        binding.helloTv.animate().translationY(-binding.helloTv.getHeight()).setDuration(50).start();
                        binding.helloTv.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (!recyclerView.canScrollVertically(-1)) {
                    if (binding.helloTv.getVisibility() == View.GONE) {
                        binding.helloTv.animate().translationY(0).setDuration(100).start();
                        binding.helloTv.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }


}