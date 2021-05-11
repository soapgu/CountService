package com.soapgu.countservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.soapgu.core.ICounter;
import com.soapgu.core.Intents;

public class MainActivity extends AppCompatActivity {

    private ICounter iService;
    private Intent intent;
    private Myconn conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent();
        intent.setPackage( this.getPackageName() );
        intent.setAction(Intents.ACTION);
        setContentView(R.layout.activity_main);
        conn = new Myconn();
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }

    private class Myconn implements ServiceConnection {
        //当服务被成功绑定的时候调用的方法.
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {//第二个参数就是服务中的onBind方法的返回值
            iService = (ICounter) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    }
}