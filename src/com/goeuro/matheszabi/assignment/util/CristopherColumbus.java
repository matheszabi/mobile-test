package com.goeuro.matheszabi.assignment.util;

import com.goeuro.matheszabi.assignment.FunnyActivity;
import com.matheszabi.util.GeolocationGetter;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class CristopherColumbus {

	private FunnyActivity funnyActivity;

	// used to sort the matching results

	public void findWhereAmI(FunnyActivity funnyActivity) {
		this.funnyActivity = funnyActivity;

		LocationManager locationManager = (LocationManager) funnyActivity.getSystemService(Context.LOCATION_SERVICE);

		if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

			// Define a listener that responds to location updates
			FilipaMonizPerestrelo wife = new FilipaMonizPerestrelo();

			// Register the listener with the Location Manager to receive location updates
			locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, wife);
		} else { // it is not enabled the location

			Log.d("FunnyActivity", "Not enabled the location, this sucks");

			// get the location by IP, but first get the IP

			// to boring to implement it... but for a drunk user better not ask to turn on the location settings, maybe he doesn't know how to do it on that
			// state...
		}
	}

	private void discoveredAmericaLocation(Location location) {

		// lets find what is the name:
		GeolocationGetter geolocationGetter = new GeolocationGetter(location) {
			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				funnyActivity.setMyLocationString(countryCode, city);
			}
		};
		geolocationGetter.execute();
	}

	// FYI: wife of CristopherColumbus http://en.wikipedia.org/wiki/Filipa_Moniz_Perestrelo
	private class FilipaMonizPerestrelo implements LocationListener {
		// http://developer.android.com/guide/topics/location/strategies.html

		public void onLocationChanged(Location location) {
			// Called when a new location is found by the network location provider.
			// Log.d("LocationListener", "onLocationChanged() location:" + location);

			funnyActivity.setMyLocation(location);

			LocationManager locationManager = (LocationManager) funnyActivity.getSystemService(Context.LOCATION_SERVICE);
			locationManager.removeUpdates(this);

			discoveredAmericaLocation(location);
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

		public void onProviderEnabled(String provider) {
		}

		public void onProviderDisabled(String provider) {
		}
	}
}
