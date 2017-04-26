package jrdcom.com.jrdrecylerdemo;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemsDefine.JrdListItem;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemsDefine.JrdTitleItem;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter.JrdRecyclerAdapter;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.interfaces.OnTouchUpListener;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.layout.SWPullRecyclerLayout;

/**
 * Created by longcheng on 2017/4/26.
 */

public class JrdMainActivity extends AppCompatActivity implements OnTouchUpListener {
    private SWPullRecyclerLayout recyclerLayout;
    List<Object> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private float getScreenDes(){
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return  displayMetrics.density;
    }

    private void initData(){
        //添加虚拟数据,这里仅是测试数据
        JrdTitleItem titleItem = new JrdTitleItem();
        titleItem.itemTitle = "test";
        mData.add(titleItem);

        for(int i = 0; i < 10; i ++){
            JrdListItem listItem = new JrdListItem();
            listItem.listImageId = R.mipmap.lufy;
            listItem.listText = "List"+i;
            mData.add(listItem);
        }
    }

    private void initView(){
        recyclerLayout = (SWPullRecyclerLayout) findViewById(R.id.recycler_layout);
        View head = LayoutInflater.from(this).inflate(R.layout.layout_head,null);
        View foot = LayoutInflater.from(this).inflate(R.layout.layout_foot,null);
        int des = (int) getScreenDes();
        recyclerLayout.addHeaderView(head, (int)60*des);
        recyclerLayout.addFooterView(foot, (int)60*des);
        /* 添加Adapter*/
        initData();
        JrdRecyclerAdapter numAdapter = new JrdRecyclerAdapter(this,mData);

        recyclerLayout.setMyRecyclerView(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL), numAdapter);
        /*设置回调*/
        recyclerLayout.addOnTouchUpListener(this);
        recyclerLayout.setScrollBackAuto(false);
    }

    @Override
    public void OnRefreshing() {

    }

    private static final int MSG_SLEEP = 1;
    private static final int MSG_CHILD_TO_MAIN = 2;
    private static final int MSG_MAIN_TO_CHILD= 3;
    private Handler mThreadHandler;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case MSG_SLEEP:
                    if(false == recyclerLayout.getScrollBackAuto()) {
                        recyclerLayout.ScrollBackManual();
                    }
                    //使用子线程发送
                    mThreadHandler.sendEmptyMessage(MSG_MAIN_TO_CHILD);
                    break;
                case MSG_CHILD_TO_MAIN:
                    Log.d("TestThread", "get message from child");
                    mThreadHandler.sendEmptyMessage(MSG_MAIN_TO_CHILD);
                    break;
            }
            //super.handleMessage(msg);
        }
    };

    @Override
    public void OnLoading() {
        /*
        * 等待3秒钟， 然后手动 Scroll back
        * */
        new Thread(new Runnable() {
            @Override
            public void run() {
                //Loop循环
                Looper.prepare();
                //接收从主线层发送过来的消息
                mThreadHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what){
                            case MSG_MAIN_TO_CHILD:
                                Log.d("TestThread", "get message from main");
                                /*休息2S后发送消息给主线程*/
                                try{
                                    Thread.sleep(2000);
                                }catch (InterruptedException e){
                                    e.printStackTrace();
                                }
                                mHandler.sendEmptyMessage(MSG_CHILD_TO_MAIN);
                                break;
                        }

                    }
                };
                try{
                    Thread.sleep(3000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                /*向主线程发送消息， 执行ScrollBackManual*/
                //Message message = new Message();
                mHandler.sendEmptyMessage(MSG_SLEEP);
                Looper.loop();
            }
        }).start();


    }
}
