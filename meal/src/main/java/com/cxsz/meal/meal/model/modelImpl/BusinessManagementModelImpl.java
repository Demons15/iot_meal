package com.cxsz.meal.meal.model.modelImpl;


import com.cxsz.meal.meal.model.modelInterface.BusinessManagementModel;
import com.google.gson.internal.LinkedTreeMap;
import java.util.ArrayList;

import common.base.BaseModel;
import common.base.rxjava.ErrorDisposableObserver;
import common.base.rxjava.ResultControlListener;
import common.model.MealCodeData;
import common.model.MealGoodsBean;
import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;
import io.reactivex.disposables.Disposable;

public class BusinessManagementModelImpl extends BaseModel implements BusinessManagementModel {
    @Override
    public void RequestGetGoodsRelevance(MealInfoBaseManager dataManager, String iccid, BaseCallBack callBack) {
        ArrayList<MealGoodsBean.MealGoodsBodyBean> bodyBeans = new ArrayList<>();

        Disposable disposable = dataManager.getGoodsRelevance(
                new ErrorDisposableObserver(new ResultControlListener() {
                    @Override
                    public void onNext(Object o) {
                        MealCodeData codeData = (MealCodeData) o;
                        if (codeData.getCode() == 1) {
                            ArrayList dataBody = (ArrayList) codeData.getBody();
                            for (int i = 0; i < dataBody.size(); i++) {
                                LinkedTreeMap mapData = (LinkedTreeMap) dataBody.get(i);
                                MealGoodsBean.MealGoodsBodyBean bodyBean = new MealGoodsBean.MealGoodsBodyBean();
                                bodyBean.getMealInfo(mapData, bodyBean);
                                bodyBeans.add(bodyBean);
                            }
                        } else {
                            callBack.onError(codeData.getMessage());
                        }
                        callBack.onSuccess(bodyBeans);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e.getMessage().toString());
                        callBack.onSuccess(bodyBeans);
                    }
                }), iccid);
        addDisposable(disposable);
    }

    @Override
    public void onRemoveInfo() {
        if (disposables != null) {
            disposables.clear();
        }
    }
}
