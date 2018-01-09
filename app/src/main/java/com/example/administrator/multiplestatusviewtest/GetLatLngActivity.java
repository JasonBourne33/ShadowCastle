package com.example.administrator.multiplestatusviewtest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GetLatLngActivity extends AppCompatActivity {

    @Bind(R.id.tv_loaction)
    TextView tvLoaction;

    private String provider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_lat_lng);
        ButterKnife.bind(this);
        List<String> list = new ArrayList<String>();
        list = getLngAndLat(this);
        System.out.println("lat=== "+list.get(0)+" lng=== "+list.get(1));
        tvLoaction.setText("lat=== "+list.get(0)+" lng=== "+list.get(1));
    }

    /**
     * 获取经纬度
     *
     * @param context
     * @return
     */
    private LocationManager locationManager;
    private List<String> getLngAndLat(Context context) {
        List<String> list = new ArrayList<String>();
        double latitude = 0.0;
        double longitude = 0.0;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {  //从gps获取经纬度
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                } else {//当GPS信号弱没获取到位置的时候又从网络获取
                    return getLngAndLatWithNetwork();
                }
            } else {    //从网络获取经纬度
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    list.add(String.valueOf(latitude));
                    list.add(String.valueOf(longitude));
                }
            }
        }
        return list;
    }

    //从网络获取经纬度
    public List<String> getLngAndLatWithNetwork() {
        List<String> list = new ArrayList<String>();
        double latitude = 0.0;
        double longitude = 0.0;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();

                list.add(String.valueOf(latitude));
                list.add(String.valueOf(longitude));
            }
        }
        return list;
    }

    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };
    //关闭时解除监听器
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //api23需要这样写
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (locationManager != null) {
                locationManager.removeUpdates(locationListener);
            }
        }
    }

//    private void getlocationGps() {
//        //此处的判定是主要问题，API23之后需要先判断之后才能调用locationManager中的方法
//
//        //包括这里的getLastKnewnLocation方法和requestLocationUpdates方法
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            //获取定位服务
//            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            //获取当前可用的位置控制器
//            List<String> list = locationManager.getProviders(true);
//
//            if (list.contains(LocationManager.GPS_PROVIDER)) {
//                //是否为GPS位置控制器
//                provider = LocationManager.GPS_PROVIDER;
//            } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
//                //是否为网络位置控制器
//                provider = LocationManager.NETWORK_PROVIDER;
//
//            } else {
//                Toast.makeText(this, "请检查网络或GPS是否打开",
//                        Toast.LENGTH_LONG).show();
//                return;
//            }
//            Location location = locationManager.getLastKnownLocation(provider);
//            System.out.println("location=== "+location);
//            if (location != null) {
//                //获取当前位置，这里只用到了经纬度
//                String string = "纬度为：" + location.getLatitude() + ",经度为："
//                        + location.getLongitude();
//                Log.i("location info=== ", string);
//            }
//            //绑定定位事件，监听位置是否改变
//            //第一个参数为控制器类型第二个参数为监听位置变化的时间间隔（单位：毫秒）
//            //第三个参数为位置变化的间隔（单位：米）第四个参数为位置监听器
//            locationManager.requestLocationUpdates(provider, 2000, 2, locationListener);
//        }
//    }

}
