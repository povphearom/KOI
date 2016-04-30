package com.phearom.koi.api.core.listener;

public interface BindViewHandler<T> {
    void onBindView(T viewModel, int pos);
}