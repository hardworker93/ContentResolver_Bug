package com.example.contentresolver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.os.IBinder;

public class RestService extends Service{

	ExecutorService mExecutor = null;
	private Map<Long,Boolean> mQueue;	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		
		    final Long transId = intent.getLongExtra(MainActivity.EXTRA_TRANSACTION_ID,-1);
			Runnable task  = new Runnable() {
				
				@SuppressWarnings("static-access")
				@Override
				public void run() {
					
					NetTrans netTrans = new NetTrans(transId,NetTrans.STATUS_PENDING,"url","param",NetTrans.POST);
					ContentValues cv = NetTransDbUtils.netTransToCV(netTrans);
					getContentResolver().insert(NetTransContentProvider.CONTENT_URI_INSERT_BY_ID, cv );
					try {
						Thread.currentThread().sleep(5000);
					} catch (InterruptedException e) {
					}
					
					netTrans.setStatus(NetTrans.STATUS_OK);
					cv = NetTransDbUtils.netTransToCV(netTrans);
					getContentResolver().insert(NetTransContentProvider.CONTENT_URI_INSERT_OR_REPLACE_BY_ID, cv );
					EventBus.getDefault().post(new NetTransMsg(true,transId, "stop yourself"));
				}
			};
			mExecutor.execute(task);
		

		
		
		
		
		return START_REDELIVER_INTENT;
	}
	
	@SuppressLint("UseSparseArrays")
	@Override
	public void onCreate() {
		super.onCreate();
		mExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		mQueue = new HashMap<Long, Boolean>();
		EventBus.getDefault().register(this);
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}


	@Override
	public void onDestroy() {
		mExecutor.shutdown();
		EventBus.getDefault().unregister(this);
		super.onDestroy();
	}

	public void onEventMainThread(Object event) {
		NetTransMsg netTransResult  =  (NetTransMsg)event;		
		mQueue.remove(netTransResult.getTransId());
		if(mQueue.isEmpty()){
			stopSelf();
		}
	}
	
	

}
