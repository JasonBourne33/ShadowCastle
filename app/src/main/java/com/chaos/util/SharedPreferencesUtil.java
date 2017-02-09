package com.chaos.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.example.administrator.multiplestatusviewtest.R;


public class SharedPreferencesUtil {
	
//	public static void localizationUserInfo(Context mContext, User user) {
//		SharedPreferences preferences = mContext.getSharedPreferences(
//				mContext.getString(R.string.my_app_name), 0);
//		Editor editor = preferences.edit();
//		editor.putString(mContext.getString(R.string.UserNeiMa), user.getUserNeiMa());
//		editor.putString(mContext.getString(R.string.UserName), user.getUserName());
//		editor.putString(mContext.getString(R.string.UserPicture), user.getUserPicture());
//		editor.putString(mContext.getString(R.string.NickName), user.getNickName());
//		editor.putInt(mContext.getString(R.string.Sex), user.getSex());
////		editor.putString(mContext.getString(R.string.Birthday), user.getBirthday());
//		editor.putString(mContext.getString(R.string.Address), user.getAddress());
////		editor.putInt(mContext.getString(R.string.IsVip), user.getIsVip());
////		editor.putInt(mContext.getString(R.string.Points), user.getPoints());
////		editor.putInt(mContext.getString(R.string.UserType), user.getUserType());
//		editor.putString(mContext.getString(R.string.Memo), user.getMemo());
//		editor.putString(mContext.getString(R.string.Mobile), user.getMobile());
//		editor.putString(mContext.getString(R.string.CountryCode), user.getCountryCode());
//		editor.putInt(mContext.getString(R.string.ApproveStatus), user.getApproveStatus());
//		editor.putInt(mContext.getString(R.string.WarnState), user.getWarnState());
//		editor.putString(mContext.getString(R.string.VersionUpdate), user.getVersionUpdate());
//		editor.putString(mContext.getString(R.string.VersionUpdateContent), user.getVersionUpdateContent());
//		editor.putInt(mContext.getString(R.string.HaveSafeOrNot), user.getHaveSafeOrNot());
//		editor.putInt(mContext.getString(R.string.IsFirstLogin),user.getIsFirstLogin());
//		editor.putString(mContext.getString(R.string.LotteryPicture), user.getLotteryPicture());
//		editor.putString("CouponActivityPicture", user.getCouponActivityPicture()==null?"":user.getCouponActivityPicture());
//		editor.commit();
//	}

	public static void clearCouponActivityPicture(Context mContext){
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		Editor editor = preferences.edit();
		editor.putString("CouponActivityPicture", "");
		editor.commit();
	}
	
//	public static User getUserInfoFromShare(Context mContext){
//		User user = new User();
//		SharedPreferences preferences = mContext.getSharedPreferences(
//				mContext.getString(R.string.my_app_name), 0);
//		user.setUserNeiMa(preferences.getString(mContext.getString(R.string.UserNeiMa), "-1"));
//		user.setUserName(preferences.getString(mContext.getString(R.string.UserName), ""));
//		user.setUserPicture(preferences.getString(mContext.getString(R.string.UserPicture), ""));
//		user.setNickName(preferences.getString(mContext.getString(R.string.NickName), ""));
//		user.setSex(preferences.getInt(mContext.getString(R.string.Sex), 0));
////		user.setBirthday(preferences.getString(mContext.getString(R.string.Birthday), ""));
//		user.setAddress(preferences.getString(mContext.getString(R.string.Address), ""));
////		user.setIsVip(preferences.getInt(mContext.getString(R.string.IsVip), 0));
////		user.setUserType(preferences.getInt(mContext.getString(R.string.UserType),0));
////		user.setPoints(preferences.getInt(mContext.getString(R.string.Points), 0));
//		user.setMemo(preferences.getString(mContext.getString(R.string.Memo), ""));
//		user.setMobile(preferences.getString(mContext.getString(R.string.Mobile), ""));
//		user.setCountryCode(preferences.getString(mContext.getString(R.string.CountryCode), ""));
//		user.setApproveStatus(preferences.getInt(mContext.getString(R.string.ApproveStatus), -1));
//		user.setWarnState(preferences.getInt(mContext.getString(R.string.WarnState), 0));
//		user.setVersionUpdate(preferences.getString(mContext.getString(R.string.VersionUpdate), "0"));
//		user.setVersionUpdateContent(preferences.getString(mContext.getString(R.string.VersionUpdateContent), ""));
//		user.setHaveSafeOrNot(preferences.getInt(mContext.getString(R.string.HaveSafeOrNot), 0));
//		user.setIsFirstLogin(preferences.getInt(mContext.getString(R.string.IsFirstLogin), 0));
//		user.setCouponActivityPicture(preferences.getString("CouponActivityPicture", ""));
//		user.setLotteryPicture(preferences.getString("LotteryPicture", ""));
//		return user;
//	}

	
	public static SharedPreferences initSharedPreferences(Context mContext) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		return preferences;
	}

	public static int getInt(Context mContext, int nResID, int nDefault) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		int result = preferences.getInt(mContext.getString(nResID), nDefault);
		return result;
	}

	public static int getInt(Context mContext, int nResID) {
		return getInt(mContext, nResID, 0);
	}

	public static boolean putString(Context mContext, int nResID,
									String strVaule) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);

		return preferences.edit()
				.putString(mContext.getString(nResID), strVaule).commit();

	}

	public static boolean putInt(Context mContext, int nResID, int nVaule) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		return preferences.edit().putInt(mContext.getString(nResID), nVaule)
				.commit();
	}
	
	public static boolean putBoolean(Context mContext, int nResID, boolean nVaule) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		return preferences.edit().putBoolean(mContext.getString(nResID), nVaule)
				.commit();
	}
	
	public static Boolean getBoolean(Context mContext, int nResID) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		Boolean result = preferences.getBoolean(mContext.getString(nResID),false);
		return result;

	}

	public static String getString(Context mContext, int nResID) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		String result = preferences.getString(mContext.getString(nResID), "");
		return result;

	}
	
	public static boolean putLong(Context mContext, int nResID, long nVaule) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		return preferences.edit().putLong(mContext.getString(nResID), nVaule)
				.commit();
	}
	
	public static long getLong(Context mContext, int nResID) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		long result = preferences.getLong(mContext.getString(nResID), 0);
		return result;
	}

	/**
	 * 清除资料
	 * 
	 * @param mContext
	 */
	public static void cleanPreferences(Context mContext) {
		SharedPreferences preferences = mContext.getSharedPreferences(
				mContext.getString(R.string.my_app_name), 0);
		preferences.edit().clear().commit();
	}

	public static String getVersionName(Context mContext) {
		// 获取packagemanager的实例
		PackageManager packageManager = mContext.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo;
		String version = "";
		try {
			packInfo = packageManager.getPackageInfo(mContext.getPackageName(),
					0);
			version = packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return version;
	}
}
