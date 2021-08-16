package com.ongvida.domain.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ongvida.domain.model.Aluno;
@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long>{
	List<Aluno> findByNome(String nome);
	List<Aluno> findByNomeContaining(String nome);
	Aluno findLifindById(Long id);
}
