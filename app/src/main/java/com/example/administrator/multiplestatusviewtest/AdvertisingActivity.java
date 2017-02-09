package com.example.administrator.multiplestatusviewtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chaos.sub.SlideShowView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AdvertisingActivity extends AppCompatActivity {


    List<ImageView> list = new ArrayList<ImageView>();
    private List<ImageView> imageViewsList = new ArrayList<ImageView>();
    @Bind(R.id.slideshowView)
    SlideShowView slideshowView;
    @Bind(R.id.img_advertise)
    ImageView ImgAdvertise;
    private ImageLoader imageLoader = null;
    private List<String> names = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising);
        ButterKnife.bind(this);

        imageLoader = ImageLoader.getInstance();
//        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(AdvertisingActivity.this));

        for (int i = 0 ; i < 3 ; i++){
            ImageView imageView = new ImageView(AdvertisingActivity.this);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 145));
            String imgUrl = "http://214.214.1.157:65500/AD/"+(i+5)+".jpg";
            imageLoader.displayImage(imgUrl, imageView);
            imageViewsList.add(imageView);
            names.add("");
        }
        String url = "http://214.214.1.157:65500/AD/7.jpg";
        imageLoader.displayImage(url,ImgAdvertise);
        slideshowView.initData(imageViewsList, names);
    }
}
