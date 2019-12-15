package br.com.amsj.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amsj.forum.model.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

	// Used "_" to avoid ambiguity with the attribute name in Topico class (only for instance in this case)
	List<Topico> findByCurso_Nome(String nomeCurso);
	
	

}
