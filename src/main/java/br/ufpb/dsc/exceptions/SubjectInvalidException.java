package br.ufpb.dsc.exceptions;

public class SubjectInvalidException extends RuntimeException{
	private String title;
	private String message;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7150945774579375377L;
	
	public SubjectInvalidException(String title, String message) {
		super();
		this.message = message;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
