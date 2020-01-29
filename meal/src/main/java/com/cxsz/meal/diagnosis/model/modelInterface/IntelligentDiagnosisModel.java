package com.cxsz.meal.diagnosis.model.modelInterface;

import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public interface IntelligentDiagnosisModel {

    void RequestRealNameDiagnosis(MealInfoBaseManager dataManager, String number, BaseCallBack callBack);

    void RequestCardPackageDiagnosis(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestSynchronizationCardStatus(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestUpdateVoiceData(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestUpdateTrafficData(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestFlowDetection(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestSpeechDetection(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestWhiteListDiagnosis(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void RequestReadCardStatus(MealInfoBaseManager dataManager,String number, BaseCallBack callBack);

    void onRemoveInfo();
}
