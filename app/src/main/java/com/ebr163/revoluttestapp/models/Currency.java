package com.ebr163.revoluttestapp.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.ebr163.revoluttestapp.BR;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class Currency extends BaseObservable {

    private String name;
    private double rate;

    public Currency(String name, double rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    @Bindable
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
        notifyPropertyChanged(BR.rate);
    }
}
