package com.soapgu.countservice;

import android.app.Application;
import android.content.Intent;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.soapgu.core.Intents;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)
                .tag("SoapAPP")
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        Logger.i("-------APP Create-------");

        Intent intent = new Intent();
        intent.setPackage( this.getPackageName() );
        intent.setAction( Intents.ACTION );
        this.startService(intent);
        Logger.i("-------Start MyCountService-------");
    }
}
