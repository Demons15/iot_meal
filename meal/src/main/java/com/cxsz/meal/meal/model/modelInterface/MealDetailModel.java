package com.cxsz.meal.meal.model.modelInterface;

import com.cxsz.meal.meal.bean.ConfirmOrderResultBean;
import com.cxsz.meal.meal.bean.CreateOrderResultBean;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import common.model.MealGoodsBean;
import common.model.MealInfoBaseManager;
import common.model.ResponseCallBack;
import common.model.callback.BaseCallBack;

public interface MealDetailModel {

    void RequestConfirmOrder(MealInfoBaseManager dataManager, MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean, BaseCallBack callBack);

    void RequestCreateOrder(MealInfoBaseManager dataManager, MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean, ConfirmOrderResultBean confirmOrderResultBean, BaseCallBack callBack);

    void RequestPayForOrder(IWXAPI api, String weChatAppId, CreateOrderResultBean createOrderResultBean, ResponseCallBack callBack);

    void onRemoveInfo();
}
