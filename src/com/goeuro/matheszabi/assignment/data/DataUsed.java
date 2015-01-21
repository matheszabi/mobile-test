package com.goeuro.matheszabi.assignment.data;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class DataUsed implements Serializable{


	private static final long serialVersionUID = 8495263703586417298L;

	private String name;
	private String country;
	private float latitude;
	private float longitude;
	private String countryCode;// Maybe it is not on the same country what was the input

	// "When displaying matches, they should be ordered by distance to the user's current location."
	// the webservice NOW it is send this value as null, so I need to calculate it at runtime. And do not save to persistent layer this listDataUsed.
	private transient Float distance = Float.MAX_VALUE;// marked transied for better visual: do not serialize it

	public static DataUsed fromJSON(JSONObject json) throws JSONException{
		if(json == null){
			return null;
		}
		
		DataUsed result = new DataUsed();
		
		result.name = json.getString("name");		
		result.country = json.getString("country");
		JSONObject geoPosition = json.getJSONObject("geo_position");
		result.latitude = Float.parseFloat(geoPosition.getString("latitude"));
		result.longitude = Float.parseFloat(geoPosition.getString("longitude"));
		result.countryCode = json.getString("countryCode");		
		
		return result;
	}

	public DataUsed() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}
	
	
}
