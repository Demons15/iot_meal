package common.constant;

/**
 * 全局的静态变量
 */
public class KeyConstants {
    /**
     * 是否第一次启动
     */
    /*Permissions Code*/
    public static final int CHECK_GPS_CODE = 0x1;
    public static final int PERMISSION_LOCATION_CODE = 0x2;
    //    public static final int PERMISSION_STORAGE_CODE = 0x3;
//    public static final int PERMISSION_MICROPHONE_CODE = 0x4;
    public static final int PERMISSION_READ_PHONE_STATE_CODE = 0x5;
    public static final int PERMISSION_ACCESS_COARSE_LOCATION_CODE = 0x6;
    public static final int PERMISSION_CAMERA_CODE = 0x7;
    public static final int PERMISSION_CALL_PHONE = 0x8;
    public static final int PERMISSION_MODIFY_AUDIO_SETTINGS = 0x9;
    public static final int PERMISSION_SYSTEM_ALERT_WINDOW = 0x10;
    public static final int PERMISSION_WAKE_LOCK = 0x11;

    public static final int SWEEP_RESULT_CODE = 0x12;
    public static final int OPEN_SWEEP_CODE = 0x13;
    public static final int TO_BUY_MEAL = 0x14;
    public static final int TO_BUY_FLOW_MEAL = 0x15;
    public static final int TO_BUY_VOICE_MEAL = 0x16;
    public static final int REFRESH_MEAL = 0x17;
    public static final int FINISH_MEAL_VIEW = 0x18;
    public static final int SELECT_LOCATION = 0x19;
    public static final int NEW_ADD_ADDRESS = 0x20;
    public static final int MODIFY_ADDRESS = 0x21;

    public static final String FIRST_LOGIN = "first_login";
    public static final String NET_TOKEN = "net_token";
    public static final String NET_USER_ID = "net_user_id";
    public static final String SWEEP_RESULT = "sweep_result";

    public static final String SP_INFO = "meal_info";

    //模块之间跳转的命名

    public static final String MEAL_DETAILS = "com.iot.mealDetails";
    public static final String MEAL_MAIN_UI = "com.iot.mealMain";
    public static final String REAL_NAME = "com.iot.realName";
    public static final String MEAL_INFO_BEAN = "MealInfoBean";


    public static final String PACKET_INFO = "packet_info";

    public static final String MESSAGE_LIST = "message_list";

    public static final String SEARCH_TYPE = "search_type";

    public static final String MEAL_SHOW_MODEL = "meal_show_model";

    public static final String CARD_BUY_ACTIVITY = "/buy/CardBuyActivity";
    public static final String LOGIN_ACTIVITY = "WeChatLoginActivity";
    public static final String MOBILE = "mobile";
    public static final String OPENID = "openid";

    public static final String WE_CHAT_LIST_START_TIME = "we_chat_list_start_time";
    public static final String LOCATION_INFO = "location_info";
    public static final String MODIFY_LOCATION_INFO = "modify_location_info";

    public static final String ADDRESS_BEAN = "address_bean";
    public static final String CONTACT_INPUT_TYPE = "1";

    public static final String ADD_CONTACT = "add";
    public static final String DELETE_CONTACT = "del";

    public static final String RESPONSE_REAL_NAME_DIAGNOSIS = "ResponseRealNameDiagnosis";
    public static final String RESPONSE_CARD_PACKAGE_DIAGNOSIS = "ResponseCardPackageDiagnosis";
    public static final String RESPONSE_SYNCHRONIZATION_CARD_STATUS = "ResponseSynchronizationCardStatus";
    public static final String RESPONSE_UPDATE_VOICE_DATA = "ResponseUpdateVoiceData";
    public static final String RESPONSE_UPDATE_TRAFFIC_DATA = "ResponseUpdateTrafficData";
    public static final String RESPONSE_FLOW_DETECTION = "ResponseFlowDetection";
    public static final String RESPONSE_SPEECH_DETECTION = "ResponseSpeechDetection";
    public static final String RESPONSE_WHITE_LIST_DIAGNOSIS = "ResponseWhiteListDiagnosis";
    public static final String RESPONSE_READ_CARD_STATUS = "ResponseReadCardStatus";
}
