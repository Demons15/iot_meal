package com.cxsz.meal.meal.presenter.presenterInterface;



import com.cxsz.meal.meal.bean.ConfirmOrderResultBean;
import com.cxsz.meal.meal.bean.CreateOrderResultBean;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import common.model.MealGoodsBean;

public interface MealDetailPresenter {
    void RequestConfirmOrder( MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean);

    void RequestCreateOrder( MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean, ConfirmOrderResultBean confirmOrderResultBean);

    void RequestPayForOrder(IWXAPI api, String weChatAppId, CreateOrderResultBean createOrderResultBean);

    void onRemoveInfo();
}
