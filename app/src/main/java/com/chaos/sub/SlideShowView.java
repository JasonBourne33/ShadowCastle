package com.chaos.sub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.multiplestatusviewtest.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SlideShowView extends FrameLayout {

    //自动轮播的时间间隔
    private final static int TIME_INTERVAL = 5;
    //自动轮播启用开关
    private final static boolean isAutoPlay = true;
	protected static final int TaskId_Loop = 1200;
	public static final int TaskId_PicOk = 1201;
	protected static final int LoadPicUrl = 0; 

    private List<ImageView> imageViewsList;
    private List<View> dotViewsList = new ArrayList<View>();
    
    private ViewPager viewPager;
    
  //当前轮播页
    private int currentItem  = 0;   

    private List<String> names;

    //定时任务
    private ScheduledExecutorService scheduledExecutorService=null;
    
    private Context context;
    
    //private JsonResult result;
    
    private String ui_contro_id;
         
    private Handler handler = new Handler(){
    	public void handleMessage(android.os.Message msg) {
    		super.handleMessage(msg);
//    		MyLog.i("Slide","Loop0:");
    		switch(msg.what){  			
    		case TaskId_Loop:
    			viewPager.setCurrentItem(currentItem,true);
    			break;   			
    		}
            
    	};
    };
	private TextView tv_name=null;
	private TextView tv_page;
	private MyPagerAdapter adpter;
	private LinearLayout ll_dot;
    
	
    public int getCurrentItem(){
    	return currentItem;
    }
	public SlideShowView(Context context) {
		this(context,null);
	}
	
	public SlideShowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public SlideShowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }
    
    public SlideShowView(Context context, AttributeSet attrs, int defStyle, List<ImageView> imageViewsList, List<String> names) {
        super(context, attrs, defStyle);
        this.context = context;
        this.imageViewsList = imageViewsList;
        this.names = names;
        
        initUI(context);
        viewPager.setCurrentItem(currentItem,true);  
        //if(isAutoPlay)
        	startPlay();  
    }
    
    public void initData(List<ImageView> imageViewsList, List<String> names){
    	this.imageViewsList = imageViewsList;
        this.names = names;
        
        initUI(context);
        viewPager.setCurrentItem(currentItem,true);  
        ll_dot.removeAllViews();
        dotViewsList.clear();
        for(int i=0; i<imageViewsList.size();i++){
    		View view = new View(context);
    		view.setBackgroundResource(R.drawable.dot_normal);
    		
    		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
    				24, 24); 
    		if(i!=0)
    			params.leftMargin = 14;
    		view.setLayoutParams(params);
    		ll_dot.addView(view);
    		dotViewsList.add(view);
    	}
        //if(isAutoPlay)
        	startPlay();  
    }
    
    public void adpterChanged(){
//    	MyLog.i("MyDBTask", "adpterChanged");
    	adpter.notifyDataSetChanged();  
    }
    
    public void adpterChanged2(){
//    	MyLog.i("MyDBTask", "adpterChanged");
    	adpter.notifyDataSetChanged();  
    }
    
    /**
     * 开始轮播图切换
     */
    public void startPlay(){
//    	MyLog.i("timerloop","startPlay"+this);
    	synchronized (this){
	    	if(scheduledExecutorService==null){
		        scheduledExecutorService = Executors.newScheduledThreadPool(3);
		        scheduledExecutorService.scheduleAtFixedRate(new SlideShowTask(), 0, TIME_INTERVAL, TimeUnit.SECONDS);
		        setViewPagerScrollSpeed();
//		        MyLog.i("timerloop","scheduledExecutorService"+scheduledExecutorService);
	        }
    	}
    }
    
    /**
     * 停止轮播图切换
     */
    public void stopPlay(){
    	synchronized (this){
	    	if(scheduledExecutorService!=null){
	    		if(!scheduledExecutorService.isShutdown()){
	    			scheduledExecutorService.shutdown();
	    			scheduledExecutorService = null;
	    		}
	    	}
    	}
    }
   
    /**
     * 初始化Views等UI
     */
    private void initUI(Context context){
    	
    	if(ll_dot==null){
    	LayoutInflater.from(context).inflate(R.layout.slide_view, this,true);
 		//tv_name = (TextView) findViewById(R.id.tv_name);
 		//tv_page = (TextView) findViewById(R.id.tv_page);
 	   	
 		viewPager = (ViewPager) findViewById(R.id.viewPager);
 		ll_dot = (LinearLayout) findViewById(R.id.ll_dot);
 		adpter = new MyPagerAdapter();
    	}
    	viewPager.setFocusable(true);
    	viewPager.setAdapter(adpter);
    	viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    
    public void initUIx(){
    	adpter = new MyPagerAdapter();
    	viewPager.setAdapter(adpter);
    	viewPager.setOnPageChangeListener(new MyPageChangeListener());
    }
    
    private OnPictureClickListener mOnClickListener=null;
    
    /**
	 * 设置tabitem的点击监听事件
	 */
	public void setOnPictureClickListener(OnPictureClickListener l) {
		mOnClickListener = l;
	}
    /**
	 * 点击回调接口
	 */
	public interface OnPictureClickListener {
		public void onPictureClick(int selectPosition);
	}
	
    /**
     * 填充ViewPager的页面适配器
     * @author caizhiming
     */
    private class MyPagerAdapter  extends PagerAdapter {

    	
        @SuppressLint("NewApi")
		@Override
		public int getItemPosition(Object object) {
   			// TODO Auto-generated method stub
        	//if(!names.isEmpty()&&!(names.get(0).isEmpty())){
        	//	return PagerAdapter.POSITION_NONE;
        	//}
			return super.getItemPosition(object);
		}

		@Override
        public void destroyItem(View container, int position, Object object) {
	            //((ViewPager)container).removeView(imageViewsList.get(position));
			//Warning：不要在这里调用removeView
        }

        @Override
        public Object instantiateItem(View container, int position) {
//        	MyLog.i("slide","instantiateItem:"+position);
            ImageView view = imageViewsList.get(position);
            //如果View已经在之前添加到了一个父组件，则必须先remove，否则会抛出IllegalStateException。
            ViewParent vp =view.getParent();
            if (vp!=null){
                ViewGroup parent = (ViewGroup)vp;
                parent.removeView(view);
            }
            
            final int fposition = position;
            view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if(mOnClickListener!=null){
							mOnClickListener.onPictureClick(fposition);
					}
					/*if(ui_contro_id.startsWith("TabView")){
						MyLog.i("AAA","TabView");
						MainTabActivity.expandTabView[MainTabActivity.expandTabViewIdx].onPressBackX();
					}*/
				}
			});
            ((ViewPager)container).addView(view);
            return view;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return imageViewsList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            // TODO Auto-generated method stub
            return arg0 == arg1;
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
            // TODO Auto-generated method stub

        }

        @Override
        public Parcelable saveState() {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void startUpdate(View arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void finishUpdate(View arg0) {
            // TODO Auto-generated method stub
            
        }
    }
    
    /**
     * ViewPager的监听器
     * 当ViewPager中页面的状态发生改变时调用
     * @author caizhiming
     */
    private class MyPageChangeListener implements OnPageChangeListener {

        boolean isAutoPlay = false;

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub
            switch (arg0) {
            case 1:// 手势滑动，空闲中
                isAutoPlay = false;
//                MyLog.i("Slide","slide idle");
                break;
            case 2:// 界面切换中
                isAutoPlay = true;
//                MyLog.i("Slide","slide ing...");
                break;
            case 0:// 滑动结束，即切换完毕或者加载完毕
                // 当前为最后一张，此时从右向左滑，则切换到第一张
                if (viewPager.getCurrentItem() == viewPager.getAdapter().getCount() - 1 && !isAutoPlay) {
                    viewPager.setCurrentItem(0,true);
                }
                // 当前为第一张，此时从左向右滑，则切换到最后一张
                else if (viewPager.getCurrentItem() == 0 && !isAutoPlay) {
                    viewPager.setCurrentItem(viewPager.getAdapter().getCount() - 1, true);
                }
                break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void onPageSelected(int pos) {
            currentItem = pos;
            /*tv_name.setText(names.get(currentItem));
            tv_page.setText("("+(currentItem+1)+"/"+imageViewsList.size()+")");*/
            
            for(int i=0;i < dotViewsList.size();i++){
                if(i == pos){
                    ((View)dotViewsList.get(pos)).setBackgroundResource(R.drawable.dot_focused);
                }else {
                    ((View)dotViewsList.get(i)).setBackgroundResource(R.drawable.dot_normal);
                }
            }
        }
    }
    
    /**
     *执行轮播图切换任务
     *@author caizhiming
     */
    private class SlideShowTask implements Runnable {

        @Override
        public void run() {
//        	MyLog.i("slide","Runnable:"+handler);
            // TODO Auto-generated method stub
            synchronized (viewPager) {
            	if(imageViewsList.size()!=0){
            		currentItem = (++currentItem)%imageViewsList.size();
            	}
                Message msg = handler.obtainMessage();
                msg.what = TaskId_Loop;
                handler.sendMessage(msg);
            }
        }
    }
    
    /**
     * 销毁ImageView资源，回收内存
     * @author caizhiming
     */
    private void destoryBitmaps() {
//    	MyLog.i("slide","destoryBitmaps");
    	
        for (int i = 0; i < imageViewsList.size(); i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                //解除drawable对view的引用
                drawable.setCallback(null);
            }
        }
 
    }
    
    private void setViewPagerScrollSpeed( ){ 
//    	MyLog.i("slide","setViewPagerScrollSpeed");
        try {  
            Field mScroller = null;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);   
            FixedSpeedScroller scroller = new FixedSpeedScroller( context );  
            mScroller.set( viewPager, scroller);  
         }catch(Exception e){
//               MyLog.i("slide","setViewPagerScrollSpeed:"+e);
         }
     }
}
