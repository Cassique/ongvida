package com.ongvida.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ongvida.domain.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,Long>{

	Professor findByName(String name);
	List<Professor> findByNameContaining(String name);
	Optional<Professor> findById(Long id);
	
}
