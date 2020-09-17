package com.nagarro.multipledbpoc.exception;

public class CityIdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CityIdNotFoundException(String cityName) {
		super("City Id for " + cityName + " could not be fetched.Check if city exists in database\n");
	}

}
