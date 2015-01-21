package com.goeuro.matheszabi.assignment.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.goeuro.matheszabi.assignment.data.DataUsed;
import com.goeuro.matheszabi.assignment.exception.ToMuchBeerConsumedException;

/**
 * Call the webservice http://api.goeuro.com/api/v2/position/suggest/{locale}/{term}
 * 
 * Params given in constructor Results, Status are stored in instance variables.
 * 
 * Do not reuse the object created
 * 
 * @author matheszabi
 * 
 */
public class WebserviceCallTask extends AsyncTask<Void, Void, Void> {

	protected String countryCode;
	protected String term;

	protected ArrayList<DataUsed> listDataUsed;

	protected TaskDoneListener taskDoneListener;

	public WebserviceCallTask(String countryCode, String term) {
		if (countryCode == null || countryCode.length() != 2) {
			throw new ToMuchBeerConsumedException("You have missed setting up the countryCode param corectly! Stop programming and continue drinking!");
		}
		if (term == null || term.length() < 2) {
			throw new ToMuchBeerConsumedException(
					"You have missed setting up the term param corectly! Min 2 character needed. Stop programming and continue drinking!");
		}
		this.countryCode = countryCode;
		this.term = term;
	}

	@Override
	protected Void doInBackground(Void... params) {
		// need to escape the term... at least UTF-8 it isn't good
		

		@SuppressWarnings("deprecation")
		String url = "http://api.goeuro.com/api/v2/position/suggest/" + countryCode + "/" + URLEncoder.encode(term);

		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			response = httpclient.execute(new HttpGet(url));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				responseString = out.toString();
				out.close();
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {
			// TODO Handle problems..
		} catch (IOException e) {
			// TODO Handle problems..
		}

		if (responseString != null) {// it should be a JSONArray - it can be an empty array
			try {
				JSONArray jsonArray = new JSONArray(responseString);

				if (jsonArray != null && jsonArray.length() > 0) {
					// we have something in this response:

					listDataUsed = new ArrayList<DataUsed>(jsonArray.length());
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonData = (JSONObject) jsonArray.get(i);
						DataUsed dataUsed = DataUsed.fromJSON(jsonData);
						listDataUsed.add(dataUsed);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// do the storage writes, if you want
		if (taskDoneListener != null) {
			taskDoneListener.taskDone(this);
		}
		return null;
	}

	public static interface TaskDoneListener {
		// it is called on the background thread - should be done the persistent read-writes here
		void taskDone(WebserviceCallTask task);
	}

	public TaskDoneListener getTaskDoneListener() {
		return taskDoneListener;
	}

	/**
	 * Use the setter, after WebserviceCallTask Constructor, if you don't want to override the class
	 * 
	 * @param taskDoneListener
	 */
	public void setTaskDoneListener(TaskDoneListener taskDoneListener) {
		this.taskDoneListener = taskDoneListener;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getTerm() {
		return term;
	}

	public ArrayList<DataUsed> getResult() {
		return listDataUsed;
	}

}
