package com.ongvida.api.model;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastroProfessorModel {
	
	private final ProfessorRepository professorRepository = null;
	
	public Professor salvar(Professor professor) {
		return professorRepository.save(professor);
	}
	
	public void excluir(Long professorId) {
		professorRepository.deleteById(professorId);
	}
	private final ModelMapper modelMapper = new ModelMapper();

	private ProfessorSummaryModel toProfessorSummaryModel(Professor professor) {
		return modelMapper.map(professor,ProfessorSummaryModel.class);
	}
	
	private ProfessorModel toProfessorModel(Professor professor){
		return modelMapper.map(professor, ProfessorModel.class);
	}
	}
