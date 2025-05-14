package com.surya.user;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {

		super(message);

	}

	private static final long serialVersionUID = 1L;

	public static String getMessage(Integer userId) {

		return "user not found with given id" + userId;
	}

}
