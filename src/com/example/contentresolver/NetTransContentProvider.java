package com.example.contentresolver;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class NetTransContentProvider extends ContentProvider{



	public static final String PACKAGE    = "com.example.contentresolver";
	public static final String TABLE_NAME = "NetworkTransaction";

	public static final String AUTHORITY  = PACKAGE + ".NetTransContentProvider";

	public static final Uri BASE_URI = Uri.parse("content://"+AUTHORITY);
 
	public static final Uri CONTENT_URI_ANY_OBSERVER           
	                                      = Uri.withAppendedPath(BASE_URI,TABLE_NAME);
	public static final Uri CONTENT_URI_FIND_BY_ID           
	                                      = Uri.withAppendedPath(BASE_URI,TABLE_NAME+"/FIND/ID");
	public static final Uri CONTENT_URI_INSERT_OR_REPLACE_BY_ID       
	                                      = Uri.withAppendedPath(BASE_URI,TABLE_NAME+"/INSERT/REPLACE/ID"); 
	public static final Uri CONTENT_URI_INSERT_BY_ID       
                                          = Uri.withAppendedPath(BASE_URI,TABLE_NAME+"/INSERT/ID"); 

	private SQLiteDatabase mDatabase;

	private static final int FIND_BY_ID                = 0;
	private static final int INSERT_OR_REPLACE_BY_ID   = 1;
	private static final int INSERT_BY_ID              = 2;

	private static final UriMatcher sUriMatcher ;
	static {
		sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
		
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME+"/FIND/ID", FIND_BY_ID);
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME+"/INSERT/REPLACE/ID", INSERT_OR_REPLACE_BY_ID);
		sUriMatcher.addURI(AUTHORITY, TABLE_NAME+"/INSERT/ID",INSERT_BY_ID);
		
	}


	@Override
	public boolean onCreate() {
		mDatabase = SingletonDatabase.getInstance(getContext().getApplicationContext()).getWritableDatabase();
		return true;
	}

    
	@Override
	public int delete(Uri uri, String selection, String[] arg2) {
		mDatabase.delete(TABLE_NAME, selection,arg2);
		
		getContext().getContentResolver().notifyChange(CONTENT_URI_ANY_OBSERVER, null);
		return 0;
	}

	@Override
	public String getType(Uri arg0) {
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
		switch (uriType) {
		
		case INSERT_OR_REPLACE_BY_ID:
			mDatabase.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
			break;
			
		case INSERT_BY_ID:
			mDatabase.insert(TABLE_NAME, null, values);
			break;
			
		default:
			break;
		}

		getContext().getContentResolver().notifyChange(CONTENT_URI_ANY_OBSERVER, null);
	
		return null;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,String sortOrder) {
		Cursor cursor = mDatabase.query(TABLE_NAME,projection,selection, selectionArgs, null, null, null);
		cursor.setNotificationUri(getContext().getContentResolver(), CONTENT_URI_ANY_OBSERVER);
		return cursor;
	}

	@Override
	public int update(Uri uri, ContentValues arg1, String arg2, String[] arg3) {
		getContext().getContentResolver().notifyChange(CONTENT_URI_ANY_OBSERVER, null);
		return 0;
	}


}
