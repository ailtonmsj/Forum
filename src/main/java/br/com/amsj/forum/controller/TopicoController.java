package br.com.amsj.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.amsj.forum.controller.dto.TopicoDto;
import br.com.amsj.forum.controller.form.TopicoForm;
import br.com.amsj.forum.model.Topico;
import br.com.amsj.forum.repository.CursoRepository;
import br.com.amsj.forum.repository.TopicoRepository;


@RestController
@RequestMapping("/topicos")
public class TopicoController {
	
	@Autowired
	TopicoRepository topicoRepository;
	
	@Autowired
	CursoRepository cursoRepository;
	
	@GetMapping
	public List<TopicoDto> list(){
		
		List<Topico> topicos = topicoRepository.findAll();		
		
		return TopicoDto.converter(topicos);
	}
	
	@GetMapping("/{idTopico}")
	public TopicoDto get(@PathVariable Long idTopico){
		
		Optional<Topico> topico = topicoRepository.findById(idTopico);
		
		return TopicoDto.converter(topico.get());
	}
	
	@GetMapping("/byCursoName/{nomeCurso}")
	public List<TopicoDto> list(@PathVariable String nomeCurso){
		
		List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
		
		return TopicoDto.converter(topicos);
	}
	
	@PostMapping
	public ResponseEntity<TopicoDto> insert(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder) {
		
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri =  uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(TopicoDto.converter(topico));
	}

}
