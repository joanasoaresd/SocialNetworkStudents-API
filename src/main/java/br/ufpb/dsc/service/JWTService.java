package br.ufpb.dsc.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.dsc.dto.UserLoginDTO;
import br.ufpb.dsc.entities.User;
import br.ufpb.dsc.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JWTService {

    @Autowired
	private UserRepository usersDAO;

	public LoginResponse autentica(UserLoginDTO user) {
		String msgErro = "Email e/ou senha invalido(s). Login nao realizado";
		Optional<User> optUser = usersDAO.findByEmail(user.getEmail());
		if (optUser.isPresent() && user.getPassword().equals(optUser.get().getPassword()))
			return new LoginResponse(geraToken(user));

		return new LoginResponse(msgErro);

	}

	private String geraToken(UserLoginDTO user) {
		String token = Jwts.builder().setSubject(user.getEmail()).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
				.setExpiration(new Date(System.currentTimeMillis() + 1 * 60 * 1000)).compact();
		return token;
	}

	public static final String TOKEN_KEY = "ja pode sair?";

	public Optional<String> recuperaUsuario(String cabecalhoAutorizacao) {
		if (cabecalhoAutorizacao == null || 
			!cabecalhoAutorizacao.startsWith("Bearer ")) {
			throw new SecurityException();
		}

		// Extraindo apenas o token do cabecalho.
		String token = cabecalhoAutorizacao.substring(br.ufpb.dsc.filters.TokenFilter.TOKEN_INDEX);

		String subject = null;
		try {
			subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
		} catch (SignatureException e) {
			throw new SecurityException("Token invalido ou expirado!");
		}
		return Optional.of(subject);
	}
}
