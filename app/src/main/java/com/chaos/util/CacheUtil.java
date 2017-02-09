package com.chaos.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.util.Base64;

public class CacheUtil {
		
		private static SharedPreferences sharedPreferences =null;
		private static String cacheTag="question";
		
		/**
		 * ȡ�û���
		 * @param context
		 * @param key
		 * @return
		 */
		public static String getCache(Context context, String key) {
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			String value=sharedPreferences.getString(key, "");
			return value;
		}

		/**
		 * ���滺��
		 * @param context
		 * @param key
		 * @param value
		 */
		public static void setCache(Context context, String key,String value){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			Editor editor=sharedPreferences.edit();
			editor.putString(key,value);
			editor.commit();
		}
		
		/**
		 * ���ֻ���
		 * @param context
		 * @param key
		 * @param value
		 */
		public static void setLongCache(Context context, String key,long value){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			Editor editor=sharedPreferences.edit();
			editor.putLong(key, value);
			editor.commit();
		}
		/**
		 * ȡ���ֻ���
		 * @param context
		 * @param key
		 * @return
		 */
		public static Long getLongCache(Context context, String key) {
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			Long value=sharedPreferences.getLong(key, 0);
			return value;
		}
		
		/**
		 * ���ֻ���
		 * @param context
		 * @param key
		 * @param value
		 */
		public static void setIntCache(Context context, String key,int value){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			Editor editor=sharedPreferences.edit();
			editor.putInt(key, value);
			editor.commit();
		}
		
		
		/**
		 * ȡ���ֻ���
		 * @param context
		 * @param key
		 * @return
		 */
		public static int getIntCache(Context context, String key) {
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			int value=sharedPreferences.getInt(key, 0);
			return value;
		}
		
		/**
		 * ��boolean����
		 * @param context
		 * @param key
		 * @param value
		 */
		public static void setBooleanCache(Context context, String key,boolean value){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			Editor editor=sharedPreferences.edit();
			editor.putBoolean(key, value);
			editor.commit();
		}
		
		
		/**
		 * ȡboolean����
		 * @param context
		 * @param key
		 * @return
		 */
		public static boolean getBooleanCache(Context context, String key) {
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			boolean value=sharedPreferences.getBoolean(key, false);
			return value;
		}
		
		
		/**
		 * ����û���������
		 * @param context
		 */
		public static void setCacheClear(Context context){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			sharedPreferences.edit().clear().commit();
		}
		
		/**
		 * ��¼����
		 * @param context
		 */
		public static void setLoginCache(Context context,String key,String value){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			 Editor editor= sharedPreferences.edit();
			 editor.putString(key, new String(Base64.encode(value.getBytes(), Base64.DEFAULT)));
			 editor.commit();
		}
		
		/**
		 * ��ȡ��¼����
		 * @param context
		 */
		public static String getLoginCache(Context context,String key){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			String value = new String(Base64.decode(sharedPreferences.getString(key, "").getBytes(),Base64.DEFAULT));
			return value;
		}
		
		/**
		 * ע���sharePreference�ļ���
		 * @param context
		 */
		public static void registerOnSharedPreferenceChangeListener(Context context){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			sharedPreferences.registerOnSharedPreferenceChangeListener(mListener);
		}
		

		/**
		 * ע����sharePreference�ļ���
		 * @param context
		 */
		public static void unregisterOnSharedPreferenceChangeListener(Context context){
			if(sharedPreferences==null){
				sharedPreferences=context.getSharedPreferences(cacheTag, context.MODE_PRIVATE);
			}
			sharedPreferences.unregisterOnSharedPreferenceChangeListener(mListener);
			onMySharedPreferenceChangeListener = null;
		}
		
		/**
		 * ��sharePreference�����ı�ʱ,�ص��÷���
		 */
		private static OnSharedPreferenceChangeListener mListener = new OnSharedPreferenceChangeListener() {
			  @Override
			  public void onSharedPreferenceChanged(
			      SharedPreferences sharedPreferences, String key) {
				  if (onMySharedPreferenceChangeListener != null){
					  onMySharedPreferenceChangeListener.onSharePreferenceKeyBack(key);
				  }
				  
			  }
			};
			
		public static OnMySharedPreferenceChangeListener onMySharedPreferenceChangeListener;
			
			
		public static void setUpDataSharePreference(OnMySharedPreferenceChangeListener onSharedPreferenceChangeListener) {
			onMySharedPreferenceChangeListener = onSharedPreferenceChangeListener;
		}

		
		public interface OnMySharedPreferenceChangeListener {
			public void onSharePreferenceKeyBack(String key);
		}	
			

		/*********************************����***********************************/
		public static String getText(Context context,String key){
			SharedPreferences  sharedPreferences=context.getSharedPreferences("test", context.MODE_APPEND);
			return sharedPreferences.getString(key, "");
		}
		public static void setText(Context context,String key,String value){
			SharedPreferences  sharedPreferences=context.getSharedPreferences("test", context.MODE_APPEND);
			Editor editor=sharedPreferences.edit();
			editor.putString(key, value);
			editor.commit();
		}
		/********************************************************************/


	
}
