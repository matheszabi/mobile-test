package com.matheszabi.util;

import java.net.URI;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.os.AsyncTask;

public class HostAvailabilityTask extends AsyncTask<String, Void, Boolean> {

	@SuppressWarnings("unused")
	private static final String TAG = "HostAvailabilityTask";

	public static interface Callback
	{
		void continueAfterCheck(boolean hostReached);
	}
	
	private Callback mCallback;
	
	public HostAvailabilityTask(Callback callback){
		mCallback = callback;
	}

    protected Boolean doInBackground(String... params) {
    	HttpParams httpParameters = new BasicHttpParams();
    	HttpConnectionParams.setConnectionTimeout(httpParameters, 2000);
    	HttpConnectionParams.setSoTimeout(httpParameters, 2000);

    	HttpClient httpclient = new DefaultHttpClient(httpParameters);
    	
        HttpGet request = new HttpGet();
        try {
			URI website = new URI(params[0]);//URISyntaxException, NullPointerException, IndexOverflowException
			request.setURI(website);
			httpclient.execute(request);
		} 
        catch (Exception e) {//ConnectException should be
        	return false;
			//Log.e(TAG, "", e);
		}
    	
        return true;       
    }

    protected void onPostExecute(Boolean result) {		
        if(mCallback != null){
        	mCallback.continueAfterCheck(result);
        }
    }   
}
