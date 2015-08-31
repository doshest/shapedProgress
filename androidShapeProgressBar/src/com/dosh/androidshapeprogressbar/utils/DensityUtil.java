package com.dosh.androidshapeprogressbar.utils;

import android.content.Context;
import android.util.TypedValue;

public class DensityUtil {
	public static int dp2Px(float dp, Context context) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}
}
