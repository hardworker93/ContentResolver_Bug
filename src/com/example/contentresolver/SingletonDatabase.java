package com.example.contentresolver;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SingletonDatabase extends SQLiteOpenHelper { 

	private static SingletonDatabase sInstance;

	private static final String DATABASE_NAME = "mydb.db";
	private static final int DATABASE_VERSION = 1;
	

	public static SingletonDatabase getInstance(Context context) {

		if (sInstance == null) {
			sInstance = new SingletonDatabase(context.getApplicationContext());
		}
		return sInstance;
	}

	private SingletonDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		try {
			db.beginTransaction();
			db.execSQL(NetTransDbUtils.TABLE_CREATE);
			db.setTransactionSuccessful();
		}
		finally{
			db.endTransaction();
		}


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		onCreate(db);

	}

	public void close(){
		this.close();
	}


}
