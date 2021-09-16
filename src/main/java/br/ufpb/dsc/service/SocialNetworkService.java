package br.ufpb.dsc.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dsc.entities.Subject;
import br.ufpb.dsc.repository.SubjectRepository;

@Service
public class SocialNetworkService {

	@Autowired
	private SubjectRepository repository;
	
	public Subject save(Subject s) {
		return this.repository.save(s);
	}

	public List<Subject> listAllSubjects() {
		return this.repository.findAll();
	}

	public Optional<Subject> getSubjectById(Integer id) {
		return this.repository.findById(id);
	}
	
	public Subject setSubjectName(int id, String name) {
		Subject temp = this.repository.getById(id);
		temp.setName(name);
		return this.repository.save(temp);
	}
	
	public Subject setSubjectNotes(int id, double[] notes) {
		Subject temp = this.repository.getById(id);
		temp.setNotes(notes);
		return this.repository.save(temp);
	}
	
	public void deleteSubject(int id) {
		this.repository.deleteById(id);
	}
	
	//Falta ordenar lista por nota
	public List<Subject> listOrderedNotes(){
		List<Subject> listTemp = listAllSubjects();		
		return null;
	}
	
}
