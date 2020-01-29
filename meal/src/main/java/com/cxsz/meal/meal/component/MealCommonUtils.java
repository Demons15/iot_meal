package com.cxsz.meal.meal.component;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MealCommonUtils {

    public static void hideSoft(Activity activity) {
        if (activity != null && activity.getCurrentFocus() != null) {
            ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static String getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);

    }

    /**
     * 保留两位小数
     */
    public static double remain2(double f) {
        BigDecimal bg = new BigDecimal(f);
        return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        Date d1 = new Date();
        return format.format(d1);
    }

    public static void formatFlowSize(double size, TextView flowSize) {
        double fileSizeString = 0;
        if (size < 1024) {
            fileSizeString = size;
            flowSize.setText(fileSizeString + "");
        } else if (size > 1024) {
            fileSizeString = size / 1024;
            String doubleValue = String.format("%.2f", fileSizeString);
            flowSize.setText(doubleValue + "");
        }
    }

    public static void formatFlowSizeAndUnit(double size, TextView flowSize) {
        double fileSizeString = 0;
        if (size < 1024) {
            fileSizeString = size;
            flowSize.setText(fileSizeString + "MB");
        } else if (size > 1024) {
            fileSizeString = size / 1024;
            flowSize.setText(fileSizeString + "G");
        }
    }

    public static long coverStringTimeToLongTime(String time) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar.getTimeInMillis();
    }

    public static boolean compare(String time) {
        //如果想比较日期则写成"yyyy-MM-dd"就可以了
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        //将字符串形式的时间转化为Date类型的时间
        Date b = null;
        try {
            b = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date a = new Date();
        //Date类的一个方法，如果a早于b返回true，否则返回false
        if (a.before(b))
            return true;
        else
            return false;
		/*
		 * 如果你不喜欢用上面这个太流氓的方法，也可以根据将Date转换成毫秒
		if(a.getTime()-b.getTime()<0)
			return true;
		else
			return false;
		*/
    }
}
