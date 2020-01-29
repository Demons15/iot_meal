package com.cxsz.meal.main.model.modelImpl;

import com.cxsz.meal.main.model.modelInterface.SearchCardModel;
import com.google.gson.internal.LinkedTreeMap;

import common.base.BaseModel;
import common.base.rxjava.ErrorDisposableObserver;
import common.base.rxjava.ResultControlListener;
import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.MealInfoBean;
import common.model.callback.BaseCallBack;
import io.reactivex.disposables.Disposable;

public class SearchCardModelImpl extends BaseModel implements SearchCardModel {

    @Override
    public void requestGetMealInfo(MealInfoBaseManager dataManager, String cardNumber, BaseCallBack callBack) {
        Disposable disposable = dataManager.getMealInfo(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {
                    MealInfoBean.BodyBean bodyBean = new MealInfoBean.BodyBean();
                    LinkedTreeMap codeDataBody = (LinkedTreeMap) codeData.getBody();
                    bodyBean.getSimPackageInfo(codeDataBody, bodyBean);
                    callBack.onSuccess(bodyBean);
                } else {
                    callBack.onError(codeData.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }), cardNumber);
        addDisposable(disposable);
    }

    @Override
    public void onRemoveInfo() {
        if (disposables != null) {
            disposables.clear();
        }
    }
}
