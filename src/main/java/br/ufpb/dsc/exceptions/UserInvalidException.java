package br.ufpb.dsc.exceptions;

public class UserInvalidException extends IllegalArgumentException {

    private String message;

    public UserInvalidException(String msg){
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
