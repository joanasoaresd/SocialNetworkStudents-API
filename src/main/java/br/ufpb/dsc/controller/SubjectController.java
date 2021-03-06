package br.ufpb.dsc.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.TextNode;

import br.ufpb.dsc.dto.CommentsDTO;
import br.ufpb.dsc.dto.SubjectDTO;
import br.ufpb.dsc.entities.Subject;
import br.ufpb.dsc.exceptions.SubjectInvalidException;
import br.ufpb.dsc.service.SubjectService;

@RestController 
public class SubjectController {
	
	@Autowired
	private SubjectService snService;
	
	public SubjectController(SubjectService socialNetworkService) {
		super();
		this.snService = socialNetworkService;
	}
	
	@PostMapping("/disciplinas")
	public ResponseEntity<Subject> addSubject(@RequestBody SubjectDTO s){
		try {
			return new ResponseEntity<Subject>(this.snService.save(s), HttpStatus.CREATED);
		} catch(SubjectInvalidException e) {
			return ResponseEntity.badRequest().build();	
		}					
	}
	
	@GetMapping("/disciplinas")
	public ResponseEntity<Collection<Subject>> getSubjects(){
		try {
			return new ResponseEntity<>(this.snService.listAllSubjects(), HttpStatus.OK);
		} catch(SubjectInvalidException e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/disciplinas/{id}")
	public ResponseEntity<Optional<Subject>> getSubjectById(@PathVariable("id") int id){
		try {
			return new ResponseEntity<Optional<Subject>>(this.snService.getSubjectById(id), HttpStatus.OK);
		} catch(SubjectInvalidException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/disciplinas/{id}/nome")
	public ResponseEntity<Subject> setSubjectName(@PathVariable("id") int id, @RequestBody TextNode name){
		try {
			return new ResponseEntity<Subject>(this.snService.setSubjectName(id, name.asText()), HttpStatus.OK);
		} catch (SubjectInvalidException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PatchMapping("/disciplinas/{id}/nota")
	public ResponseEntity<Subject> setSubjectNotes(@PathVariable("id") int id, @RequestBody double notes){
		try {
			return new ResponseEntity<Subject>(this.snService.setSubjectNotes(id, notes), HttpStatus.OK);
		} catch (SubjectInvalidException e) {
			return ResponseEntity.notFound().build();
		}
	}
		
	@DeleteMapping("/disciplinas/{id}")
	public ResponseEntity<Subject> removeSubject(@PathVariable("id") int id){
		try {
			this.snService.deleteSubject(id);	
			return ResponseEntity.noContent().build();
		} catch (SubjectInvalidException e) {
			return ResponseEntity.notFound().build();
		}		
	}
	
	@GetMapping("/disciplinas/ranking/notas")
	public ResponseEntity<?> getAllSubjectsByNotes(){		
		return new ResponseEntity<>(this.snService.listOrderedNotes(), HttpStatus.OK);
	}

	@GetMapping("/disciplinas/ranking/likes")
	public ResponseEntity<?> getAllSubjectsByLikes(){		
		return new ResponseEntity<>(this.snService.listOrderedLikes(), HttpStatus.OK);
	}

	@PutMapping("/likes/{id}")
    public ResponseEntity<SubjectDTO> addLikes(@PathVariable Integer id, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(snService.addLikesToSubjectById(id, token), HttpStatus.OK);
    }

	@PutMapping("/comment/{id}")
    public ResponseEntity<SubjectDTO> addCommentToSubjectById(@PathVariable Integer id,
            @RequestBody CommentsDTO dto, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(snService.addCommentToSubjectById(id, dto, token), HttpStatus.OK);
    }

	@PutMapping("/notes/{id}")
    public ResponseEntity<SubjectDTO> addNotesToSubjectById(@PathVariable Integer id,
            @RequestBody double nota, @RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(snService.addNotesToSubjectById(id, nota, token), HttpStatus.OK);
    }

}
