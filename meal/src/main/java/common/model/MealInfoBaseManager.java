package common.model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.model.http.BaseApiService;
import common.util.GetMealCommonUtils;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MealInfoBaseManager extends BaseDataManager {

    public MealInfoBaseManager(DataManager mDataManager) {
        super(mDataManager);
    }

    public static MealInfoBaseManager getInstance(DataManager dataManager) {
        return new MealInfoBaseManager(dataManager);
    }

    /*
     * 卡信息查询
     */
    public Disposable getMealInfo(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).getSimByParam(body), consumer);
    }

    public Disposable getSimPackage(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).getSimPackage(body), consumer);
    }

    public Disposable getGoodsRelevance(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).getGoodsRelevance(body), consumer);
    }

    public Disposable queryChooseNumber(DisposableObserver<MealCodeData> consumer, String tempIccid, String number, String pageNum, String pageSize) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("tempIccid", tempIccid);
        map.put("number", number);
        map.put("pageNum", pageNum);
        map.put("pageSize", pageSize);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).queryChooseNumber(body), consumer);
    }

    public Disposable chooseNumber(DisposableObserver<MealCodeData> consumer, String tempIccid, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("tempIccid", tempIccid);
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).chooseNumber(body), consumer);
    }

    public Disposable confirmOrder(DisposableObserver<MealCodeData> consumer, String cardNumber, String goodsId) {
        List<ConfirmOrderBean.GoodsRelevanceBean> goodsRelevanceBeanList = new ArrayList<ConfirmOrderBean.GoodsRelevanceBean>();
        ConfirmOrderBean.GoodsRelevanceBean goodsRelevanceBean = new ConfirmOrderBean.GoodsRelevanceBean();
        goodsRelevanceBean.setGoodsId(goodsId);
        goodsRelevanceBeanList.add(goodsRelevanceBean);
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        map.put("goodsRelevance", new Gson().toJson(goodsRelevanceBeanList));
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).confirmOrder(body), consumer);

    }

    public Disposable createNewOrder(DisposableObserver<MealCodeData> consumer, String cardNumber, String dealMode, String packageEndTime, String packageEndTimeNew, String goodsId, String goodsDescribe) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        map.put("dealMode", dealMode);
        if (packageEndTime == null) {
            map.put("packageEndTime", "");
        } else {
            map.put("packageEndTime", packageEndTime);
        }
        map.put("packageEndTimeNew", packageEndTimeNew);
        map.put("goodsId", goodsId);
        map.put("goodsDescribe", goodsDescribe);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).createNewOrder(body), consumer);
    }

    public Disposable payOrder(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).payOrder(body), consumer);
    }


    public Disposable sendMsg(DisposableObserver<MealCodeData> consumer, String cardNumber, String messageInfo) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("target", cardNumber);
        map.put("message", messageInfo);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).sendMsg(body), consumer);
    }

    public Disposable queryHistoryMsg(DisposableObserver<MealCodeData> consumer, String cardNumber, String searchType) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("target", cardNumber);
        map.put("searchType", searchType);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).queryHistoryMsg(body), consumer);
    }


    public Disposable realNameDiagnosis(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).realNameDiagnosis(body), consumer);
    }

    public Disposable cardPackageDiagnosis(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).cardPackageDiagnosis(body), consumer);
    }

    public Disposable synchronizationCardStatus(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).synchronizationCardStatus(body), consumer);
    }

    public Disposable updateVoiceData(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).updateVoiceData(body), consumer);
    }

    public Disposable updateTrafficData(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).updateTrafficData(body), consumer);
    }

    public Disposable flowDetection(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).flowDetection(body), consumer);
    }

    public Disposable speechDetection(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).speechDetection(body), consumer);
    }

    public Disposable whiteListDiagnosis(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).whiteListDiagnosis(body), consumer);
    }

    public Disposable readCardStatus(DisposableObserver<MealCodeData> consumer, String cardNumber) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("cardNumber", cardNumber);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).readCardStatus(body), consumer);
    }


    public Disposable queryVoiceWhiteList(DisposableObserver<MealCodeData> consumer, String type, String msisdn) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("type", type);
        map.put("msisdn", msisdn);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).queryVoiceWhiteList(body), consumer);
    }

    /***
     *
     * @param consumer
     * @param type  传入的参数类型：1 物联网卡号 2 imei 3 imsi
     * @param msisdn 物联网卡号
     * @param operType  add 新增  del 删除
     * @param phone  添加的白名单的手机号码多个以”,”隔开
     * @return
     */
    public Disposable addOrDelVoiceWhiteManager(DisposableObserver<MealCodeData> consumer, String type, String msisdn, String operType, String phone) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("type", type);
        map.put("msisdn", msisdn);
        map.put("operType", operType);
        map.put("phone", phone);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).addOrDelVoiceWhiteManager(body), consumer);
    }

    public Disposable queryAddWhiteCount(DisposableObserver<MealCodeData> consumer, String type, String msisdn) {
        Map<String, String> map = new HashMap<>();
        map.put("appId", ModuleHelper.getInstance().getMealAppId());
        map.put("timestamp", GetMealCommonUtils.getTime());
        map.put("signature", "");
        map.put("nonceStr", ModuleHelper.getInstance().getMealNonceStr());
        map.put("type", type);
        map.put("msisdn", msisdn);
        List<String> ignoreParamNames = new ArrayList<>();
        ignoreParamNames.add("signature");
        String sign = NetSignUtil.sign(map, ignoreParamNames, ModuleHelper.getInstance().getMealSecret());
        map.put("signature", sign);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).queryAddWhiteCount(body), consumer);
    }
}
