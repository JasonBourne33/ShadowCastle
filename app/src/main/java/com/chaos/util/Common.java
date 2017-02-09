package com.chaos.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Locale;


public class Common {
	public final static String TAG = "myLog";

	public final static String previewTempFilePath = Environment.getExternalStorageDirectory() + "/forecast_res/";

	/**
	 * 打印日志
	 * 
	 * @param msg
	 */
	public static void log(String msg) {
		Log.i(TAG, msg);
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getWidthPixels(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param activity
	 * @return
	 */
	public static int getHeightPixels(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	public static String getgetLanguage() {
		return Locale.getDefault().getLanguage() + "_" + Locale.getDefault().getCountry();
	}

	/**
	 * 返回当前程序版本名
	 */
	public static String getAppVersionName(Context context) {
		String versionName = "";
		int versioncode = 0;
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versionName = pi.versionName;
			versioncode = pi.versionCode;
			if (versionName == null || versionName.length() <= 0) {
				return "";
			}
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versionName;
	}
	
	public static int getAppVersionCode(Context context) {
		int versioncode = 0;
		try {
			// ---get the package info---
			PackageManager pm = context.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			versioncode = pi.versionCode;
		} catch (Exception e) {
			Log.e("VersionInfo", "Exception", e);
		}
		return versioncode;
	}

	public static int getInt(JSONObject obj, String key) {
		int value = 0;
		try {
			value = obj.getInt(key);
		} catch (Exception e) {
		}

		return value;
	}

	public static boolean getBoolean(JSONObject obj, String key) {
		boolean value = false;
		try {
			value = obj.getBoolean(key);
		} catch (Exception e) {
		}

		return value;
	}

	public static String getString(JSONObject obj, String key) {
		String value = "";
		try {
			value = obj.getString(key);
		} catch (Exception e) {
		}

		return value;
	}

	public static long getLong(JSONObject obj, String key) {
		long value = 0;
		try {
			value = obj.getLong(key);
		} catch (Exception e) {
		}

		return value;
	}

	public static double getDouble(JSONObject obj, String key) {
		double value = 0.0;
		try {
			value = obj.getDouble(key);
		} catch (Exception e) {
		}

		return value;
	}

	public static JSONObject getJSONObject(JSONObject obj, String key) {
		JSONObject json = null;
		try {
			json = obj.getJSONObject(key);
		} catch (Exception e) {
		}

		return json;
	}

	public static JSONArray getJSONArray(JSONObject obj, String key) {
		JSONArray json = null;
		try {
			json = obj.getJSONArray(key);
		} catch (Exception e) {
		}

		return json;
	}

	public static JSONObject getJSONObject(JSONArray array, int index) {
		JSONObject json = null;
		try {
			json = array.getJSONObject(index);
		} catch (Exception e) {
		}

		return json;
	}

	public static JSONArray getJSONArray(JSONArray array, int index) {
		JSONArray json = null;
		try {
			json = array.getJSONArray(index);
		} catch (Exception e) {
		}

		return json;
	}

	/**
	 * 保存文件
	 * 
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public static void saveFile(Bitmap bm, String fileName) {
		try {
			File dirFile = new File(previewTempFilePath);
			if (!dirFile.exists()) {
				dirFile.mkdir();
			}
			File myCaptureFile = new File(previewTempFilePath + fileName);
			if (myCaptureFile.exists()) {
				myCaptureFile.delete();
			}

			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
			if (fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".jpge")) {
				bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
			} else {
				bm.compress(Bitmap.CompressFormat.PNG, 100, bos);
			}
			bos.flush();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getBitmap(String fileName) {
		File mFile = new File(previewTempFilePath + fileName);
		// 若该文件存在
		if (mFile.exists()) {
			Bitmap bitmap = BitmapFactory.decodeFile(previewTempFilePath + fileName);
			return bitmap;
		}
		return null;
	}

	public static String changeFloat(String stringFloat) {
		if (stringFloat.length() <= 1) {
			return stringFloat;
		}

		if (!stringFloat.contains(".")) {
			return stringFloat;
		}

		if (stringFloat.endsWith(".000")) {
			return stringFloat.substring(0, stringFloat.length() - 4);
		}

		if (stringFloat.endsWith(".00")) {
			return stringFloat.substring(0, stringFloat.length() - 3);
		}

		if (stringFloat.endsWith(".0")) {
			return stringFloat.substring(0, stringFloat.length() - 2);
		}

		return stringFloat;
	}

	public static String getStringForAuto(String cnString, String hkString) {
		if (getgetLanguage().equals("zh_CN")) {
			return cnString;
		}

		return hkString;
	}

	public static String getDeviceInfo(Context context) {
		try {
			android.telephony.TelephonyManager tm = (android.telephony.TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

			String device_id = tm.getDeviceId();

			android.net.wifi.WifiManager wifi = (android.net.wifi.WifiManager) context.getSystemService(Context.WIFI_SERVICE);

			String mac = wifi.getConnectionInfo().getMacAddress();
			if (TextUtils.isEmpty(device_id)) {
				device_id = mac;
			}

			if (TextUtils.isEmpty(device_id)) {
				device_id = android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
			}

			return mac + "|" + device_id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int[] getMeasuredSize(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int height = view.getMeasuredHeight();
		int width = view.getMeasuredWidth();
		return new int[] { width, height };
	}

	/*
	 * 设置控件所在的位置YY，并且不改变宽高， XY为绝对位置
	 */
	public static void setCenter(View view, int x, int y) {
		int[] size = Common.getMeasuredSize(view);
		view.setX(x - size[0] / 2);
		view.setY(y - size[1] / 2);
	}

	/**
	 * 字符串转Timestamp yyyy-mm-dd hh:mm:ss
	 * 
	 * @param timeStr
	 * @return
	 */
	public static Timestamp strToTimestamp(String timeStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(timeStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	public static int getDiffTimeSecond(Timestamp formatTime1, Timestamp formatTime2) {
		SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long t1 = 0L;
		long t2 = 0L;
		try {
			t1 = timeformat.parse(getTimeStampNumberFormat(formatTime1)).getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2 = timeformat.parse(getTimeStampNumberFormat(formatTime2)).getTime();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 因为t1-t2得到的是毫秒级,所以要初3600000得出小时.算天数或秒同理
		int second = (int) ((t1 - t2) / 1000);
		return second;
	}

	/**
	 * 格式化时间 Locale是设置语言敏感操作
	 * 
	 * @param formatTime
	 * @return
	 */
	public static String getTimeStampNumberFormat(Timestamp formatTime) {
		SimpleDateFormat m_format = new SimpleDateFormat("yyyy-MM-dd,HH:mm:ss", new Locale("zh", "cn"));
		return m_format.format(formatTime);
	}

	public static String getShowNumber(int value) {
		StringBuffer str = new StringBuffer(String.valueOf(value));
		if (str.length() <= 3) {
			return str.toString();
		}

		long d = str.length() / 3;
		long s = 0;
		if (str.length() % 3 == 0) {
			d--;
			s += 3;
		}
		s += str.length() % 3;
		for (long i = d - 1; i >= 0; i--) {
			str.insert((int) (s + i * 3), ",");
		}

		return str.toString();
	}

	public static class SortBySoccerAndNbaStartTimeAsc implements Comparator<JSONObject> {
		public int compare(JSONObject info1, JSONObject info2) {
			String timeStr1 = Common.getString(Common.getJSONObject(info1, "datastr"), "gtf");
			String timeStr2 = Common.getString(Common.getJSONObject(info2, "datastr"), "gtf");
			timeStr1 = timeStr1.replace("-", "").replace(":", "").replace(" ", "");
			timeStr2 = timeStr2.replace("-", "").replace(":", "").replace(" ", "");

			long time1 = Long.parseLong(timeStr1);
			long time2 = Long.parseLong(timeStr2);
			return (int) (time1 - time2);
		}
	}

	public static class SortBySoccerAndNbaStartTimeDesc implements Comparator<JSONObject> {
		public int compare(JSONObject info1, JSONObject info2) {
			String timeStr1 = Common.getString(Common.getJSONObject(info1, "datastr"), "gtf");
			String timeStr2 = Common.getString(Common.getJSONObject(info2, "datastr"), "gtf");
			timeStr1 = timeStr1.replace("-", "").replace(":", "").replace(" ", "");
			timeStr2 = timeStr2.replace("-", "").replace(":", "").replace(" ", "");

			long time1 = Long.parseLong(timeStr1);
			long time2 = Long.parseLong(timeStr2);
			return (int) (time2 - time1);
		}
	}

	// public static void showShare(final Context context, String title, String
	// message){
	// File dirFile = new File(previewTempFilePath);
	// if (!dirFile.exists()) {
	// dirFile.mkdir();
	// }
	//
	// File myCaptureFile = new File(previewTempFilePath + "share_logo.png");
	// if (!myCaptureFile.exists()) {
	// try {
	// copyBigDataToSD(context, myCaptureFile.getAbsolutePath());
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	//
	//
	//
	// OnekeyShare oks = new OnekeyShare();
	// oks.setSilent(true);
	//
	// oks.setTitle("");
	// oks.setTitleUrl("http://www.2pick1.net/");
	// oks.setText(message);
	// oks.setImagePath(myCaptureFile.getAbsolutePath());
	// // oks.setImageUrl("http://www.2pick1.net/share-logo.jpg");
	// //
	// oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
	// oks.setUrl("http://www.2pick1.net/"); //微信不绕过审核分享链接
	//
	// oks.setSite(""); //QZone分享完之后返回应用时提示框上显示的名称
	// oks.setSiteUrl("http://www.2pick1.net/");//QZone分享参数
	// oks.setVenueName("");
	// //context.getResources().getString(R.string.app_name)
	// oks.setCallback(new PlatformActionListener() {
	//
	// @Override
	// public void onError(Platform arg0, int arg1, Throwable arg2) {
	// Common.log("onError=" + arg2.getMessage());
	// Toast.makeText(context,
	// context.getResources().getString(R.string.TXT_169),Toast.LENGTH_LONG).show();
	// }
	//
	// @Override
	// public void onComplete(Platform arg0, int arg1, HashMap<String, Object>
	// arg2) {
	// Common.log("onComplete");
	// Toast.makeText(context,
	// context.getResources().getString(R.string.TXT_168),Toast.LENGTH_LONG).show();
	// }
	//
	// @Override
	// public void onCancel(Platform arg0, int arg1) {
	// Common.log("onCancel");
	// }
	// });
	//
	// // 启动分享GUI
	// oks.show(context);
	// }



	public static String getColorString(int r, int g, int b) {
		String v1 = Integer.toHexString(r);
		String v2 = Integer.toHexString(g);
		String v3 = Integer.toHexString(b);
		if (v1.length() == 1) {
			v1 = "0" + v1;
		}
		if (v2.length() == 1) {
			v2 = "0" + v2;
		}
		if (v3.length() == 1) {
			v3 = "0" + v3;
		}
		return "#" + v1 + v2 + v3;
	}
}
