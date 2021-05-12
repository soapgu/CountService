package com.soapgu.countservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.orhanobut.logger.Logger;
import com.soapgu.core.Broadcasts;
import com.soapgu.core.ICounter;
import com.soapgu.core.Intents;
import com.soapgu.countservice.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ICounter iService;
    private  MainViewModel viewModel;
    private BroadcastReceiver broadcastReceiver;
    private final ServiceConnection conn = new ServiceConnection(){
        //当服务被成功绑定的时候调用的方法.
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {//第二个参数就是服务中的onBind方法的返回值
            Logger.i( "-----onServiceConnected----" );
            iService = (ICounter) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Logger.w( "-----onServiceDisconnected----" );
            //add commit
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(MainViewModel.class);
        ActivityMainBinding binding  = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setDataContext( viewModel);
        binding.buttonGetCount.setOnClickListener( v -> viewModel.setMessage( String.format( "Count:%s",iService.getCount() ) ));
    }

    @Override
    protected void onResume() {
        setupBroadcast();
        super.onResume();
    }

    @Override
    protected void onStart() {
        Intent intent = new Intent();
        intent.setPackage( this.getPackageName() );
        intent.setAction(Intents.ACTION);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(broadcastReceiver);
        unbindService(conn);
        iService = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupBroadcast() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Broadcasts.First);
        registerReceiver((broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Logger.i("onReceive Broadcast>>>>>>");
                viewModel.setMessage( String.format( "Count:%s",iService.getCount() ) );
            }
        }), filter);
    }
}