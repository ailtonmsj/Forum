package br.com.amsj.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.amsj.forum.model.StatusTopico;
import br.com.amsj.forum.model.Topico;

public class TopicoDetailDto {
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
	private String autor;
	private List<RespostaDto> respostasDto;
	
	public TopicoDetailDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus();
		this.autor = topico.getAutor().getNome();
		
		this.respostasDto = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
		
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public StatusTopico getStatus() {
		return status;
	}

	public String getAutor() {
		return autor;
	}

	public List<RespostaDto> getRespostasDto() {
		return respostasDto;
	}
}
