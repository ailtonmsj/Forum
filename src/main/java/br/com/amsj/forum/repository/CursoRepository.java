package br.com.amsj.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amsj.forum.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	
	public Curso findByNome(String nome); 

}
