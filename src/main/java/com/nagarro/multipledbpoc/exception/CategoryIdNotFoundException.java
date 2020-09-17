package com.nagarro.multipledbpoc.exception;

public class CategoryIdNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoryIdNotFoundException(String categoryName) {
		super("Category Id for " + categoryName + " could not be fetched.Check if city exists in database\n");
	}

}
