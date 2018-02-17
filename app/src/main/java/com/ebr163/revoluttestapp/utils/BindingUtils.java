package com.ebr163.revoluttestapp.utils;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class BindingUtils {

    @BindingAdapter("text")
    public static void setFormatDouble(TextView textView, double number) {
        textView.setText(String.format(Locale.getDefault(), "%.4f", number));
    }
}
