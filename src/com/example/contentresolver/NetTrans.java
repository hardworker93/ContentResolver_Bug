package com.example.contentresolver;

public class NetTrans {
	
	public static final String STATUS_FAILED_IO_EXCEPTION  = "io_exception";
	public static final String STATUS_FAILED_TIME_OUT      = "time_out";
	public static final String STATUS_LOGIC_FAILED         = "logic_error";
	public static final String STATUS_PENDING              = "pending";
	public static final String STATUS_OK                   = "ok";
	
	public static final String GET                         = "get";
	public static final String POST                        = "post";
	public static final String PUT                         = "put";
	public static final String DELETE                      = "delete";
	
	
	private long mId;  
	private String mStatus;
	private String mUrl;
	private String mParam;
	private String mMethodType;
	private String mResult;
	private String mMessage;
	private long mInitiateTime;
	
	public NetTrans(){
	}
	

	public NetTrans(long id,String status,String url,String param,String methodType){
		mId = id;
		mStatus = status;
		mUrl = url;
		mParam = param;
		mInitiateTime = System.currentTimeMillis();
		mMethodType = methodType;
	}


	public long getId() {
		return mId;
	}


	public void setId(long id) {
		mId = id;
	}


	public String getStatus() {
		return mStatus;
	}


	public void setStatus(String status) {
		mStatus = status;
	}

	public String getUrl() {
		return mUrl;
	}


	public void setUrl(String url) {
		mUrl = url;
	}


	public String getParam() {
		return mParam;
	}


	public void setParam(String param) {
		mParam = param;
	}


	public String getMethodType() {
		return mMethodType;
	}


	public void setMethodType(String methodType) {
		mMethodType = methodType;
	}


	public long getInitiateTime() {
		return mInitiateTime;
	}


	public void setInitiateTime(long initiateTime) {
		mInitiateTime = initiateTime;
	}


	public String getResult() {
		return mResult;
	}


	public void setResult(String result) {
		mResult = result;
	}


	public  String getMessage() {
		return mMessage;
	}


	public  void setMessage(String message) {
		mMessage = message;
	}
	
	
	
	
	
}
