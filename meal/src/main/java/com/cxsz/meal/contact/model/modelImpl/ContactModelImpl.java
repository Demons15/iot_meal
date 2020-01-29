package com.cxsz.meal.contact.model.modelImpl;

import com.cxsz.meal.contact.model.modelInterface.ContactModel;

import common.base.BaseModel;
import common.base.rxjava.ErrorDisposableObserver;
import common.base.rxjava.ResultControlListener;
import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;
import io.reactivex.disposables.Disposable;

public class ContactModelImpl extends BaseModel implements ContactModel {

    @Override
    public void RequestAddOrDelVoiceWhiteManager(MealInfoBaseManager dataManager, String type, String msisdn, String operType, String phone, BaseCallBack callBack) {
        Disposable disposable = dataManager.addOrDelVoiceWhiteManager(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {
                    callBack.onSuccess(codeData);
                } else {
                    callBack.onError(codeData.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }), type, msisdn, operType, phone);
        addDisposable(disposable);
    }

    @Override
    public void RequestQueryVoiceWhiteList(MealInfoBaseManager dataManager, String type, String msisdn, BaseCallBack callBack) {
        Disposable disposable = dataManager.queryVoiceWhiteList(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {
                    callBack.onSuccess(codeData);
                } else {
                    callBack.onError(codeData.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }), type, msisdn);
        addDisposable(disposable);
    }

    @Override
    public void RequestQueryAddWhiteCount(MealInfoBaseManager dataManager, String type, String msisdn, BaseCallBack callBack) {
        Disposable disposable = dataManager.queryAddWhiteCount(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {
                    callBack.onSuccess(codeData);
                } else {
                    callBack.onError(codeData.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }), type, msisdn);
        addDisposable(disposable);
    }

    @Override
    public void onRemoveInfo() {
        if (disposables != null) {
            disposables.clear();
        }
    }
}
