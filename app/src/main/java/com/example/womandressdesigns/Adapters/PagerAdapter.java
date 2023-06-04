package com.example.womandressdesigns.Adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.List;

public class PagerAdapter extends androidx.viewpager.widget.PagerAdapter {
        private List<Drawable> images; // List of drawable images to display

        public PagerAdapter(List<Drawable> images) {
            this.images = images;
        }

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(container.getContext());
            imageView.setImageDrawable(images.get(position));

            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((ImageView) object);
        }

    public Drawable getImageDrawable(int currentPosition) {
        if (currentPosition >= 0 && currentPosition < images.size()) {
            return images.get(currentPosition);
        }
        return null;
    }


}


