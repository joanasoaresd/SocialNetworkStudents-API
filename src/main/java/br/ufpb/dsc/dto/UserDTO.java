package br.ufpb.dsc.dto;

import br.ufpb.dsc.exceptions.UserInvalidException;
import lombok.Data;

@Data
public class UserDTO {

	private int id;
    private String name; 
	private String email; 

    public UserDTO(){}

    public boolean checkUser() {
		if(this.name == null || this.name.isBlank() || this.name.isEmpty()) {
			throw new UserInvalidException("Nome inválido");
		} if(this.email == null || this.email.isBlank() || this.email.isEmpty()) {
			throw new UserInvalidException("Email inválido");
		}
        
		return true;
	}
}
