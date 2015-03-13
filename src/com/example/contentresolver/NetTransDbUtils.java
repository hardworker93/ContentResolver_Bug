package com.example.contentresolver;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;

public class NetTransDbUtils {
	
	public static final String TABLE_NAME = "NetworkTransaction";


	public static final String COLUMN_ID                = "id";
	public static final String COLUMN_STATUS            = "status";
	public static final String COLUMN_URL               = "url";
	public static final String COLUMN_PARAM             = "parameter";
	public static final String COLUMN_METHOD            = "method";
	public static final String COLUMN_INITIATE_TIME     = "initiateTime";
	public static final String COLUMN_RESULT            = "result";
	public static final String COLUMN_MESSAGE           = "message";

	public static final String[] allColumns = {
		                      COLUMN_ID,
		                      COLUMN_STATUS,
		                      COLUMN_URL,
		                      COLUMN_PARAM ,
		                      COLUMN_METHOD,
		                      COLUMN_INITIATE_TIME,
		                      COLUMN_RESULT,
		                      COLUMN_MESSAGE
	};


	public static final String TABLE_CREATE = 
			"CREATE TABLE " +" IF NOT EXISTS "+ TABLE_NAME +  " (" +
					COLUMN_ID           + " REAL PRIMARY KEY , " +
					COLUMN_STATUS       + " TEXT, " +
					COLUMN_URL          + " TEXT, " +
					COLUMN_PARAM        + " TEXT, " +
					COLUMN_METHOD       + " TEXT, " +
			        COLUMN_RESULT      + " TEXT, " +
                    COLUMN_MESSAGE     + " TEXT, " +
					COLUMN_INITIATE_TIME +" REAL " +
					")";

	public static ContentValues netTransToCV(NetTrans networkTransaction){

		ContentValues values = new ContentValues();
		values.put(COLUMN_ID, networkTransaction.getId());
		values.put(COLUMN_STATUS,networkTransaction.getStatus());
		values.put(COLUMN_URL,networkTransaction.getUrl());
		values.put(COLUMN_PARAM,networkTransaction.getParam());
		values.put(COLUMN_METHOD,networkTransaction.getMethodType());
		values.put(COLUMN_INITIATE_TIME,networkTransaction.getInitiateTime());
		values.put(COLUMN_RESULT,networkTransaction.getResult());
		values.put(COLUMN_MESSAGE,networkTransaction.getMessage());


		return values;
	}

	public static List<NetTrans> cursorToList(Cursor cursor) {

		List<NetTrans> networkTransactionList = new ArrayList<NetTrans>();	
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do{
				NetTrans networkTransaction = new NetTrans();
				networkTransaction.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
				networkTransaction.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));
				networkTransaction.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
				networkTransaction.setParam(cursor.getString(cursor.getColumnIndex(COLUMN_PARAM)));
				networkTransaction.setMethodType(cursor.getString(cursor.getColumnIndex(COLUMN_METHOD)));
				networkTransaction.setInitiateTime(cursor.getLong(cursor.getColumnIndex(COLUMN_INITIATE_TIME)));
				networkTransaction.setMessage(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE)));
				networkTransaction.setResult(cursor.getString(cursor.getColumnIndex(COLUMN_RESULT)));
				networkTransactionList.add(networkTransaction);
			}while (cursor.moveToNext()); 
		}
		cursor.close();
		return networkTransactionList;

	}


	public static NetTrans cursorToNetTrans(Cursor cursor) {

		NetTrans networkTransaction = new NetTrans();	
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do{
				networkTransaction.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
				networkTransaction.setStatus(cursor.getString(cursor.getColumnIndex(COLUMN_STATUS)));
				networkTransaction.setUrl(cursor.getString(cursor.getColumnIndex(COLUMN_URL)));
				networkTransaction.setParam(cursor.getString(cursor.getColumnIndex(COLUMN_PARAM)));
				networkTransaction.setMethodType(cursor.getString(cursor.getColumnIndex(COLUMN_METHOD)));
				networkTransaction.setInitiateTime(cursor.getLong(cursor.getColumnIndex(COLUMN_INITIATE_TIME)));
				networkTransaction.setMessage(cursor.getString(cursor.getColumnIndex(COLUMN_MESSAGE)));
				networkTransaction.setResult(cursor.getString(cursor.getColumnIndex(COLUMN_RESULT)));
				
			}while (cursor.moveToNext()); 
		}
		cursor.close();
		return networkTransaction;

	}


}
