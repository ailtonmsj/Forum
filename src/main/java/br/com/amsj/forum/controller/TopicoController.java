package br.com.amsj.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

}
