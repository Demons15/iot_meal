package com.demons.iotmeal;

import android.app.Application;

import common.CommonModule;
import common.model.ModuleHelper;

public class App extends Application {
    public static final String APP_ID_WEI_XIN = "wxc3de19321a3b653d";//微信登录appid
    public static final String APP_ID = "CFBBFBD78EC2731A4208A4802E4A3DE9";
    public static final String MEAL_SECRET = "D9CF4912EA8C0ECB70B11592C9BD2900";
    public static final String NONCE_STR = "sanjitongchuanandchanxingshenzhou";

    @Override
    public void onCreate() {
        super.onCreate();
        ModuleHelper.getInstance().setNumber("17234248248");
        CommonModule.init(this,APP_ID_WEI_XIN,APP_ID,MEAL_SECRET,NONCE_STR);
    }
}
