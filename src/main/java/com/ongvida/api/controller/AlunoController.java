package com.ongvida.api.controller;

import java.util.List;
import java.util.Optional;
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
import com.ongvida.api.model.CadastroAlunoModel;
import com.ongvida.domain.model.Aluno;
import com.ongvida.domain.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class AlunoController {
	
    private final AlunoRepository alunoRepository = null;
	
    private final CadastroAlunoModel cadastroAlunoService = new CadastroAlunoModel();
	
	@GetMapping
	public List<Aluno> listar(){
		
		return alunoRepository.findAll();
		}
	@GetMapping("/tolist")
	public List<Aluno> listarPorNome(){				
		return alunoRepository.findByNome("alunoAtualizado");
	}
	
	@GetMapping("/tolistcontaining")
	public List<Aluno> listarPorNomeContaining(){
		return alunoRepository.findByNomeContaining("tu");
	}
	
	@GetMapping("/{studentId}")
	public ResponseEntity<Aluno> buscar(@PathVariable Long alunoId) {
		Optional<Aluno> aluno = alunoRepository.findById(alunoId);
	
		if(aluno.isPresent()) {
			return ResponseEntity.ok(aluno.get());
			
		}
		
	return ResponseEntity.notFound().build();
	
}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aluno adicionar(@Valid @RequestBody Aluno aluno) {
	return	cadastroAlunoService.salvar(aluno);
		
	}
	
	@PutMapping("/{studentId}")
	public ResponseEntity<Aluno> atualizar(@Valid @PathVariable Long alunoId,
	@RequestBody Aluno aluno){
		
		if (!alunoRepository.existsById(alunoId)) {
			return ResponseEntity.notFound().build();
		}
		
		aluno.setId(alunoId);
		aluno = cadastroAlunoService.salvar(aluno);
		
		return ResponseEntity.ok(aluno);
	}
	
	@DeleteMapping("/{studentId}")	
	public ResponseEntity<Void> remover(@PathVariable Long alunoId) {
		if (!alunoRepository.existsById(alunoId)) {
			return ResponseEntity.notFound().build();
		}
		
		cadastroAlunoService.excluir(alunoId);
		return ResponseEntity.noContent().build();
	}
	
}


