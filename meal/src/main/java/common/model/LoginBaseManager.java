package common.model;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import common.model.http.BaseApiService;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginBaseManager extends LoginDataManager {

    public LoginBaseManager(LoginManager mDataManager) {
        super(mDataManager);
    }

    public static LoginBaseManager getInstance(LoginManager dataManager) {
        return new LoginBaseManager(dataManager);
    }

    /*
     * //微信登录
     */
    public Disposable weChatLogin(DisposableObserver<CodeData> consumer, String wechatCode, String appid) {
        return changeIOToMainThread(getService(BaseApiService.class).weChatLogin( wechatCode, appid), consumer);
    }

    /*
     *  获取验证码
     */
    public Disposable sendVerifyCode(DisposableObserver<CodeData> consumer, String mobile) {
        return changeIOToMainThread(getService(BaseApiService.class).sendVefifyCode( mobile), consumer);
    }

    /*
     * 绑定手机号
     */
    public Disposable bindMobile(DisposableObserver<CodeData> consumer, String mobile, String verifyCode, String openid, String appid) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("verifyCode", verifyCode);
        map.put("openid", openid);
        map.put("appid", appid);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), new Gson().toJson(map));
        return changeIOToMainThread(getService(BaseApiService.class).bindMobile( body), consumer);
    }
}
