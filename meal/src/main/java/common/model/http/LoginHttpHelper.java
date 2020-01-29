package common.model.http;

import android.content.Context;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import common.constant.NetService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *  登录相关 http helper负责创建ApiService实例
 */
@Singleton
public class LoginHttpHelper {
    private Context context;
    private Retrofit mRetrofitClient;
    private HashMap<String, Object> mServiceMap;

    @Inject
    public LoginHttpHelper(Context context) {
        this.context = context;
        mServiceMap = new HashMap<>();
        initRetrofitClient();
    }


    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass) {
        if (mServiceMap.containsKey(serviceClass.getName())) {
            return (S) mServiceMap.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, null);
            mServiceMap.put(serviceClass.getName(), obj);
            return (S) obj;
        }


    }

    @SuppressWarnings("unchecked")
    public <S> S getService(Class<S> serviceClass, OkHttpClient client) {
        if (mServiceMap.containsKey(serviceClass.getName())) {
            return (S) mServiceMap.get(serviceClass.getName());
        } else {
            Object obj = createService(serviceClass, client);
            mServiceMap.put(serviceClass.getName(), obj);
            return (S) obj;
        }
    }

    private void initRetrofitClient() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .readTimeout(NetService.HTTP_READ_TIME_OUT, TimeUnit.SECONDS)
                .connectTimeout(NetService.HTTP_CONNECT_TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(new BaseInterceptor<>(null, context))
                .build();
        mRetrofitClient = createRetrofitClient(httpClient);
    }

    private Retrofit createRetrofitClient(OkHttpClient httpClient) {
        Retrofit.Builder client = new Retrofit.Builder()
                .client(httpClient);
        client.baseUrl(NetService.SERVER_URL);
        client.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        return client.build();

    }

    private <S> S createService(Class<S> serviceClass, OkHttpClient client) {
        if (client == null) {
            return mRetrofitClient.create(serviceClass);
        } else {
            return createRetrofitClient(client).create(serviceClass);
        }
    }
}
