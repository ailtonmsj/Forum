package br.com.amsj.forum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amsj.forum.controller.dto.TopicoDto;
import br.com.amsj.forum.model.Topico;
import br.com.amsj.forum.repository.TopicoRepository;


@RestController
public class TopicoController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@RequestMapping("/topicos")
	public List<TopicoDto> lista(){
		
		List<Topico> topicos = topicoRepository.findAll();		
		
		return TopicoDto.converter(topicos);
	}
	
	@RequestMapping("/topicos/{idTopico}")
	public TopicoDto get(@PathVariable Long idTopico){
		
		Optional<Topico> topico = topicoRepository.findById(idTopico);
		
		return TopicoDto.converter(topico.get());
	}
	
	@RequestMapping("/topicos/byCursoName/{nomeCurso}")
	public List<TopicoDto> get(@PathVariable String nomeCurso){
		
		List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		
		return TopicoDto.converter(topicos);
	}

}
