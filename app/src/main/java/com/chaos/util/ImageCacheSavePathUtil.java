package com.chaos.util;

import android.content.Context;
import android.os.Environment;


import com.example.administrator.multiplestatusviewtest.R;

import java.io.File;


public class ImageCacheSavePathUtil {

	/**
	 * 返圖片拍照的保存路徑、imageLoader的圖片緩存路徑
	 * @return 
	 *
	 */
	public static String getImageSavePath(Context context) {
		File DataDir = null;
		String Path = null;
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			DataDir = Environment.getExternalStorageDirectory();
			Path = context.getString(R.string.SDcardPath);
		} else {
			DataDir = Environment.getDataDirectory();
			Path = context.getString(R.string.DataPath);
		}
		StringBuffer DataPath = new StringBuffer();
		StringBuffer Cachepath = new StringBuffer();
		DataPath.append(DataDir.getPath()).append(Path);
		Cachepath.append(DataPath.toString()).append(".cache");
		File cache = new File(Cachepath.toString());
		if (!cache.exists()) {
			cache.mkdirs();
		}
		return DataPath.toString();
	}

	/**
	 * @author 返回音頻的緩存路徑
	 * @return 
	 *
	 */
//	public static String getRecorderSavePath(Context context){
//		File DataDir = null;
//		String Path = null;
//		if (Environment.getExternalStorageState().equals(
//				Environment.MEDIA_MOUNTED)) {
//			DataDir = Environment.getExternalStorageDirectory();
//			Path = context.getString(R.string.RECORDER_SDCARD_Path);
//		} else {
//			DataDir = Environment.getDataDirectory();
//			Path = context.getString(R.string.RECORDER_PATH);
//		}
//		String DataPath = DataDir.getPath()+Path;
//		File cache = new File(DataPath);
//		if (!cache.exists()) {
//			cache.mkdirs();
//		}
//		return DataPath.toString();
//	}
	
	/**
	 *  返回圖片保存的緩存路徑
	 * @return 
	 *
	 */
	public static String getMyImageSavePath(Context context){
		File DataDir = null;
		String Path = "/anxin/";
		String DataPath = "";
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			DataDir = Environment.getExternalStorageDirectory();
			 DataPath = DataDir.getPath()+Path;
		} else {
//			DataDir = Environment.getDataDirectory();
			DataPath = "/data/data/com.mit.merchant"+Path;
		}
		
		File cache = new File(DataPath);
		if (!cache.exists()) {
			cache.mkdirs();
		}
		return DataPath.toString();
	}
}

