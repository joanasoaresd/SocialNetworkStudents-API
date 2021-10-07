package br.ufpb.dsc.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dsc.dto.SubjectDTO;
import br.ufpb.dsc.entities.Subject;
import br.ufpb.dsc.exceptions.SubjectInvalidException;
import br.ufpb.dsc.mapper.SubjectMapper;
import br.ufpb.dsc.repository.SubjectRepository;

@Service
public class SocialNetworkService {

	@Autowired
	private SubjectRepository repository;
	
	public Subject save(SubjectDTO s) throws SubjectInvalidException {
		s.checkSubject();
		if(repository.existsById(s.getId())) {
			throw new SubjectInvalidException("Disciplina já existe no sistema", "Esta disciplina já existe no sistema e pode apenas ser atualizada"); 
		}
		Subject subject = SubjectMapper.MAPPER.toModel(s);
		return this.repository.save(subject);
	}

	public List<Subject> listAllSubjects() {
		return this.repository.findAll();
	}

	public Optional<Subject> getSubjectById(Integer id) throws SubjectInvalidException {
		if(!repository.existsById(id)) {
			throw new SubjectInvalidException("Id da disciplina não encontrado", "O Id desta disciplina não existe no sistema");
		}
		return this.repository.findById(id);
	}
	
	public Subject setSubjectName(int id, String name) throws SubjectInvalidException {
		Subject temp = this.repository.findById(id).get();
		temp.setName(name);
		return this.repository.save(temp);
	}
	
	public Subject setSubjectNotes(int id, double notes) throws SubjectInvalidException {
		Subject temp = this.repository.getById(id);
		if(!repository.existsById(temp.getId())) {
			throw new SubjectInvalidException("Id da disciplina não encontrado", "O Id desta disciplina não existe no sistema");
		}
		temp.setNotes(notes);
		return this.repository.save(temp);
	}
	
	public void deleteSubject(int id) throws SubjectInvalidException {
		if(!repository.existsById(id)) {
			throw new SubjectInvalidException("Id da disciplina não encontrado", "O Id desta disciplina não existe no sistema");
		}
		this.repository.deleteById(id);
	}
	
	public List<SubjectDTO> listOrderedNotes(){
		List<SubjectDTO> listTemp = new ArrayList<>();	
		repository.findAll().forEach(subj -> listTemp.add(SubjectMapper.MAPPER.toDTO(subj)));
		listTemp.sort(Comparator.comparing(subject -> subject.getNotes()*(-1)));		
		return listTemp;
	}

	public List<SubjectDTO> listOrderedLikes(){
		List<SubjectDTO> listTemp = new ArrayList<>();	
		repository.findAll().forEach(subj -> listTemp.add(SubjectMapper.MAPPER.toDTO(subj)));
		listTemp.sort(Comparator.comparing(subject -> subject.getLikes()*(-1)));		
		return listTemp;
	}
	
}
