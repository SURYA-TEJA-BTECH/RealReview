package com.surya.image;

public class ImageMetaDataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageMetaDataNotFoundException(String mess) {

		super(mess);
	}

	public static String getMessage(Integer metaDataId) {

		return "given metaId not found";
	}
}
