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
    private double amount;

    public Currency(String name, double rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Bindable
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
        notifyPropertyChanged(BR.amount);
    }

    public void setAmountWithoutUpdate(double amount){
        this.amount = amount;
    }
}
