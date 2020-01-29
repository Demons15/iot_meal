package common;

import android.app.Application;

import common.model.ModuleHelper;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * 主框架初始化
 */
public class CommonModule {
    public static void init(Application application,String APP_ID_WEI_XIN,String APP_ID,String MEAL_SECRET,String NONCE_STR) {
        GlobalAppComponent.init(application);
        //初始化套餐购买相关信息
        ModuleHelper.getInstance().init(application, APP_ID_WEI_XIN, APP_ID, MEAL_SECRET, NONCE_STR);
        AutoSize.initCompatMultiProcess(application);
        AutoSizeConfig.getInstance().setCustomFragment(true).setExcludeFontScale(true);
    }
}
