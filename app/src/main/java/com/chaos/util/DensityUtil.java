package com.chaos.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author Administrator
 * 屏幕单位转换
 */
public class DensityUtil {
	public static int sp2px(Context context, float spValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static int px2sp(Context context, float pxValue) {
        float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    public static int dip2px(Context context, int dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    public static int[] getDisplayMetrics(Context context){
    	DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    	((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
    	int W = mDisplayMetrics.widthPixels;
    	int H = mDisplayMetrics.heightPixels;
//    	Log.i("Main", "Width = " + W);
//    	Log.i("Main", "Height = " + H);
    	int array[] = {W,H};
    	return array;
    }
    /**
     * @param 返回對話文本框的長度
     * @return
     */
    public static int getChatMaxWidth(Context context){
    	int caculateWidth = 200;
    	int density[] =  getDisplayMetrics(context);
    	int width = density[0];
    	int	height = density[1];
    	if (width == 1080 && height == 1920) {
    		caculateWidth = 220;
		}else if(width == 768 && height == 1280){
			caculateWidth = 250;
		}else if(width == 480 && height == 800){
			caculateWidth = 200;
		}else if(width == 1600 && height == 2560){
			caculateWidth = 600;
		}else if(width == 1200 && height == 1920){
			caculateWidth = 450;
		}else if(width == 800 && height == 1280){
			caculateWidth = 240;
		}else{
			caculateWidth = 200;
		}
    	return dip2px(context, caculateWidth);
    }
    
    /**
     * @param 返回錄音框的長度
     * @return
     */
    public static int getAudioWidth(Context context){
    	int caculateWidth = 120;
    	int density[] =  getDisplayMetrics(context);
    	int width = density[0];
    	int	height = density[1];
    	if (width == 1080 && height == 1920) {
    		caculateWidth = 160;
		}else if(width == 768 && height == 1280){
			caculateWidth = 120;
		}else if(width == 480 && height == 800){
			caculateWidth = 80;
		}else if(width == 1600 && height == 2560){
			caculateWidth = 200;
		}else if(width == 1200 && height == 1920){
			caculateWidth = 180;
		}else if(width == 800 && height == 1280){
			caculateWidth = 120;
		}else{
			caculateWidth = 120;
		}
    	return caculateWidth;
    }

	public static int dp2px(Context context, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
	}
}
