package com.cxsz.meal.meal.model.modelInterface;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public interface MineMealModel {
    void RequestSimCardMealInfo(MealInfoBaseManager dataManager, String iccid, BaseCallBack callBack);

    void RequestSimCardMealList(MealInfoBaseManager dataManager, String iccid, BaseCallBack callBack);

    void onRemoveInfo();
}
