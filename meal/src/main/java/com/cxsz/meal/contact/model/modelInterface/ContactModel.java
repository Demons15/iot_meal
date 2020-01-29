package com.cxsz.meal.contact.model.modelInterface;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public interface ContactModel {

    void RequestAddOrDelVoiceWhiteManager(MealInfoBaseManager dataManager, String type, String msisdn, String operType, String phone, BaseCallBack callBack);

    void RequestQueryVoiceWhiteList(MealInfoBaseManager dataManager,String type, String msisdn, BaseCallBack callBack);

    void RequestQueryAddWhiteCount(MealInfoBaseManager dataManager,String type, String msisdn, BaseCallBack callBack);

    void onRemoveInfo();
}
