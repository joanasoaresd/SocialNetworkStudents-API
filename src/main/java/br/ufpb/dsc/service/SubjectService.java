package br.ufpb.dsc.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.dsc.dto.CommentsDTO;
import br.ufpb.dsc.dto.SubjectDTO;
import br.ufpb.dsc.entities.Comments;
import br.ufpb.dsc.entities.Subject;
import br.ufpb.dsc.entities.User;
import br.ufpb.dsc.exceptions.SubjectExistsException;
import br.ufpb.dsc.exceptions.SubjectInvalidException;
import br.ufpb.dsc.exceptions.UserExistsException;
import br.ufpb.dsc.exceptions.UserNotLoggedException;
import br.ufpb.dsc.mapper.SubjectMapper;
import br.ufpb.dsc.repository.SubjectRepository;
import br.ufpb.dsc.repository.UserRepository;

@Service
public class SubjectService {

	@Autowired
	private SubjectRepository repository;

	@Autowired
    private UserRepository userRepository;

	@Autowired
    private JWTService jwtSecurity;
	
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

	public SubjectDTO addCommentToSubjectById(int id, CommentsDTO dto, String header) {

		Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado");
        }

        // Verificando crendenciais do usuário logado
        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.isPresent()) {
            throw new UserExistsException("Email do usuário logado não encontrado!");
        }

        if (!repository.existsById(id)) {
            throw new SubjectExistsException("Disciplina não encontrada", "SubjectService.addCommentToSubjectById");
        }
        Subject s = repository.findById(id).get();
        Comments comment = new Comments();
        comment.setMsg(dto.getMsg());
        comment.setSubject(s);
        comment.setUserEmail(dto.getUserEmail());
        s.getComments().add(comment);
        repository.save(s);
        return new SubjectDTO(s);
    }

	public SubjectDTO addLikesToSubjectById(int id, String header) {

		Optional<String> loggedEmail = jwtSecurity.getUser(header);
        if (!loggedEmail.isPresent()) {
            throw new UserNotLoggedException("Usuário não logado");
        }

        // Verificando crendenciais do usuário logado
        Optional<User> user = userRepository.findByEmail(loggedEmail.get());
        if (!user.isPresent()) {
            throw new UserExistsException("Email do usuário logado não encontrado!");
        }

        if (!repository.existsById(id)) {
            throw new SubjectExistsException("Disciplina não encontrada", "addLikesToSubjectById");
        }

        Subject s = repository.findById(id).get();
        s.setLikes(s.getLikes() + 1);
        repository.save(s);
        return new SubjectDTO(s);
    }
	
}
