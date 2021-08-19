package br.ufpb;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Sistema {
	
	private Map<Integer, Disciplina> disciplinas;
	private Map<String, Usuario> usuarios;
	
	Sistema() {
		this.disciplinas = new LinkedHashMap<Integer, Disciplina>();
		this.usuarios = new LinkedHashMap<String, Usuario>();
	}
	
	public void criarUsuario(String nome, String email, String senha) {
		if(this.usuarios.get(email) == null) {
			Usuario usuario = new Usuario(nome, email, senha);
			usuarios.put(email, usuario);
		} else {
			throw new RuntimeException("Falha ao adicionar disciplina");
		}
	}
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuariosTemp = new ArrayList<>();
		for(Map.Entry<String, Usuario> u: this.usuarios.entrySet()) {
			usuariosTemp.add(u.getValue());
		}
		return usuariosTemp;
	}
	
	public Usuario getUsuario(String email) {
		Usuario usuario = this.getUsuarioNaoExiste(email);
		return usuario;
	}
	
	public Usuario editarUsuario(String nome, String email, String senha) throws Exception {
		Usuario usuario = this.usuarios.get(email);
		if(usuario != null) {
			usuario.setSenha(senha);
			usuario.setNome(nome);
		} else {
			throw new Exception("Falha ao editar usuário!");
		}					
		return usuario;
	}
	
	
	public Usuario deletarUsuario(String email) {
		Usuario usuario = getUsuarioNaoExiste(email);			
		return this.usuarios.remove(email);
	}
	
	public boolean realizarLogin(String email, String senha) {
		Usuario usuario = getUsuarioNaoExiste(email);
		if(usuario.getSenha().equals(senha)) {
			return true;
		}
		return false;
	}
	
	public void AdicionarDisciplina(int id, String nome, int likes, double nota) {		
		if(this.disciplinas.get(id) == null) {
			Disciplina disciplina = new Disciplina(id, nome, likes, nota);
			disciplinas.put(id, disciplina);
		} else {
			throw new RuntimeException("Falha ao adicionar disciplina");
		}		
	}
	
	public List<Disciplina> listarDisciplinas(){
		List<Disciplina> disciplinasTemp = new ArrayList<>();
		for(Map.Entry<Integer, Disciplina> d: this.disciplinas.entrySet()) {
			disciplinasTemp.add(d.getValue());
		}
		return disciplinasTemp;
	}
	
	public Disciplina atualizarNomeDisciplina(int id, String nome) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);		
		disciplina.setNome(nome);				
		return disciplina;
	}
	
	public Disciplina atualizarNotaDisciplina(int id, double nota) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);		
		disciplina.setNota(nota);					
		return disciplina;
	}
	
	public Disciplina adicionarComentario(int id, String comentarios) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);
		disciplina.adicionarComentario(id, comentarios);		
		return disciplina;
	}	
	
	public List<Comentario> deleteComentario(int id) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);
		for(Comentario c: disciplina.getComentarios()) {
			c.setEstaRemovido(true);
		}
		return listarComentarios(id);
	}
	
	public List<Comentario> listarComentarios(int id) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);
		return disciplina.getComentarios();
	}
	
	public Disciplina deleteDisciplina(int id) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);		
		return disciplinas.remove(id);
	}
	
	public Disciplina listarDisciplinaPorId (int id) {
		Disciplina disciplina = getdisciplinaNaoExiste(id);
		return disciplina;
	}
	
	// Retorna todas as disciplinas inseridas no sistema ordenadas pela nota (da maior para a menor. 
	// Pense em todas as possibilidades de erro em cada uma das funcionalidades e programe-se para elas. Use exceções sempre que necessário.
	
	//public List<Disciplina> listarDisciplinasOrdenadasNota() {
		//List<Disciplina> disciplinas = listarDisciplinas();
		//return Collections.sort(disciplinas);
	//}
	
	private Disciplina getdisciplinaNaoExiste(int id) { 
		Disciplina disciplina = this.disciplinas.get(id);
		if(disciplina != null) {
			return disciplina;
		} 
		
		throw new RuntimeException("Disciplina não existe!");						
	}
	
	private Usuario getUsuarioNaoExiste(String email) { 
		Usuario usuario = this.usuarios.get(email);
		if(usuario!= null) {
			return usuario;
		} 
		
		throw new RuntimeException("Usuário não existe!");						
	}

}
