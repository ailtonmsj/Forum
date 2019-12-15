package br.com.amsj.forum.controller.form;

import br.com.amsj.forum.model.Curso;
import br.com.amsj.forum.model.Topico;
import br.com.amsj.forum.repository.CursoRepository;

public class TopicoForm {
	
	private String titulo;
	private String mensagem;
	private String curso;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public Topico converter(CursoRepository cursoRepository) {
		
		Curso curso = cursoRepository.findByNome(this.getCurso());
		return new Topico(this.titulo, this.mensagem, curso);
	}
}
