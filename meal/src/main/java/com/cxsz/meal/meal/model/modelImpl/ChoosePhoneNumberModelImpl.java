package com.cxsz.meal.meal.model.modelImpl;

import com.cxsz.meal.meal.model.modelInterface.ChoosePhoneNumberModel;
import com.cxsz.meal.meal.bean.ChooseNumberBean;
import com.google.gson.Gson;
import java.util.List;

import common.base.BaseModel;
import common.base.rxjava.ErrorDisposableObserver;
import common.base.rxjava.ResultControlListener;
import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;
import io.reactivex.disposables.Disposable;

public class ChoosePhoneNumberModelImpl extends BaseModel implements ChoosePhoneNumberModel {

    @Override
    public void RequestQueryChooseNumber(MealInfoBaseManager dataManager, String tempIccid, String pageNumber, String number, String pageSize, BaseCallBack callBack) {
        Disposable disposable = dataManager.queryChooseNumber(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {
                    String s = new Gson().toJson(codeData);
                    ChooseNumberBean chooseNumberBean = new Gson().fromJson(s, ChooseNumberBean.class);
                    List<ChooseNumberBean.BodyBean.ListBean> listBeans = chooseNumberBean.getBody().getList();
                    callBack.onSuccess(listBeans);
                } else {
                    callBack.onSuccess(codeData.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }), tempIccid, number, pageNumber, pageSize);
        addDisposable(disposable);
    }

    @Override
    public void onRemoveInfo() {
        if (disposables != null) {
            disposables.clear();
        }
    }
}
