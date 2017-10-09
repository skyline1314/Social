package com.skyline.social.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.skyline.social.base.App;

public class DisplayUtils {
	/**
	 * 将px值转换为dp值
	 */
	public static int px2dp( float pxValue) {
		final float scale = App.app.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dp值转换为px值
	 */
	public static int dp2px(float dpValue) {
		final float scale = App.app.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值
	 */
	public static int px2sp(float pxValue) {
		final float fontScale = App.app.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值
	 */
	public static int sp2px(float spValue) {
		final float fontScale = App.app.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取屏幕宽度
	 */
	public static int getScreenWidthPixels(Activity context) {
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 */
	public static int getScreenHeightPixels(Activity context) {
		DisplayMetrics metric = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(metric);
		return metric.heightPixels;
	}


	/**
	 * 获取状态栏的高度
	 */
	public static int getStatusHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		if (activity != null) {
			activity.getWindow().getDecorView()
					.getWindowVisibleDisplayFrame(localRect);
			statusHeight = localRect.top;
			if (0 == statusHeight) {
				Class<?> localClass;
				try {
					localClass = Class.forName("com.android.internal.R$dimen");
					Object localObject = localClass.newInstance();
					int i5 = Integer.parseInt(localClass
							.getField("status_bar_height").get(localObject)
							.toString());
					statusHeight = activity.getResources()
							.getDimensionPixelSize(i5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return statusHeight;
	}


}
