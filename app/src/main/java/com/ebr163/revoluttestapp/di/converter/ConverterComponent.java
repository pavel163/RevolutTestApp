package com.ebr163.revoluttestapp.di.converter;

import com.ebr163.revoluttestapp.business.IConverterInteractor;

import dagger.Subcomponent;

/**
 * Created by Bakht
 * on 17.02.2018.
 */
@Converter
@Subcomponent(modules = ConverterModule.class)
public interface ConverterComponent {

    @Subcomponent.Builder
    interface Builder {
        ConverterComponent.Builder module(ConverterModule module);

        ConverterComponent build();
    }

    IConverterInteractor interactor();
}
