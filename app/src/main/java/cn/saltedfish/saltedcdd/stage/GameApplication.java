package cn.saltedfish.saltedcdd.stage;

import android.app.Application;
import android.content.Context;

import cn.saltedfish.saltedcdd.data.Config;

public class GameApplication extends Application {
    private static Context appContext;

    @Override
    public void onCreate()
    {
        super.onCreate();
        appContext = getApplicationContext();

        Config.load();
    }

    public static Context getAppContext()
    {
        return appContext;
    }
}
