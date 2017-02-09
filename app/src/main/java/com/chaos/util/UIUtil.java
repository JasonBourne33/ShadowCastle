package com.chaos.util;

import android.app.Activity;
import android.os.Build;
import android.view.View;

import com.example.administrator.multiplestatusviewtest.R;


/**
 * Created by KevinDan on 16/8/9.
 */
public class UIUtil {
    /**适配标题栏高度函数*/
    public static void adaptationTopbarHeight(Activity activity){
        adaptationTopbarHeight(activity, R.id.topbar);
    }

    /**适配标题栏高度函数*/
    public static void adaptationTopbarHeight(Activity activity, int resid){
        adaptationTopbarHeight(activity, resid, null);
    }

    public static void adaptationTopbarHeight(Activity activity, int resid, View view){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View v = view!=null ? view.findViewById(resid) : activity.findViewById(resid);
            int pxValue = DensityUtil.dip2px(activity, 20);
            if (v != null)
                v.setPadding(v.getPaddingLeft(), pxValue, v.getPaddingRight(), v.getPaddingBottom());

        }
    }

//    public static void setImage(ImageView iv, String url){
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        imageLoader.displayImage(url, iv, CustomImageOptions.getOptions(R.drawable.logo_default));
//    }
//
//    public static void setImage(ImageView iv, String url, int loadImgRes){
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        imageLoader.displayImage(url, iv, CustomImageOptions.getOptions(loadImgRes));
//    }
//    public static void setImage(ImageView iv, String url, int loadImgRes, int round){
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        imageLoader.displayImage(url, iv, CustomImageOptions.getRoundOptions(loadImgRes,round));
//    }
//
//    public static  void removeImageCache(String url){
//        ImageLoader imageLoader = ImageLoader.getInstance();
//        DiscCacheUtils.removeFromCache(url, imageLoader.getDiskCache());
//        MemoryCacheUtils.removeFromCache(url, imageLoader.getMemoryCache());
//    }
//
//    public static View addBottomView(ListView listView, Context context, View.OnClickListener clickListner){
//        View buttomView = LayoutInflater.from(context).inflate(R.layout.pulladdmore, null);
//        listView.addFooterView(buttomView, null, false);
//        buttomView.setOnClickListener(clickListner);
//        return buttomView;
//    }
//
//    public static void startLoading(View buttomView){
//        buttomView.findViewById(R.id.addmore_text).setVisibility(View.GONE);
//        buttomView.findViewById(R.id.addmore_bar).setVisibility(View.VISIBLE);
//    }
//    public static void finishLoading(View buttomView){
//        buttomView.findViewById(R.id.addmore_text).setVisibility(View.VISIBLE);
//        buttomView.findViewById(R.id.addmore_bar).setVisibility(View.GONE);
//
//    }
}
