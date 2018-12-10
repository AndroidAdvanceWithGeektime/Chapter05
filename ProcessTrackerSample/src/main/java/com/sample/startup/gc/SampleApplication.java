package com.sample.startup.gc;


import android.app.Application;
import android.content.Context;

public class SampleApplication extends Application {

    public SampleApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(final Context base) {
        super.attachBaseContext(base);
    }
}
