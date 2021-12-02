package br.ufpb.dsc.dto;

import br.ufpb.dsc.entities.User;
import br.ufpb.dsc.exceptions.UserInvalidException;
import lombok.Data;

@Data
public class UserDTO {

	private int id;
    private String name; 
	private String email; 

    public UserDTO(User u){
		this.id = u.getId();
		this.name = u.getName();
		this.email = u.getEmail();
	}

    public boolean checkUser() {
		if(this.name == null || this.name.isBlank() || this.name.isEmpty()) {
			throw new UserInvalidException("Nome inválido");
		} if(this.email == null || this.email.isBlank() || this.email.isEmpty()) {
			throw new UserInvalidException("Email inválido");
		}
        
		return true;
	}
}
