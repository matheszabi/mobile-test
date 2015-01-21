package com.goeuro.matheszabi.assignment.data;

import java.util.Comparator;

import com.goeuro.matheszabi.assignment.exception.ToMuchBeerConsumedException;

import android.location.Location;
import android.location.LocationManager;

public class DataUsedComparator implements Comparator<DataUsed>{

	private Location myLocation;
	
	/**
	 * 
	 * @param myLocation can't be null
	 */
	public DataUsedComparator(Location myLocation) {
		if(myLocation == null){
			throw new ToMuchBeerConsumedException("myLocation is null");
		}
		this.myLocation = myLocation;
	}

	/**
	 * @return an integer < 0 if lhs is less than rhs, 0 if they are equal, and > 0 if lhs is greater than rhs.
	 */
	@Override
	public int compare(DataUsed lhs, DataUsed rhs) {
		//myLocation is variable. Do not re use the distance value always.
		
		Location leftLocation = new Location(LocationManager.NETWORK_PROVIDER);
		leftLocation.setLongitude(lhs.getLongitude());
		leftLocation.setLatitude(lhs.getLatitude());
		
		float leftDistanceMeters = myLocation.distanceTo(leftLocation);		
		lhs.setDistance(leftDistanceMeters);

		Location rightLocation = new Location(LocationManager.NETWORK_PROVIDER);
		rightLocation.setLongitude(rhs.getLongitude());
		rightLocation.setLatitude(rhs.getLatitude());
		
		float rightDistanceMeters = myLocation.distanceTo(rightLocation);
		rhs.setDistance(rightDistanceMeters);
		
		return (int) (leftDistanceMeters - rightDistanceMeters);
	}

}
