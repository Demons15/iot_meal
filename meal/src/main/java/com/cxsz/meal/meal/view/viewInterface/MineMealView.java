package com.cxsz.meal.meal.view.viewInterface;

import common.BaseUiView;

public interface MineMealView extends BaseUiView {
    <T> void ResponseSimCardMealInfo(T t);

    <T> void ResponseSimCardMealList(T t);
}
