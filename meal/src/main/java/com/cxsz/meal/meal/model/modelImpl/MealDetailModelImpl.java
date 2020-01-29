package com.cxsz.meal.meal.model.modelImpl;

import com.cxsz.meal.meal.model.modelInterface.MealDetailModel;
import com.cxsz.meal.meal.bean.ConfirmOrderResultBean;
import com.cxsz.meal.meal.bean.CreateOrderResultBean;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import common.base.BaseModel;
import common.base.rxjava.ErrorDisposableObserver;
import common.base.rxjava.ResultControlListener;
import common.model.MealCodeData;
import common.model.MealGoodsBean;
import common.model.MealInfoBaseManager;
import common.model.ResponseCallBack;
import common.model.callback.BaseCallBack;
import io.reactivex.disposables.Disposable;

public class MealDetailModelImpl extends BaseModel implements MealDetailModel {

    @Override
    public void RequestConfirmOrder(MealInfoBaseManager dataManager, MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean, BaseCallBack callBack) {
        Disposable disposable = dataManager.confirmOrder(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {
                    String s = new Gson().toJson(codeData);
                    ConfirmOrderResultBean confirmOrderResultBean = new Gson().fromJson(s, ConfirmOrderResultBean.class);
                    callBack.onSuccess(confirmOrderResultBean);
                } else {
                    callBack.equals(codeData.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }), mealGoodsBodyBean.getIccid(), mealGoodsBodyBean.getGoodsId());
        addDisposable(disposable);
    }

    @Override
    public void RequestCreateOrder(MealInfoBaseManager dataManager, MealGoodsBean.MealGoodsBodyBean mealGoodsBodyBean, ConfirmOrderResultBean confirmOrderResultBean, BaseCallBack callBack) {
        Disposable disposable = dataManager.createNewOrder(new ErrorDisposableObserver(new ResultControlListener() {
                    @Override
                    public void onNext(Object o) {
                        MealCodeData codeData = (MealCodeData) o;
                        if (codeData.getCode() == 1) {
                            String s = new Gson().toJson(codeData);
                            CreateOrderResultBean createOrderResultBean = new Gson().fromJson(s, CreateOrderResultBean.class);
                            callBack.onSuccess(createOrderResultBean);
                        } else {
                            callBack.onError(codeData.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                        callBack.onError(e.getMessage());
                    }
                }), mealGoodsBodyBean.getCardNumber(), confirmOrderResultBean.getBody().getGoodsRelevance().get(0).getEffectiveMode().get(0).getDealMode(),
                confirmOrderResultBean.getBody().getGoodsRelevance().get(0).getEffectiveMode().get(0).getOldPackageEndTime(),
                confirmOrderResultBean.getBody().getGoodsRelevance().get(0).getEffectiveMode().get(0).getPackageEndTime(),
                mealGoodsBodyBean.getGoodsId(), mealGoodsBodyBean.getGoodsDescribe());
        addDisposable(disposable);
    }

    @Override
    public void RequestPayForOrder(IWXAPI api, String weChatAppId, CreateOrderResultBean createOrderResultBean, ResponseCallBack callBack) {
        PayReq req = new PayReq();
        req.appId = weChatAppId;
        req.partnerId = createOrderResultBean.getBody().getPartnerId();
        req.prepayId = createOrderResultBean.getBody().getPrepayId();
        req.nonceStr = createOrderResultBean.getBody().getNonceStr();
        req.timeStamp = createOrderResultBean.getBody().getTimeStamp();
        req.packageValue = createOrderResultBean.getBody().getPackageX();
        req.sign = createOrderResultBean.getBody().getSign();
        callBack.onResponse("正在跳转支付,请稍后...");
        // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
        api.sendReq(req);
    }

    @Override
    public void onRemoveInfo() {
        if (disposables != null) {
            disposables.clear();
        }
    }
}
