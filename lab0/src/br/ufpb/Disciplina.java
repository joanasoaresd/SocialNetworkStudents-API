package br.ufpb;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Disciplina {
	
	private int id;
	private String nome;
	private List<Comentario> comentarios;
	private int likes;
	private double nota;
	
	public Disciplina() {
	// TODO Auto-generated constructor stub
	}		

	public Disciplina(int id, String nome, int likes, double nota) {
		super();
		this.id = id;
		this.nome = nome;
		this.comentarios = new ArrayList<>();
		this.likes = likes;
		this.nota = nota;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Comentario> getComentarios() {
		List<Comentario> comentariosTemp = new ArrayList<>();
		for(Comentario c: this.comentarios) {
			if(!c.isEstaRemovido()) {
				comentariosTemp.add(c);
			}
		}
		return comentariosTemp;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	
	public void adicionarComentario(int id,String comentario) {
		this.comentarios.add(new Comentario(id, new Date(), comentario));
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}
	
	
}
