package com.example.lenovo.touchview;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    RecyclerViewAdapter mAdapter;
    List<String> data;

    //定义浮动窗口布局
   // RelativeLayout mFloatLayout;
    TouchView view;
    WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    WindowManager mWindowManager;

    PopupWindow mPopupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // initData();
       // initView();
       // initPopuptWindow();

        initData();
        initView();
       // initPopuptWindow();

    }

    private void initView(){

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mAdapter=new RecyclerViewAdapter(this,data);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState==RecyclerView.SCROLL_STATE_IDLE){

                    Log.e("view","top "+newState);
                    //mWindowManager.removeView(view);

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("view", "scroll " + mRecyclerView.getScrollState() + " " + dy + " " + dx);
                if (mLinearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    Log.e("view", "scroll " + mRecyclerView.getScrollState() + " " + dy + " " + dx);
                    createFloatView();

                }
            }
        });

    }

    private void initData(){
        data=new ArrayList<String>();
        for(int i=0;i<100;i++){
            data.add("test data");
        }
    }

    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        //获取的是WindowManagerImpl.CompatModeWrapper
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        Log.i("window", "mWindowManager--->" + mWindowManager);
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        int []location=new int[2];
        mRecyclerView.getLocationInWindow(location);
        wmParams.x = location[0];
        wmParams.y = location[1]-72;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        wmParams.height =400;

         /*// 设置悬浮窗口长宽数据
        wmParams.width = 200;
        wmParams.height = 80;*/

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局
       // mFloatLayout = (RelativeLayout) inflater.inflate(R.layout.layout, null);
        view=new TouchView(this);
        //添加mFloatLayout
        mWindowManager.addView(view, wmParams);
    }


}
