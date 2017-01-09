package com.example.wangkuan.fuwuyinyuebofang;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FuWu.ZhongJian mFw;
    ServiceConnection s = new ServiceConnection() {


        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            //连接上以后，找到传回来的中间这
            mFw = (FuWu.ZhongJian) iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private ArrayList<File> mSdk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.a1);

        // 开启服务
        Intent service = new Intent(this, FuWu.class);
        startService(service);
        //捆绑服务
        bindService(service, s, Service.BIND_AUTO_CREATE);

        mSdk = SaoMiao.Sdk();
        Log.i("TAG", mSdk.get(1).getPath() + "++++++++++++++++++++++");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Log.i("TAG", musicPath.get(1).getPath() + "++++++++++++++++++++++");
                //     ms.qidong(musicPath.get(1).getPath());
                //点击启动
                mFw.qidong(mSdk.get(1).getPath());
            }
        });
    }
}
