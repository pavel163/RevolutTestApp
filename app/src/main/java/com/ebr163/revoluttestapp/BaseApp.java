package com.ebr163.revoluttestapp;

import android.app.Application;

import com.ebr163.revoluttestapp.di.base.AppComponent;
import com.ebr163.revoluttestapp.di.base.DaggerAppComponent;
import com.ebr163.revoluttestapp.di.converter.ConverterComponent;
import com.ebr163.revoluttestapp.di.converter.ConverterModule;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class BaseApp extends Application {

    private static AppComponent appComponent;
    private static ConverterComponent converterComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .build();
    }

    public static ConverterComponent getConverterComponent(){
        if (converterComponent == null){
            converterComponent = appComponent.converterComponentBuilder()
                    .module(new ConverterModule())
                    .build();
        }
        return converterComponent;
    }
}
