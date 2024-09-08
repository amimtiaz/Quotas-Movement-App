package com.imtiaz_acedamy.july2024app.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imtiaz_acedamy.july2024app.Domain.EmoItmes;
import com.imtiaz_acedamy.july2024app.R;
import com.imtiaz_acedamy.july2024app.databinding.EmotionLayout2Binding;
import com.imtiaz_acedamy.july2024app.databinding.EmotionLayoutBinding;

import java.util.ArrayList;

public class EmoAdapterHome extends RecyclerView.Adapter<EmoAdapterHome.ViewHolder> {

    ArrayList<EmoItmes> items;
    Context context;
    Bitmap bitmap;

    public EmoAdapterHome(ArrayList<EmoItmes> items){
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EmotionLayout2Binding binding = EmotionLayout2Binding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new EmoAdapterHome.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (items.get(position) != null && items.get(position).getEmoText() != null) {
            holder.binding.emoTxt.setText(items.get(position).getEmoText());
        } else {
            holder.binding.emoTxt.setText("null");
        }

        if (items.get(position) != null ) {

            Glide.with(holder.itemView.getContext())
                    .load(items.get(position).getImage())
                    .into(holder.binding.img);
        } else {

            holder.binding.img.setImageResource(R.drawable.emo_pic_3); // Set a placeholder image if needed
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        EmotionLayout2Binding binding;
        public ViewHolder(EmotionLayout2Binding binding) {
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
