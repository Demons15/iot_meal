package com.cxsz.meal.main.view.viewInterface;

import common.BaseUiView;
import common.model.MealInfoBean;

public interface SearchCardView extends BaseUiView {

    void responseGetMealInfo(MealInfoBean.BodyBean bodyBean);
}
