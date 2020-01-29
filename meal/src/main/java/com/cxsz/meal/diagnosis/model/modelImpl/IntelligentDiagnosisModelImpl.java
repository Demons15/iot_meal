package com.cxsz.meal.diagnosis.model.modelImpl;

import com.cxsz.meal.diagnosis.model.modelInterface.IntelligentDiagnosisModel;

import common.base.BaseModel;
import common.base.rxjava.ErrorDisposableObserver;
import common.base.rxjava.ResultControlListener;
import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;
import io.reactivex.disposables.Disposable;

public class IntelligentDiagnosisModelImpl extends BaseModel implements IntelligentDiagnosisModel {

    @Override
    public void RequestRealNameDiagnosis(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.realNameDiagnosis(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestCardPackageDiagnosis(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.cardPackageDiagnosis(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestSynchronizationCardStatus(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.synchronizationCardStatus(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestUpdateVoiceData(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.updateVoiceData(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestUpdateTrafficData(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.updateTrafficData(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestFlowDetection(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.flowDetection(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestSpeechDetection(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.speechDetection(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestWhiteListDiagnosis(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.whiteListDiagnosis(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void RequestReadCardStatus(MealInfoBaseManager dataManager, String number, BaseCallBack callBack) {
        Disposable disposable = dataManager.readCardStatus(new ErrorDisposableObserver(new ResultControlListener() {
            @Override
            public void onNext(Object o) {
                MealCodeData codeData = (MealCodeData) o;
                if (codeData.getCode() == 1) {

                }
                callBack.onSuccess(codeData);
            }

            @Override
            public void onError(Throwable e) {
                //如果需要发生Error时操作UI可以重写onError，统一错误操作可以在ErrorDisposableObserver中统一执行
                callBack.onError(e.getMessage());
            }
        }) , number);
        addDisposable(disposable);
    }

    @Override
    public void onRemoveInfo() {
        if (disposables != null) {
            disposables.clear();
        }
    }
}
