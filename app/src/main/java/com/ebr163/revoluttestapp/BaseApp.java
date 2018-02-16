package com.ebr163.revoluttestapp;

import android.app.Application;

import com.ebr163.revoluttestapp.di.base.AppComponent;
import com.ebr163.revoluttestapp.di.base.DaggerAppComponent;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class BaseApp extends Application {

    public static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .build();
    }
}
