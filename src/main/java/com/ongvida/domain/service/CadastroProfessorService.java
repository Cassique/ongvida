package com.ongvida.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;
@Service
public class CadastroProfessorService {
	@Autowired
	private ProfessorRepository professorRepository;
	public Professor salvar(Professor professor) {
		return professorRepository.save(professor);
	}
	public void excluir(Long professorId) {
		professorRepository.deleteById(professorId);
	}
	
	
	
}
