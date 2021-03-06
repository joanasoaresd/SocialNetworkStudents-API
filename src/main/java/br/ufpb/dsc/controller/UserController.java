package br.ufpb.dsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.dsc.dto.LoginResponseDTO;
import br.ufpb.dsc.dto.UserDTO;
import br.ufpb.dsc.dto.UserLoginDTO;
import br.ufpb.dsc.entities.User;
import br.ufpb.dsc.exceptions.UserExistsException;
import br.ufpb.dsc.exceptions.UserInvalidException;
import br.ufpb.dsc.service.JWTService;
import br.ufpb.dsc.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    public UserController(UserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/usuarios")
	public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
		try {
			return new ResponseEntity<UserDTO>(service.criandoUser(user), HttpStatus.CREATED);
		} catch (UserExistsException uee) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (UserInvalidException uie) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	@DeleteMapping("/auth/usuarios/{email}")
	public ResponseEntity<User> deletaUsuario(@PathVariable String email,
	@RequestHeader("Authorization") String token) {
		try {
			return new ResponseEntity<>(service.removeUsuario(email, token), HttpStatus.OK);
		} catch (UserExistsException uee) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		} catch (UserInvalidException uie) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}	 
    
}
