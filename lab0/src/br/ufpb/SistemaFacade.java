package br.ufpb;

import java.util.List;
import java.util.Scanner;

public class SistemaFacade {
	
	protected static final Scanner in = new Scanner(System.in);
	private Sistema sistema;
	private boolean estaLogado;
	private Usuario usuarioAtual;
	
	
	public SistemaFacade(){
		super();
		this.sistema = new Sistema();
		this.estaLogado = false;
	}


	public void verificaSeEstaLogado() {
		if(!estaLogado) {
			String email = in.nextLine();
			String senha = in.nextLine();
			this.estaLogado = this.sistema.realizarLogin(email, senha);		
			usuarioAtual = sistema.getUsuario(email);
		} 		
	}
	
	public List<Usuario> listarUsuarios(){
		return this.sistema.listarUsuarios();
	}
	
	public List<Disciplina> listarDisciplinas(){
		return this.sistema.listarDisciplinas();
	}
	
	public void editarUsuario() {
		String nome = in.nextLine();
		String email = in.nextLine();
		String senha = in.nextLine();
		try {
			sistema.editarUsuario(nome, email, senha);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void atualizarDisciplinaPorNome() {
		String nome = in.nextLine();
		int id = in.nextInt();
		this.sistema.atualizarNomeDisciplina(id, nome);
	}
	
	public void deletarUsuario() {
		String email= in.nextLine();
		this.sistema.deletarUsuario(email);
	}
	
	public void deletarComentario() {
		int id = in.nextInt();
		this.sistema.deleteComentario(id);
	}
	
	public void listarDisciplinaPorId() {
		int id = in.nextInt();
		this.sistema.listarDisciplinaPorId(id);
	}
	
	public void deletarDisciplina() {
		int id = in.nextInt();
		this.sistema.deleteDisciplina(id);
	}
	
	public void listarComentarios() {
		int id = in.nextInt();
		this.sistema.listarComentarios(id);
	}
	
	public void adicionarComentario() {
		int id = in.nextInt();
		String comentarios = in.nextLine();
		this.sistema.adicionarComentario(id, comentarios);
	}
	
	public void adicionarDisciplina() {
		int id = in.nextInt();
		String nome = in.nextLine();
		int likes = in.nextInt();
		double nota = in.nextDouble();
		this.sistema.AdicionarDisciplina(id, nome, likes, nota);
	}
	
	public void atualizarNotaDisciplina() {
		int id = in.nextInt();
		double nota = in.nextDouble();
		this.sistema.atualizarNotaDisciplina(id, nota);
	}
	
	public void criarUsuario() {
		String nome = in.nextLine();
		String email = in.nextLine();
		String senha = in.nextLine();
		this.sistema.criarUsuario(nome, email, senha);
	}

}
