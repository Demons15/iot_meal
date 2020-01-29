package common.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;

import androidx.annotation.ColorInt;


/*
 * 与屏幕信息相关的工具类，如宽高，密度，转换等
 */
public class ScreenUtil {

	private static DisplayMetrics initScreen(Context context) {
		return context.getResources().getDisplayMetrics();
	}

	public static int getWidth(Context activity) {
		return initScreen(activity).widthPixels;
	}

	public static int getHeight(Context context) {
		return initScreen(context).heightPixels;
	}

	public static int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue/scale +0.5f);
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/*
	 * 获取状态栏高度
	 */
	public static int getStatusBarHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		// int statusBarHeight =
		return frame.top;
	}

	/*
	 * 反射方式获取状态栏高度
	 */
	public static int getStatusBarHeightByReflact(Activity activity) {
		try {
			Class<?> c = Class.forName("com.android.internal.R$dimen");
			Object obj = c.newInstance();
			Field field = c.getField("status_bar_height");
			int x = Integer.parseInt(field.get(obj).toString());
			int sbar = activity.getResources().getDimensionPixelSize(x);
			return sbar;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/*
	 * 获取标题栏高度
	 */
	public static int gettitleBarHeight(Activity activity, int statusBarHeight) {
		int contentTop = activity.getWindow()
				.findViewById(Window.ID_ANDROID_CONTENT).getTop();
		if (statusBarHeight <= 0)
			statusBarHeight = getStatusBarHeight(activity);
		// statusBarHeight是上面所求的状态栏的高度
		int titleBarHeight = contentTop - statusBarHeight;
		return titleBarHeight;
	}

	/*
	 * 得到view 的宽高
	 */
	public static int[] getViewWH(Activity activity, View view) {
		int width = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);

		int height = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);

		view.measure(width, height);
		int w = view.getMeasuredWidth();
		int h = view.getMeasuredHeight();
		return new int[] { w, h };
	}

	/**
	 * 获得屏幕宽度，单位px
	 *
	 * @param context 上下文
	 * @return 屏幕宽度
	 */
	public static int getScreenWidth(Context context) {
		if(context==null)return 0;
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.widthPixels;
	}


	/**
	 * 获得屏幕高度
	 *
	 * @param context 上下文
	 * @return 屏幕除去通知栏的高度
	 */
	public static int getScreenHeight(Context context) {
		if(context==null)return 0;
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		return dm.heightPixels-getStatusBarHeight(context);
	}
	/**
	 * 获取通知栏高度
	 *
	 * @param context 上下文
	 * @return 通知栏高度
	 */
	public static int getStatusBarHeight(Context context) {
		int statusBarHeight = 0;
		try {
			Class<?> clazz = Class.forName("com.android.internal.R$dimen");
			Object obj = clazz.newInstance();
			Field field = clazz.getField("status_bar_height");
			int temp = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(temp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}

	/**
	 * dp转px
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static float dpTopx(Context context, float dpValue) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
	}

	/**
	 * 获取文本的宽度
	 *
	 * @return
	 */
	public static int getTextWidth(String text, Paint paint) {
		int textWidth = 0;
		for (int index = 0; index < text.length(); index++) {
			char ch = text.charAt(index);
			float[] widths = new float[1];
			String srt = String.valueOf(ch);
			paint.getTextWidths(srt, widths);
			textWidth += widths[0];
		}
		return textWidth;
	}

	/**
	 * 获取文本的高度(int)
	 *
	 * @return
	 */
	public static int getTextHeightForInt(Paint paint) {
		Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
		return fontMetrics.bottom - fontMetrics.top;
	}


	/**
	 * 状态颜色且状态栏字符变深
	 * @param activity
	 * @param color
	 */
	public static void setColor(Activity activity, @ColorInt int color) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			activity.getWindow().setStatusBarColor(color);
		}
	}
}
