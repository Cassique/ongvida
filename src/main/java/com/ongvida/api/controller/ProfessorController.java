package com.ongvida.api.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ongvida.api.model.CadastroProfessorModel;
import com.ongvida.api.model.ProfessorModel;
import com.ongvida.api.model.ProfessorSummaryModel;
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class ProfessorController {
	
	private final ProfessorRepository professorRepository = null;
	
	private final CadastroProfessorModel cadastroProfessorModel = new CadastroProfessorModel();
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	private ProfessorModel toProfessorModel(Professor professor){
		return modelMapper.map(professor, ProfessorModel.class);
	}
	
	private ProfessorSummaryModel toProfessorSummaryModel(Professor professor) {
		
		return modelMapper.map(professor,ProfessorSummaryModel.class);
	}
	
	@GetMapping
	public List<ProfessorSummaryModel> listarTodos(){
	return professorRepository.findAll()
	.stream()
	.map(this::toProfessorSummaryModel)
	.collect(Collectors.toList());
	}	
	
	@GetMapping("/{professorId}")
	public ProfessorModel buscarPorId(@PathVariable Long professorId) {
		Professor professor = professorRepository.findById(professorId)
				.orElseThrow(
						//ProfessorNaoEncontradoException::new
						);
	return toProfessorModel(professor);
			}
	@GetMapping("/{name}")
	public ProfessorModel buscarPeloNome(@PathVariable String name){
		Professor professor = (Professor) professorRepository.findByName(name);
				//ProfessorNaoEncontradoException::new
				//);
				//return professorRepository.findByName("Cassio");
	return toProfessorModel(professor);
	}
	@GetMapping("/name/{name}")
	public ProfessorModel buscarPorLetraDoNome(@PathVariable String name){
		Professor professor = (Professor) professorRepository.findByNameContaining(name);
		return toProfessorModel(professor);
	}
	/*@PutMapping("/{teacherId}")
	public ResponseEntity<Professor> atualizar(@Valid @PathVariable Long professorId,
	@RequestBody Professor professor){
		if (!professorRepository.existsById(professorId)) {
			return ResponseEntity.notFound().build();
		}
		professor.setId(professorId);
		professor = cadastroProfessorModel.salvar(professor);
		return ResponseEntity.ok(professor);
	}*/
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProfessorModel adicionar(@Valid @RequestBody Professor professor) {
	return	cadastroProfessorModel.salvar(professor);
		
	}
	
	
	
	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Void> remover(@PathVariable Long professorId) {
		if (!professorRepository.existsById(professorId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroProfessorModel.excluir(professorId);
		return ResponseEntity.noContent().build();
	}

	}
