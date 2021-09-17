package br.ufpb.dsc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.ufpb.dsc.dto.SubjectDTO;
import br.ufpb.dsc.entities.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{

}
