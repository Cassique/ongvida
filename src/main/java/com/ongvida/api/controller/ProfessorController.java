package com.ongvida.api.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;
import com.ongvida.domain.service.CadastroProfessorService;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class ProfessorController {
	
	private final ProfessorRepository professorRepository = null;
	private final CadastroProfessorService cadastroProfessorService = new CadastroProfessorService();
	
	@GetMapping
	public List<Professor> listar(){
		
		return professorRepository.findAll();
		}
	@GetMapping("/findAll")
	public List<Professor> findByname(){
		return professorRepository.findByName("Cassio");
	}
	
	@GetMapping("/name/{name}")
	public List<Professor> findBynameContaining(@PathVariable String name){
		return professorRepository.findByNameContaining(name);
	}
	
	@GetMapping("/{teacherId}")
	public ResponseEntity<Professor> buscar(@PathVariable Long professorId) {
		Optional<Professor> professor = professorRepository.findById(professorId);
	
		if(professor.isPresent()) {
			return ResponseEntity.ok(professor.get());
			
		}
		
	return ResponseEntity.notFound().build();
	
}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Professor adicionar(@Valid @RequestBody Professor professor) {
	return	cadastroProfessorService.salvar(professor);
		
	}
	
	@PutMapping("/{teacherId}")
	public ResponseEntity<Professor> atualizar(@Valid @PathVariable Long professorId,
	@RequestBody Professor professor){
		
		if (!professorRepository.existsById(professorId)) {
			return ResponseEntity.notFound().build();
		}
		
		professor.setId(professorId);
		professor = cadastroProfessorService.salvar(professor);
		
		return ResponseEntity.ok(professor);
	}
	
	@DeleteMapping("/{teacherId}")
	public ResponseEntity<Void> remover(@PathVariable Long professorId) {
		if (!professorRepository.existsById(professorId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroProfessorService.excluir(professorId);
		return ResponseEntity.noContent().build();
	}
	
}
