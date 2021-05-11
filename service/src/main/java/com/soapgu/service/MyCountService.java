package com.soapgu.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.orhanobut.logger.Logger;
import com.soapgu.core.ICounter;

public class MyCountService extends Service {
    public MyCountService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.i( "------MyCountService onBind-------" );
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        Logger.i("-------MyCountService onCreate-------");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i("-------MyCountService onStartCommand-------");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Logger.i("-------MyCountService onDestroy-------");
        super.onDestroy();
    }

    private class MyBinder extends Binder implements ICounter {

        @Override
        public int getCount() {
            return 10;
        }
    }
}