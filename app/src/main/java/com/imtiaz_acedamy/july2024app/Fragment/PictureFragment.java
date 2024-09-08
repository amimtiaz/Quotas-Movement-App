package com.imtiaz_acedamy.july2024app.Fragment;

import static java.util.Locale.filter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imtiaz_acedamy.july2024app.Adapter.EmoAdapter;
import com.imtiaz_acedamy.july2024app.Adapter.NewsAdapter2;
import com.imtiaz_acedamy.july2024app.Domain.EmoItmes;
import com.imtiaz_acedamy.july2024app.Domain.NewsItems;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.FragmentNewsListBinding;
import com.imtiaz_acedamy.july2024app.databinding.FragmentPictureBinding;

import java.util.ArrayList;

public class PictureFragment extends Fragment {
    FragmentPictureBinding binding;
    Context context;
    ArrayList<EmoItmes> list;
    ArrayList<EmoItmes> filteredList;
    EmoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPictureBinding.inflate(inflater, container, false);
        context = getContext();

        scrollAnimation();
        initEmoinit();


        return binding.getRoot();
    }

    private void initEmoinit() {

        list = new ArrayList<>();
        filteredList = new ArrayList<>();

        populateList();

        binding.view5.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));


        adapter = new EmoAdapter(filteredList);
        binding.view5.setAdapter(adapter);

        binding.searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        filter("");

    }

    private void populateList() {
        list.add(new EmoItmes(R.drawable.emo_pic_1, "ছেলের মৃত্যুতে বাবা রতন চন্দ্র তরুয়ার আহাজারি"));
        list.add(new EmoItmes(R.drawable.emo_pic_2, "ছাত্র আন্দোলনে আহত এক শিক্ষার্থীকে নিয়ে যাচ্ছেন কয়েকজন"));
        list.add(new EmoItmes(R.drawable.emo_pic_3, "ঢাকা বিশ্ববিদ্যালয়ে কয়েকজন শহীদ ছাত্রের জানাজা"));
        list.add(new EmoItmes(R.drawable.emo_pic_4, "মিছিলে ছাত্রজনতার উপর পুলিশ ও ছাত্রলীগের যৌত হামলা"));
        list.add(new EmoItmes(R.drawable.emo_pic_5, "পুলিশের গুলির সামনে এভাবেই দাড়িয়ে ছিল শহীদ আবু সাইদ "));

        filteredList.addAll(list);
    }


    private void filter(String text) {
        filteredList.clear();

        if (text.isEmpty()) {
            filteredList.addAll(list);
        } else {
            for (EmoItmes item : list) {
                if (item.getEmoText().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }

        adapter.notifyDataSetChanged();
    }

    private void scrollAnimation() {
        binding.view5.addOnScrollListener(new RecyclerView.OnScrollListener() {

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