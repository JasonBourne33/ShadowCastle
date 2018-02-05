package com.example.administrator.multiplestatusviewtest;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.chaos.util.OpenLocalMapUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leefeng.promptlibrary.PromptButton;
import me.leefeng.promptlibrary.PromptButtonListener;
import me.leefeng.promptlibrary.PromptDialog;

public class OpenLocalMapActivity extends AppCompatActivity {

    @Bind(R.id.tv_address)
    TextView tvAddress;


    //地图初始化
    /**
     * 当前位置
     */
    private static double[] START_LATLON = {120.11649, 30.272873};
    /**
     * 目的地
     */
    private static double[] DESTINATION_TA_LATLON = {120.156132, 30.237626};
    private String SNAME = "我的位置";
    private String DNAME = "终点";
    private String CITY = "杭州";
    private static String APP_NAME = "OPenLocalMapDemo";
    private boolean isOpened;
    private static String SRC = "thirdapp.navi.beiing.openlocalmapdemo";
    private List<PromptButton> listButtons = new ArrayList<PromptButton>();
    PromptButton pbBaiduWeb;
    PromptButton pbBaidu;
    PromptButton pbGaode;
    private PromptDialog promptDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_local_map);

        ButterKnife.bind(this);
        initPromtButton();
    }

    @OnClick(R.id.tv_address)
    public void onClick() {
        PromptButton[] buttons = new PromptButton[listButtons.size()];
        for (int i = 0; i < listButtons.size(); i++) {
            buttons[i] = listButtons.get(i);
        }
        promptDialog.showAlertSheet("选择您的导航地图", true, buttons);
    }

    private void initPromtButton() {
        //创建对象
        promptDialog = new PromptDialog(this);
        //设置自定义属性
        promptDialog.getDefaultBuilder().touchAble(true).round(3).loadingDuration(3000);
        //设置按钮的特点，颜色大小什么的，具体看PromptButton的成员变量
        DNAME = tvAddress.getText().toString();
        PromptButton cancle = new PromptButton("取消", null);
        cancle.setTextColor(Color.parseColor("#0076ff"));
        pbBaiduWeb = new PromptButton("百度网页地图", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                openWebMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME, CITY);
            }
        });
        pbBaidu = new PromptButton("百度地图", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                openBaiduMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME, CITY);
            }
        });
        pbGaode = new PromptButton("高德地图", new PromptButtonListener() {
            @Override
            public void onClick(PromptButton button) {
                openGaoDeMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME);
            }
        });

        listButtons.add(cancle);
        listButtons.add(pbBaiduWeb);
        if (OpenLocalMapUtil.isBaiduMapInstalled()) {
            listButtons.add(pbBaidu);
        }
        if (OpenLocalMapUtil.isGdMapInstalled()) {
            listButtons.add(pbGaode);
        }

    }

    public void openBaiduMap(View view) {
        openBaiduMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME, CITY);
    }

    public void openWebBaiduMap(View view) {
        openWebMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME, CITY);
    }

    public void openGaodeMap(View view) {
        openGaoDeMap(START_LATLON[0], START_LATLON[1], SNAME, DESTINATION_TA_LATLON[0], DESTINATION_TA_LATLON[1], DNAME);
    }

    /**
     * 打开百度地图
     */
    private void openBaiduMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city) {
        if (OpenLocalMapUtil.isBaiduMapInstalled()) {
            try {
                String uri = OpenLocalMapUtil.getBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                        String.valueOf(dlat), String.valueOf(dlon), dname, city, SRC);
                Intent intent = Intent.parseUri(uri, 0);
                startActivity(intent); //启动调用

                isOpened = true;
            } catch (Exception e) {
                isOpened = false;
                e.printStackTrace();
            }
        } else {
            isOpened = false;
        }
    }

    /**
     * 打开高德地图
     */
    private void openGaoDeMap(double slat, double slon, String sname, double dlat, double dlon, String dname) {
        if (OpenLocalMapUtil.isGdMapInstalled()) {
            try {
                CoordinateConverter converter = new CoordinateConverter(this);
                converter.from(CoordinateConverter.CoordType.BAIDU);
                DPoint sPoint = null, dPoint = null;
                try {
                    converter.coord(new DPoint(slat, slon));
                    sPoint = converter.convert();
                    converter.coord(new DPoint(dlat, dlon));
                    dPoint = converter.convert();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (sPoint != null && dPoint != null) {
                    String uri = OpenLocalMapUtil.getGdMapUri(APP_NAME, String.valueOf(sPoint.getLatitude()), String.valueOf(sPoint.getLongitude()),
                            sname, String.valueOf(dPoint.getLatitude()), String.valueOf(dPoint.getLongitude()), dname);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setPackage("com.autonavi.minimap");
                    intent.setData(Uri.parse(uri));
                    startActivity(intent); //启动调用

                    isOpened = true;
                }
            } catch (Exception e) {
                isOpened = false;
                e.printStackTrace();
            }
        } else {
            isOpened = false;
        }
    }

    /**
     * 打开浏览器进行百度地图导航
     */
    private void openWebMap(double slat, double slon, String sname, double dlat, double dlon, String dname, String city) {
        Uri mapUri = Uri.parse(OpenLocalMapUtil.getWebBaiduMapUri(String.valueOf(slat), String.valueOf(slon), sname,
                String.valueOf(dlat), String.valueOf(dlon),
                dname, city, APP_NAME));
        Intent loction = new Intent(Intent.ACTION_VIEW, mapUri);
        startActivity(loction);
    }
}
