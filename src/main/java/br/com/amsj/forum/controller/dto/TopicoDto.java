package br.com.amsj.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;

import br.com.amsj.forum.model.StatusTopico;
import br.com.amsj.forum.model.Topico;

public class TopicoDto {
	
	public TopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
		this.status = topico.getStatus();
	}
	
	private Long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private StatusTopico status;
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
	
	public static Page<TopicoDto> converter(Page<Topico> pageTopico) {
		
		Page<TopicoDto> topicosDto = null;
		
		if(pageTopico != null) {
			return pageTopico.map(TopicoDto::new);
		}
		
		return topicosDto;
	}
}
