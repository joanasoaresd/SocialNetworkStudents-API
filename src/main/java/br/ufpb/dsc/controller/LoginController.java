package br.ufpb.dsc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.dsc.dto.LoginResponseDTO;
import br.ufpb.dsc.dto.UserLoginDTO;
import br.ufpb.dsc.service.JWTService;

@RestController
@RequestMapping("/auth")
public class LoginController {
    
	@Autowired
	private JWTService jwtService;

	@PostMapping("/api/login")
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody UserLoginDTO user) {
    return new ResponseEntity<>(jwtService.authenticate(user), HttpStatus.OK);
    }
    
}
