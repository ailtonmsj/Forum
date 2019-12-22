package br.com.amsj.forum.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.amsj.forum.controller.dto.TopicoDetailDto;
import br.com.amsj.forum.controller.dto.TopicoDto;
import br.com.amsj.forum.controller.form.TopicoForm;
import br.com.amsj.forum.controller.form.TopicoUpdateForm;
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
	
	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetailDto> detail(@PathVariable Long id){
		
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new TopicoDetailDto(optional.get()));	
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	// Paging using static parameters
	@GetMapping
	public Page<TopicoDto> list(@RequestParam(required = false) String nomeCurso, 
			@RequestParam int page, @RequestParam int qtd, @RequestParam String order){
		
		Pageable pageable = PageRequest.of(page, qtd, Direction.ASC, order);
		
		if(nomeCurso == null) {
			Page<Topico> pageTopico = topicoRepository.findAll(pageable);
			return TopicoDto.converter(pageTopico);
		}
		else {
			Page<Topico> pageTopico = topicoRepository.findByCurso_Nome(nomeCurso, pageable);
			return TopicoDto.converter(pageTopico);
		}
		
	}
	
	// Paging using dynamic parameters
	@GetMapping("/dynamicPaging")
	public Page<TopicoDto> list(@RequestParam(required = false) String nomeCurso, 
			 Pageable pageable){
		
		if(nomeCurso == null) {
			Page<Topico> pageTopico = topicoRepository.findAll(pageable);
			return TopicoDto.converter(pageTopico);
		}
		else {
			Page<Topico> pageTopico = topicoRepository.findByCurso_Nome(nomeCurso, pageable);
			return TopicoDto.converter(pageTopico);
		}
		
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<TopicoDto> insert(@RequestBody @Valid TopicoForm topicoForm, UriComponentsBuilder uriComponentsBuilder) {
		
		Topico topico = topicoForm.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri =  uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<TopicoDto> update(@PathVariable Long id, @RequestBody @Valid TopicoUpdateForm topicoUpdateForm) {
		
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			Topico topico = topicoUpdateForm.update(id, topicoRepository);		
			return ResponseEntity.ok(new TopicoDto(topico));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Optional<Topico> optional = topicoRepository.findById(id);
		if(optional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
