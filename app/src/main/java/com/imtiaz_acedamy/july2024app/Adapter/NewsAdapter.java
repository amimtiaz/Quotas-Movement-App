package com.imtiaz_acedamy.july2024app.Adapter;

import static androidx.core.content.ContextCompat.startActivity;

import static com.imtiaz_acedamy.july2024app.Activity.NewsDetailsActivity.description;
import static com.imtiaz_acedamy.july2024app.Activity.NewsDetailsActivity.place;
import static com.imtiaz_acedamy.july2024app.Activity.NewsDetailsActivity.title;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imtiaz_acedamy.july2024app.Activity.NewsDetailsActivity;
import com.imtiaz_acedamy.july2024app.Domain.NewsItems;
import com.imtiaz_acedamy.july2024app.databinding.NewsLayoutBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    ArrayList<NewsItems> items;
    Context context;
    Bitmap bitmap;

    public NewsAdapter(ArrayList<NewsItems> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        NewsLayoutBinding binding = NewsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.binding.titleTxt.setText(items.get(position).getTitle());
        holder.binding.timeTxt.setText(items.get(position).getTime());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .into(holder.binding.img);

        holder.itemView.setOnClickListener(v -> {
            // Get title and description
            String title = items.get(position).getTitle();
            String description = items.get(position).getDescription();
            String place = items.get(position).getPlace();


            // Get Bitmap from RoundedImageView
            Drawable drawable = holder.binding.img.getDrawable();
            Bitmap bitmap = getBitmapFromDrawable(drawable);

            // Save the Bitmap to a file
            String filename = "bitmap_image.png";
            FileOutputStream outputStream;
            try {
                outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Start the new activity and pass the data
            Intent intent = new Intent(context, NewsDetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("description", description);
            intent.putExtra("place", place);
            intent.putExtra("image_path", filename);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });



    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        NewsLayoutBinding binding;
        public ViewHolder(NewsLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            // Handle other drawable types
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }

}


