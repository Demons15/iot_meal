package common.model;

import android.content.Context;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import common.model.dao.DataBaseHelper;
import common.model.http.LoginHttpHelper;
import common.model.sp.SharePreferenceHelper;

/**
 * Created by admin on 2017/3/9.
 */
@Singleton
public class LoginManager {

    private LoginHttpHelper httpHelper;

    private SharePreferenceHelper sharePreferenceHelper;

    private DataBaseHelper dataBaseHelper;

    private Context context;


    @Inject
    public LoginManager(Context context , LoginHttpHelper httpHelper , SharePreferenceHelper sharePreferenceHelper
        , DataBaseHelper dataBaseHelper) {
        this.context = context;
        this.httpHelper = httpHelper;
        this.sharePreferenceHelper = sharePreferenceHelper;
        this.dataBaseHelper = dataBaseHelper;
    }



    public <S> S getService(Class<S> serviceClass){
        return httpHelper.getService(serviceClass);
    }


    public void saveSPData(String key , String value){
        sharePreferenceHelper.saveData(key , value);
    }

    public void saveSPMapData(Map<String,String> map){
        sharePreferenceHelper.saveData(map);
    }

    public String getSPData(String key){
        return sharePreferenceHelper.getValue(key);
    }

    public void deleteSPData(){
        sharePreferenceHelper.deletePreference();
    }

    public Map<String ,String> getSPMapData(){
        return sharePreferenceHelper.readData();
    }


    public Context getContext() {
        return context;
    }
}
