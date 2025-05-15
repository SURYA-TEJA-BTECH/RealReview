package com.surya.user;

public class UniqueEmailException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UniqueEmailException(String message) {
		super(message);
	}

}
