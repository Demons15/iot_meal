package com.cxsz.meal.diagnosis.presenter.presenterImpl;

import com.cxsz.meal.diagnosis.model.modelImpl.IntelligentDiagnosisModelImpl;
import com.cxsz.meal.diagnosis.model.modelInterface.IntelligentDiagnosisModel;
import com.cxsz.meal.diagnosis.presenter.presenterInterface.IntelligentDiagnosisPresenter;
import com.cxsz.meal.diagnosis.view.viewInterface.IntelligentDiagnosisView;
import javax.inject.Inject;

import common.constant.KeyConstants;
import common.model.MealCodeData;
import common.model.MealInfoBaseManager;
import common.model.callback.BaseCallBack;

public class IntelligentDiagnosisPresenterImpl implements IntelligentDiagnosisPresenter {
    private MealInfoBaseManager mealInfoManager;
    private IntelligentDiagnosisView intelligentDiagnosisView;
    private IntelligentDiagnosisModel intelligentDiagnosisModel;

    @Inject
    public IntelligentDiagnosisPresenterImpl(MealInfoBaseManager mealInfoManager, IntelligentDiagnosisView intelligentDiagnosisView) {
        this.mealInfoManager = mealInfoManager;
        this.intelligentDiagnosisView = intelligentDiagnosisView;
        intelligentDiagnosisModel = new IntelligentDiagnosisModelImpl();
    }

    @Override
    public void RequestRealNameDiagnosis(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestRealNameDiagnosis(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseRealNameDiagnosis(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_REAL_NAME_DIAGNOSIS,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestCardPackageDiagnosis(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestCardPackageDiagnosis(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseCardPackageDiagnosis(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_CARD_PACKAGE_DIAGNOSIS,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestSynchronizationCardStatus(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestSynchronizationCardStatus(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseSynchronizationCardStatus(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_SYNCHRONIZATION_CARD_STATUS,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestUpdateVoiceData(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestUpdateVoiceData(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseUpdateVoiceData(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_UPDATE_VOICE_DATA,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestUpdateTrafficData(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestUpdateTrafficData(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseUpdateTrafficData(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_UPDATE_TRAFFIC_DATA,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestFlowDetection(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestFlowDetection(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseFlowDetection(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_FLOW_DETECTION,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestSpeechDetection(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestSpeechDetection(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseSpeechDetection(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_SPEECH_DETECTION,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestWhiteListDiagnosis(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestWhiteListDiagnosis(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseWhiteListDiagnosis(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_WHITE_LIST_DIAGNOSIS,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void RequestReadCardStatus(String number) {
        intelligentDiagnosisView.showUiLoadingView();
        intelligentDiagnosisModel.RequestReadCardStatus(mealInfoManager, number, new BaseCallBack() {
            @Override
            public void onSuccess(Object response) {
                MealCodeData bodyBean = (MealCodeData) response;
                intelligentDiagnosisView.ResponseReadCardStatus(bodyBean);
                intelligentDiagnosisView.closeUiLoadingView();
            }

            @Override
            public void onError(String error) {
                if (error != null) {
                    intelligentDiagnosisView.showUiErrorInfo(KeyConstants.RESPONSE_READ_CARD_STATUS,error);
                }
                intelligentDiagnosisView.closeUiLoadingView();
            }
        });
    }

    @Override
    public void onRemoveInfo() {
        intelligentDiagnosisModel.onRemoveInfo();
    }
}
