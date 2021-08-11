package com.ongvida.api.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ongvida.domain.model.Professor;
import com.ongvida.domain.repository.ProfessorRepository;

@RestController
@RequestMapping("/professores")

public class ProfessorController {
	
	@Autowired
	private ProfessorRepository professorRepository;
	
	@GetMapping
	public List<Professor> listar(){
		
		return professorRepository.findAll();
		}
	@GetMapping("/listar")
	public List<Professor> listarPorNome(){
		return professorRepository.findByNome("Cassio");
	}
	
	@GetMapping("/listarcontaining")
	public List<Professor> listarPorNomeContaining(){
		return professorRepository.findByNomeContaining("Ca");
	}
	
	@GetMapping("/{professorId}")
	public ResponseEntity<Professor> buscar(@PathVariable Long professorId) {
		Optional<Professor> professor = professorRepository.findById(professorId);
	
		if(professor.isPresent()) {
			return ResponseEntity.ok(professor.get());
			
		}
		
	return ResponseEntity.notFound().build();
	
}

	@PostMapping
	public Professor adicionar(@RequestBody Professor professor) {
	return	professorRepository.save(professor);
		
	}
	
}
