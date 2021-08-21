package com.ongvida.api.model;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor

public class ProfessorSummaryModel {

	private Long id;
	private String name;
	private String cpf;
 
	
	
}
