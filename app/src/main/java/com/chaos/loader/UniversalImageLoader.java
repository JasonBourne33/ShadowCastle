package com.chaos.loader;

import android.content.Context;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2017/1/19.
 */

public class UniversalImageLoader extends com.chaos.loader.ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        ImageLoader loader=ImageLoader.getInstance();
        loader.displayImage(path.toString(),imageView);

    }
}
