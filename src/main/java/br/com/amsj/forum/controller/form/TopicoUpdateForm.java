package br.com.amsj.forum.controller.form;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.amsj.forum.model.Topico;
import br.com.amsj.forum.repository.TopicoRepository;

public class TopicoUpdateForm {
	
	@NotNull @NotEmpty @Length(min=5)
	private String titulo;
	@NotNull @NotEmpty @Length(min=10)
	private String mensagem;
	
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
	public Topico update(Long id, TopicoRepository topicoRepository) {
		
		Topico topico = topicoRepository.getOne(id);
		topico.setMensagem(this.getMensagem());
		topico.setTitulo(this.titulo);
		
		return topico;
	}

}
