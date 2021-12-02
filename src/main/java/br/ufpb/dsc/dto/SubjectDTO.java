package br.ufpb.dsc.dto;

import java.util.ArrayList;
import java.util.List;


import br.ufpb.dsc.entities.Comments;
import br.ufpb.dsc.entities.Subject;
import br.ufpb.dsc.exceptions.SubjectInvalidException;
import lombok.Data;

@Data
public class SubjectDTO{
	
	private int id;
	
	private String name;
	
	private int likes;
	
	private double notes;
	
	private List<Comments> comments;
	
	public SubjectDTO(Subject s) {
		super();
		this.id = s.getId();
		this.name = s.getName();
		this.likes = s.getLikes();
		this.notes = s.getNotes();
		this.comments = s.getComments();
	}

	
	public void setNotes(double notes) {
		this.getMediaNotes(notes);
	}

	public List<Comments> getComments() {
		List<Comments> comentariosTemp = new ArrayList<>();
		for(Comments c: this.comments) {
			if(c.getIsRemove() == 0) {
				comentariosTemp.add(c);
			}
		}
		return comentariosTemp;
	}

	public boolean checkSubject() {
		if(this.name == null || this.name.isBlank() || this.name.isEmpty()) {
			throw new SubjectInvalidException("Nome inválido", "O nome da disciplina é um campo de texto obrigatório");
		} if(this.likes<=0) {
			throw new SubjectInvalidException("Quantidade de Likes inválido", "A quantidade de likes é um campo de texto obrigatório");
		} if(this.notes == 0.0) {
			throw new SubjectInvalidException("Notas inválida", "A nota da disciplina é um campo de texto obrigatório");
		}
		return true;
	}
	
	public double getMediaNotes(double value) {
		return this.notes == 0 ? value : ((this.notes + value) / 2);
	}

}
