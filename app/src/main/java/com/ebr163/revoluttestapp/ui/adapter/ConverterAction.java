package com.ebr163.revoluttestapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.ebr163.revoluttestapp.models.Currency;
import com.ebr163.revoluttestapp.ui.presenter.ConverterPresenter;
import com.ebr163.universalrecyclerview.BindingAction;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class ConverterAction extends BindingAction<Currency> {

    private final ConverterPresenter presenter;
    private final RecyclerView recyclerView;

    public ConverterAction(RecyclerView recyclerView, ConverterPresenter presenter) {
        this.presenter = presenter;
        this.recyclerView = recyclerView;
    }

    public void onClickName(View view) {
        RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
        presenter.moveItem(holder.getAdapterPosition(), 0);
    }

    public void changeCount(Currency currency) {
        Log.d("editRate", currency.getName());
    }
}
