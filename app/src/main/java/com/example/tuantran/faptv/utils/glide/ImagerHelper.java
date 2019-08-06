package com.example.tuantran.faptv.utils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImagerHelper {
    public static void load(Context mContext, ImageView imageView, String url) {
//        Glide.with(mContext).
        Glide.with(mContext)
                .load(url)
                .into(imageView);
    }

    public static void load(Context mContext, ImageView imageView, int image){
        Glide.with(mContext)
                .load(image)
                .into(imageView);
    }
}
