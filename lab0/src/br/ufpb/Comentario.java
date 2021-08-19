package br.ufpb;

import java.util.Date;

public class Comentario {
	private int idUsuario;
	private Date data;
	private String mensagem;
	private boolean estaRemovido;
	
	public Comentario() {}	
	
	public Comentario(int idUsuario, Date data, String mensagem) {
		super();
		this.idUsuario = idUsuario;
		this.data = data;
		this.mensagem = mensagem;
		this.estaRemovido = false;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public boolean isEstaRemovido() {
		return estaRemovido;
	}

	public void setEstaRemovido(boolean estaRemovido) {
		this.estaRemovido = estaRemovido;
	}	
	
}
