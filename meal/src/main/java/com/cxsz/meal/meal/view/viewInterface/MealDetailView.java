package com.cxsz.meal.meal.view.viewInterface;

import common.BaseUiView;

public interface MealDetailView extends BaseUiView {

    <T> void ResponseConfirmOrder(T t);

    <T> void ResponseCreateOrder(T t);

    <T> void ResponsePayForOrder(T t);
}
