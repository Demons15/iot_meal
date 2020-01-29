package com.cxsz.meal.meal.model.modelInterface;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public interface ChoosePhoneNumberModel {
    void RequestQueryChooseNumber(MealInfoBaseManager dataManager, String tempIccid, String pageNumber, String number, String pageSize, BaseCallBack callBack);

    void onRemoveInfo();
}
