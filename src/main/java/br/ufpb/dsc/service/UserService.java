package br.ufpb.dsc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dsc.dto.UserDTO;
import br.ufpb.dsc.entities.User;
import br.ufpb.dsc.exceptions.UserExistsException;
import br.ufpb.dsc.exceptions.UserInvalidException;
import br.ufpb.dsc.mapper.UserMapper;
import br.ufpb.dsc.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repository;

    @Autowired
	private JWTService jwtService;

    public User save(UserDTO u) throws IllegalArgumentException {
		if(repository.existsById(u.getId())) 
			throw new UserInvalidException("Usuário já existe no sistema"); 
		if (repository.findByEmail(u.getEmail()).isPresent())
            throw new UserExistsException("");
        if (!u.checkUser())
            throw new UserInvalidException("");
        User user = UserMapper.MAPPER.toModel(u);
        return this.repository.save(user);
	}

	public UserDTO deletaUsuario(String cabecalhoDeAutorizacao) {
		//vai ler o token e recuperar o subject
		Optional<String> userId = jwtService.recuperaUsuario(cabecalhoDeAutorizacao);

		//vai pegar o subject do token e ver se existe um usuário correspondente
		User user = validateUser(userId);

		//vai remover o usuario associado ao token
		repository.delete(user);
		return new UserDTO();
	}

	private User validateUser(Optional<String> id) {
		if(id.isEmpty())
			throw new UserInvalidException("");

		Optional<User> usuario = repository.findByEmail(id.get());
		if (usuario.isEmpty())
			throw new UserInvalidException("");

		return usuario.get();

	}
}
