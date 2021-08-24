package com.ongvida.api.model;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastroProfessorModel {
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	private ProfessorModel toProfessorModel(Professor professor){
		return modelMapper.map(professor, ProfessorModel.class);
	}
	private final ProfessorRepository professorRepository = null;
	
	public ProfessorModel salvar(Professor professor) {
		return toProfessorModel(professor);
	}
	
	public void excluir(Long professorId) {
		professorRepository.deleteById(professorId);
	}
	}
