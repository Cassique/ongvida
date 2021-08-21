package com.ongvida.api.model;

import org.springframework.stereotype.Service;
import com.ongvida.domain.model.Aluno;
import com.ongvida.domain.repository.AlunoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CadastroAlunoModel {
	
	private final AlunoRepository alunoRepository = null;
	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
		
	}
	
	public void excluir(Long alunoId) {
		alunoRepository.deleteById(alunoId);
		
	}
	
	}
	
	


