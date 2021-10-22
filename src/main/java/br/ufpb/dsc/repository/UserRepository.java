package br.ufpb.dsc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufpb.dsc.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>  {
    
    Optional<User> findByEmail(String email);
}
