package com.example.tuantran.faptv;

import android.content.Context;

public class Application extends android.app.Application {
    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
