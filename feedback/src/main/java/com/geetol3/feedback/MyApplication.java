package com.geetol3.feedback;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ImageLoaderConfiguration loaderConfiguration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(loaderConfiguration);
    }
}