package com.matheszabi.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

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

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

/**
 * http://stackoverflow.com/questions/2296377/how-to-get-city-name-from-latitude-and-longitude-coordinates-in-google-maps
 * 
 * Since the UI elements are removed can be rewriten to a Loader class Async task is still better, because it is easier to re add the loading progress
 * 
 * @author matheszabi
 * 
 */
public class GeolocationGetter extends AsyncTask<Void, Void, Void> {

	// protected and let the extended class access it
	// usually it it is used as extended, because is overriden the onPostExecute
	// or modify this class and add a taskDoneListener interface, get an instance to that and call it.
	// It is just easier to override.

	// the input param
	protected Location location;
	// the output param
	protected String city;
	protected String countryCode;

	public GeolocationGetter(Location location) {
		this.location = location;
	}

	@Override
	protected Void doInBackground(Void... arg0) {

		String jsonStr = getLocationInfo(location);
		if (jsonStr != null && jsonStr.length() > 0) {
			Log.i("location--??", jsonStr);

			JSONObject jsonObj;
			try {
				jsonObj = new JSONObject(jsonStr);

				// WTF is this? - Java object starting with Upper case letter??? Vaishali !!!
				String Status = jsonObj.getString("status");
				if (Status.equalsIgnoreCase("OK")) {
					JSONArray Results = jsonObj.getJSONArray("results");
					JSONObject zero = Results.getJSONObject(0);
					JSONArray address_components = zero.getJSONArray("address_components");

					for (int i = 0; i < address_components.length(); i++) {
						JSONObject zero2 = address_components.getJSONObject(i);
						String long_name = zero2.getString("long_name");
						JSONArray mtypes = zero2.getJSONArray("types");
						String Type = mtypes.getString(0);
						// It is not good in my case:
						// if (Type.equalsIgnoreCase("administrative_area_level_2")) {
						// // Address2 = Address2 + long_name + ", ";
						// String City = long_name;
						// Log.d(" CityName --->", City + "");
						// }

						// changed to this:
						if (Type.equalsIgnoreCase("locality")) {
							city = long_name;
							// Log.d(" CityName --->", city + "");
						}

						// and is needed the country code too for API
						if (Type.equalsIgnoreCase("country")) {
							countryCode = zero2.getString("short_name");
							// Log.d(" countryCode --->", countryCode + "");
						}
					}// /for
				}// if OK
				else {
					Log.d("GeolocationGetter", "status not OK:" + Status + "\n" + jsonStr);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

		} else {
			Log.d("GeolocationGetter", "jsonStr empty or null");
		}

		return null;
	}

	// ####
	// public static methods - write tests for that!
	// ###
	public static String getLocationInfo(Location location) {
		return getLocationInfo(location.getLatitude(), location.getLongitude());
	}

	// call it in a background thread! Need to wait like 5 min between ech call, otherwise Google will refuse to give answers
	public static String getLocationInfo(double lat, double lng) {

		HttpGet httpGet = new HttpGet("http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng + "&sensor=false");
		HttpClient client = new DefaultHttpClient();

		String result = "";

		try {
			HttpResponse response = client.execute(httpGet);

			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				result = out.toString("UTF-8");
				out.close();
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				// throw new IOException(statusLine.getReasonPhrase());
				Log.e("GeolocationGetter", "HTTP Response code: " + statusLine.getStatusCode() + " ReasonPhrase: " + statusLine.getReasonPhrase());
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();// handle it as how you want
		} catch (IOException e) {
			e.printStackTrace();// handle it as how you want
		}

		// return stringBuilder.toString();
		return result;
	}

}
