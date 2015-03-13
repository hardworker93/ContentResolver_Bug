package com.example.contentresolver;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements 
LoaderManager.LoaderCallbacks<Cursor>{

	public static final String   LOG_TAG                 = "MainActivity";
	public static final boolean  DEBUGE                  = true;

	public static final String   PACKAGE                 = " com.example.contentresolver";
  

	public static final String EXTRA_TRANSACTION_ID      = PACKAGE + ".transaction.id";
	

	private final int  NET_TRANS_LOADER_ID  = 0;


	Button mStart;
	TextView mText;
	Long mTransactionId;
	
	Bundle mBundle;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mText = (TextView)findViewById(R.id.result);
		mText.setText("Nothing yet!");
		mStart = (Button)findViewById(R.id.start);
		mStart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mTransactionId = System.currentTimeMillis();
				Intent intent = new Intent(MainActivity.this,RestService.class);
				intent.putExtra(EXTRA_TRANSACTION_ID, mTransactionId);
				startService(intent);
				
			}
		});
		
		if(savedInstanceState != null){
			mTransactionId = savedInstanceState.getLong(EXTRA_TRANSACTION_ID);
		}else{
			mTransactionId = -1L;
		}
		
		
	}
	@Override
	protected void onPostResume() {
		super.onPostResume();
		mBundle = new Bundle();
		mBundle.putLong(EXTRA_TRANSACTION_ID, mTransactionId);
		getSupportLoaderManager().restartLoader(NET_TRANS_LOADER_ID,mBundle,this).forceLoad();
		
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
		Uri uri = null;
		CursorLoader cl=null;
		switch (id) {
		case NET_TRANS_LOADER_ID:
			uri = NetTransContentProvider.CONTENT_URI_FIND_BY_ID;
			cl  = new CursorLoader(MainActivity.this, uri, NetTransDbUtils.allColumns,
					NetTransDbUtils.COLUMN_ID + " = ? ",
					new String[]{String.valueOf(bundle.getLong(EXTRA_TRANSACTION_ID,-1))}, null);
			break;
		default:
			break;
		}
		return cl;
	}

	

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		final int loaderId = loader.getId();
		switch (loaderId) {
		case NET_TRANS_LOADER_ID:
			final NetTrans netTrans = NetTransDbUtils.cursorToNetTrans(cursor);
			if(netTrans != null && !TextUtils.isEmpty(netTrans.getStatus())){
				mText.setText(netTrans.getStatus());  
				
			}
			break;

		default:
			break;
		}
	}
	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		Log.i(LOG_TAG,"Reset of loader");
	}
}
