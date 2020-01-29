package com.cxsz.meal.main.model.modelInterface;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public interface SearchCardModel {
    void requestGetMealInfo(MealInfoBaseManager dataManager, String cardNumber, BaseCallBack callBack);

    void onRemoveInfo();
}
