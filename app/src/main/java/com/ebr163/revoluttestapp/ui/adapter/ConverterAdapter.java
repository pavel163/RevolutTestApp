package com.ebr163.revoluttestapp.ui.adapter;

import com.ebr163.revoluttestapp.models.Currency;
import com.ebr163.universalrecyclerview.UniversalRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Bakht
 * on 17.02.2018.
 */

public class ConverterAdapter extends UniversalRecyclerViewAdapter<Currency, ConverterAction> {

    public ConverterAdapter(int variableId) {
        super(variableId);
    }

    public ConverterAdapter(int variableId, List<Currency> items) {
        super(variableId, items);
    }

    public ConverterAdapter(int variableId, int layout) {
        super(variableId, layout);
    }

    public void moveItem(int from, int to) {
        Currency item = items.get(from);
        items.remove(from);
        items.add(to, item);
        notifyItemMoved(from, to);
    }
}
