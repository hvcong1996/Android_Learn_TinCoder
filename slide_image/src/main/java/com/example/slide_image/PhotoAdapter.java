package com.example.slide_image;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private Context context;
    private List<Photo> photoList;

    public PhotoAdapter(Context context, List<Photo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Context context = container.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(R.layout.item_photo, container, false);

        ImageView imageView = view.findViewById(R.id.imageView);

        Photo photo = photoList.get(position);
        if(photo != null){
            // Load photo to imageView
            Glide.with(context).load(photo.getResourceId()).into(imageView);
        }

        // Add view to ViewGroup
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {

        if(photoList != null){
            return photoList.size();
        }

        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // Remove view in ViewGroup
        container.removeView((View) object);
    }
}
