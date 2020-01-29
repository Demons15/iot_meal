package common.constant;


import com.cxsz.meal.BuildConfig;

/**
 * 网络服务类
 * Created by cxsz-hp11 on 2018/6/1.
 */
public class NetService {
    public static int HTTP_READ_TIME_OUT = 15;
    public static int HTTP_CONNECT_TIME_OUT = 15;
    public static boolean DEBUG = true;
    public static String SHARE_PREFERENCE_FILE_NAME = "meal_info";
    //用于判断是否调试模式
    public static final boolean IS_DEBUG = BuildConfig.IS_DEBUG;
    //App内接口回调的主机名
    public static final String SERVER_URL = BuildConfig.SERVER_URL;

    //App内接口回调的主机名
    public static final String MEAL_INFO_URL = BuildConfig.MEAL_INFO_URL;

}
