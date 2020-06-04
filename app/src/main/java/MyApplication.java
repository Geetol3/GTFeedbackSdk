import android.app.Application;
import android.content.Context;
import com.gtdev5.geetolsdk.mylibrary.initialization.GTBaseSdk;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        GTBaseSdk.init(this, "qq.qqkj66.com.txt", true, false, "", "");
        disableAPIDialog();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    /**
     * application里面 调用  反射 禁止弹窗 解决 detected problems with api  9.0手机（小米）
     */
    private void disableAPIDialog() {
        try {
            Class clazz = Class.forName("android.app.ActivityThread");
            Method currentActivityThread = clazz.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object activityThread = currentActivityThread.invoke(null);
            Field mHiddenApiWarningShown = clazz.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
