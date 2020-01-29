package com.cxsz.meal.meal.model.modelInterface;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public interface BusinessManagementModel {
    void RequestGetGoodsRelevance(MealInfoBaseManager dataManager, String iccid, BaseCallBack callBack);

    void onRemoveInfo();
}
