package br.ufpb.dsc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import br.ufpb.dsc.exceptions.SubjectInvalidException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectDTO{
	
	private int id;
	@NotNull
	private String name;
	@NotNull
	private int likes;
	@NotNull
	private double[] notes;
	
	public SubjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public double[] getNotes() {
		return notes;
	}
	public void setNotes(double[] notes) {
		this.notes = notes;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean checkSubject() {
		if(this.name == null || this.name.isBlank() || this.name.isEmpty()) {
			throw new SubjectInvalidException("Nome inválido", "O nome da disciplina é um campo de texto obrigatório");
		} if(this.likes<=0) {
			throw new SubjectInvalidException("Quantidade de Likes inválido", "A quantidade de likes é um campo de texto obrigatório");
		} if(this.notes == null) {
			throw new SubjectInvalidException("Notas inválida", "A nota da disciplina é um campo de texto obrigatório");
		}
		return true;
	}
	
	public double getMediaNotes() {
		double media = 0;
		for(double d: this.notes) {
			media += d;
		}
		media = media/this.notes.length;
		return media;
	}

}
