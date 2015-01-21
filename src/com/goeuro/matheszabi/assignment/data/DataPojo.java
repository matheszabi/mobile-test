package com.goeuro.matheszabi.assignment.data;

/**
 * Represents a data from the webservice call listDataUsed at http://api.goeuro.com/api/v2/position/suggest/{locale}/{term}
 * 
 * Field names as the JSON keys, doesn't follow the Java naming conventions here
 * It could be used for auto parsing with reflection.  I think I will be to lazy to do it :)
 * @author matheszabi
 * @deprecated It has a to much useless fields, what the webservice is sending, but the app doesn't use.
 */
@Deprecated
public class DataPojo {
	private int _id;
	private String key;
	private String name;
	private String fullName;
	private String iata_airport_code;
	private String type;
	private String country;
	//geo_position: - now its broken the auto-parse with reflection
	private float latitude;
	private float longitude;
	private String locationId;
	private boolean inEurope;
	private String countryCode;
	private boolean coreCountry;
	private Float distance;
}
