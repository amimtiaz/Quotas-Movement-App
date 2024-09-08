package com.imtiaz_acedamy.july2024app.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.imtiaz_acedamy.july2024app.Activity.SohidDetailsActivity;
import com.imtiaz_acedamy.july2024app.Domain.Sohid_items;
import com.imtiaz_acedamy.july2024app.Fragment.SohidListFragment;
import com.imtiaz_acedamy.july2024app.databinding.SohidLayoutBinding;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Sohid_Adapter extends RecyclerView.Adapter<Sohid_Adapter.ViewHolder> {
    ArrayList<Sohid_items> items;
    Context context;
    Bitmap bitmap;

    public Sohid_Adapter(ArrayList<Sohid_items> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        SohidLayoutBinding binding = SohidLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.nameTxt.setText(items.get(position).getName());
        holder.binding.textView5.setText(items.get(position).getBackground());
        holder.binding.textView9.setText(items.get(position).getSchoolName());
        holder.binding.timeTxt.setText(items.get(position).getDeathDate());

        Glide.with(context)
                .load(items.get(position).getImagePath())
                .into(holder.binding.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = items.get(position).getName();
                String background = items.get(position).getBackground();
                String schoolName = items.get(position).getSchoolName();
                String deathDate = items.get(position).getDeathDate();
                String lifeHistory = items.get(position).getLifeHistory();
                String deathHistory = items.get(position).getDeathHistory();
                //int imagePath = items.get(position).getImagePath();



                Drawable drawable = holder.binding.img.getDrawable();
                Bitmap bitmap = getBitmapFromDrawable(drawable);

                // Save the Bitmap to a file
                String filename = "image_" + System.currentTimeMillis() + ".png"; // Use a unique filename
                FileOutputStream outputStream;
                try {
                    outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


//                String filename = "bitmap_img.png";
//                FileOutputStream outputStream;
//                try {
//                    outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE);
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                    outputStream.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }


                // Intent for SohidDetailsActivity
                Intent intent = new Intent(context, SohidDetailsActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("background", background);
                intent.putExtra("schoolName", schoolName);
                intent.putExtra("deathDate", deathDate);
                intent.putExtra("lifeHistory", lifeHistory);
                intent.putExtra("deathHistory", deathHistory);
                intent.putExtra("image_path", filename);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Intent intent2 = new Intent(context, SohidDetailsActivity.class);
                intent2.putExtra("name", name);
                intent2.putExtra("background", background);
                intent2.putExtra("schoolName", schoolName);
                intent2.putExtra("deathDate", deathDate);
                intent2.putExtra("lifeHistory", lifeHistory);
                intent2.putExtra("deathHistory", deathHistory);
                intent2.putExtra("image_path", filename);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent2);

                context.startActivity(intent);

//                Glide.with(context)
//                        .asBitmap()
//                        .load(imagePath)
//                        .placeholder(R.drawable.baseline_person_24)
//                        .error(R.drawable.baseline_person_24)
//                        .into(new CustomTarget<Bitmap>() {
//                            @Override
//                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                                // Save the bitmap to internal storage
//                                String fileName = "bitmap_image.png";
//                                try (FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
//                                    resource.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
//                                    intent.putExtra("image_path", fileName);
//                                    context.startActivity(intent); // Start SohidDetailsActivity after bitmap is saved
//                                } catch (IOException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void onLoadCleared(@Nullable Drawable placeholder) {
//                                // Handle if necessary
//                            }
//                        });

                // Uncomment if you need to start the second activity (SohidListActivity)



            }
        });


    }

    public Bitmap getBitmapFromDrawable(Drawable drawable){
        if (drawable instanceof BitmapDrawable){
            return ((BitmapDrawable) drawable).getBitmap();
        }else {
            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        SohidLayoutBinding binding;
        public ViewHolder(SohidLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
