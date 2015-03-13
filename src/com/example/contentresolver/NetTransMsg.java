package com.example.contentresolver;

public class NetTransMsg {
    
	private boolean isSuccess;
    private long mTransId;
    private String mMessage;

    public NetTransMsg(boolean isSuccsse,Long transId,String message) {
    	isSuccess = isSuccsse;
    	mTransId = transId;
    	mMessage = message;
    }

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean success) {
		isSuccess = success;
	}
	public long getTransId() {
		return mTransId;
	}

	public void setTransId(long transId) {
		mTransId = transId;
	}

	public  String getMessage() {
		return mMessage;
	}

	public  void setMessage(String message) {
		mMessage = message;
	}
	
	
}
