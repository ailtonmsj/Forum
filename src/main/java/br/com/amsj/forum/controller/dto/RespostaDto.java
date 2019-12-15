package br.com.amsj.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.amsj.forum.model.Resposta;

public class RespostaDto {
	
	private Long id;
	private String mensagem;
	private LocalDateTime dataCriacao;
	private String autor;
	private Boolean solucao;
	
	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.dataCriacao = resposta.getDataCriacao();
		this.autor = resposta.getAutor().getNome();
		this.solucao = resposta.getSolucao();
	}

	public Long getId() {
		return id;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public String getAutor() {
		return autor;
	}

	public Boolean getSolucao() {
		return solucao;
	}
}
