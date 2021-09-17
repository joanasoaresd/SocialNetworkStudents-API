package br.ufpb.dsc.exceptions;

public class SubjectExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String message;

	public SubjectExistsException(String title, String message) {
		super();
		this.message = message;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

}
