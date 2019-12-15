package br.com.amsj.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public static TopicoDto converter(Topico topico) {
		return new TopicoDto(topico);
	}
	
	public static List<TopicoDto> converter(List<Topico> topicos) {
		
		List<TopicoDto> topicosDto = null;
		
		if(topicos != null) {
			Stream<Topico> streamTopico = topicos.stream();
			topicosDto = streamTopico.map(t -> new TopicoDto(t)).collect(Collectors.toList());
			return topicosDto;
		}
		
		return topicosDto;
	}
}
