package br.ufpb.dsc.exceptions;

public class UserNotLoggedException extends RuntimeException {

    private String message;

    public UserNotLoggedException(String msg){
        super();
        this.message = msg;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
