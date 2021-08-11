package com.ongvida.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ongvida.domain.model.Professor;

@Repository

public interface ProfessorRepository extends JpaRepository<Professor,Long>{

	List<Professor> findByNome(String nome);
	List<Professor> findByNomeContaining(String nome);
}
