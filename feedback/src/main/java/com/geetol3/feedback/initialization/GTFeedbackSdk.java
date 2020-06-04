package com.geetol3.feedback.initialization;

import android.content.Context;

import com.gtdev5.geetolsdk.mylibrary.initialization.GTBaseSdk;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class GTFeedbackSdk {
    public static String TAG = "GTFeedbackSdk";

    private static Context mContext;

    public static void init(Context context) {
        try {
            if (mContext == null) {
                mContext = context;
            }
            ImageLoaderConfiguration loaderConfiguration = ImageLoaderConfiguration.createDefault(mContext);
            ImageLoader.getInstance().init(loaderConfiguration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Context getmContext() {
        return mContext;
    }

    /**
     * 简单初始化(默认打印日志，但不保存错误信息)
     * @param fileName 配置文件名
     */
    public static void init(Context context, String fileName) {
        init(context, fileName, true, false, "", "");
    }

    /**
     * 简单初始化(默认打印日志，但不保存错误信息)
     * @param fileName 配置文件名
     * @param showLog 是否显示日志
     * @param saveLog 是否保存日志
     * @param logFile 错误日志保存路径
     */
    public static void init(Context context, String fileName, boolean showLog, boolean saveLog, String logFile, String logName) {
        init(context);
        GTBaseSdk.init(mContext, fileName, showLog, saveLog, logFile, logName);
    }
}
