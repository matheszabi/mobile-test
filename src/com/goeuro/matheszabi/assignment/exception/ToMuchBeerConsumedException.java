package com.goeuro.matheszabi.assignment.exception;

/**
 * Just an alias :) 
 * @author matheszabi
 *
 */
@SuppressWarnings("serial")
public class ToMuchBeerConsumedException extends IllegalStateException{

	public ToMuchBeerConsumedException(String string) {
		super(string);
	}

}
