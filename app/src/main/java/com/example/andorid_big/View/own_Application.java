package com.example.andorid_big.View;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class own_Application extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }
}
