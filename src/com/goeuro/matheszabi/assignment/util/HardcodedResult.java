package com.goeuro.matheszabi.assignment.util;

/**
 * Use this for testing, when you don't want to call they webservice or the webservice is down.
 * http://api.goeuro.com/api/v2/position/suggest/{locale}/{term}
 * 
 * @author matheszabi
 *
 */
public class HardcodedResult {
	
	// the first 2 letter from München ( Oktoberfest location) 
	public static final String URL1 = "http://api.goeuro.com/api/v2/position/suggest/de/mu";
	

	public static final String JSON1 = "[{\"_id\":376946,\"key\":null,\"name\":\"M\u00FCnchen\",\"fullName\":\"M\u00FCnchen, Deutschland\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":48.13743,\"longitude\":11.57549},\"locationId\":9120,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":378700,\"key\":null,\"name\":\"Murcia\",\"fullName\":\"Murcia, Spanien\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Spanien\",\"geo_position\":{\"latitude\":37.98704,\"longitude\":-1.13004},\"locationId\":10885,\"inEurope\":true,\"countryCode\":\"ES\",\"coreCountry\":true,\"distance\":null},{\"_id\":464076,\"key\":null,\"name\":\"M\u00FCnster\",\"fullName\":\"M\u00FCnster, Deutschland\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":51.96236,\"longitude\":7.62571},\"locationId\":165485,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":376943,\"key\":null,\"name\":\"M\u00FClheim (Ruhr)\",\"fullName\":\"M\u00FClheim (Ruhr), Deutschland\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":51.43333,\"longitude\":6.88333},\"locationId\":9117,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":313870,\"key\":null,\"name\":\"M\u00FCnchen\",\"fullName\":\"M\u00FCnchen (MUC), Deutschland\",\"iata_airport_code\":\"MUC\",\"type\":\"airport\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":48.354597,\"longitude\":11.785065},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}]";

	

	// the first 3 letter from Berlin 
	public static final String URL2 = "http://api.goeuro.com/api/v2/position/suggest/de/berl";
	
	public static final String JSON2 = "[{\"_id\":376217,\"key\":null,\"name\":\"Berlin\",\"fullName\":\"Berlin, Deutschland\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":52.52437,\"longitude\":13.41053},\"locationId\":8384,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":369197,\"key\":null,\"name\":\"Berlare\",\"fullName\":\"Berlare, Belgien\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Belgien\",\"geo_position\":{\"latitude\":51.03333,\"longitude\":4.0},\"locationId\":1356,\"inEurope\":true,\"countryCode\":\"BE\",\"coreCountry\":true,\"distance\":null},{\"_id\":369196,\"key\":null,\"name\":\"Berlaar\",\"fullName\":\"Berlaar, Belgien\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Belgien\",\"geo_position\":{\"latitude\":51.1176,\"longitude\":4.65835},\"locationId\":1355,\"inEurope\":true,\"countryCode\":\"BE\",\"coreCountry\":true,\"distance\":null},{\"_id\":439828,\"key\":null,\"name\":\"Berlaimont\",\"fullName\":\"Berlaimont, Frankreich\",\"iata_airport_code\":null,\"type\":\"location\",\"country\":\"Frankreich\",\"geo_position\":{\"latitude\":50.20155,\"longitude\":3.81343},\"locationId\":139312,\"inEurope\":true,\"countryCode\":\"FR\",\"coreCountry\":false,\"distance\":null},{\"_id\":314826,\"key\":null,\"name\":\"Berlin Tegel\",\"fullName\":\"Berlin Tegel (TXL), Deutschland\",\"iata_airport_code\":\"TXL\",\"type\":\"airport\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":52.5548,\"longitude\":13.28903},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null},{\"_id\":314827,\"key\":null,\"name\":\"Berlin Sch\u00F6nefeld\",\"fullName\":\"Berlin Sch\u00F6nefeld (SXF), Deutschland\",\"iata_airport_code\":\"SXF\",\"type\":\"airport\",\"country\":\"Deutschland\",\"geo_position\":{\"latitude\":52.3887261,\"longitude\":13.5180874},\"locationId\":null,\"inEurope\":true,\"countryCode\":\"DE\",\"coreCountry\":true,\"distance\":null}]";	
}