package br.ufpb.dsc.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class User {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String name; 
	private String email; 
	private String password;

    public User(){
        // TODO Auto-generated constructor stub
    }

    public boolean isValid() {
		return !email.isBlank() && !name.isBlank() && !password.isBlank();
	}

}
