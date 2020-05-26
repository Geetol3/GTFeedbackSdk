package com.geetol3.feedback.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * 自定义Glide加载工具
 */
public class GlideLoader implements ImageEngine {
    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        try {
            Glide.with(context).asBitmap().load(uri).into(imageView);
        } catch (Exception e) {
            Glide.with(context).load(uri).asBitmap().into(imageView);
            e.printStackTrace();
        }
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        try {
            Glide.with(context).asGif().load(uri).into(imageView);
        } catch (Exception e) {
            Glide.with(context).load(uri).asGif().into(imageView);
            e.printStackTrace();
        }
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        try {
            Glide.with(context).asBitmap().load(uri).into(imageView);
        } catch (Exception e) {
            Glide.with(context).load(uri).asBitmap().into(imageView);
            e.printStackTrace();
        }
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        try {
            Glide.with(context).asGif().load(uri).into(imageView);
        } catch (Exception e) {
            Glide.with(context).load(uri).asGif().into(imageView);
            e.printStackTrace();
        }
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
