package br.com.amsj.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amsj.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	
	

}
