package com.ongvida.domain.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ongvida.domain.model.Aluno;
import com.ongvida.domain.repository.AlunoRepository;
@Service
public class CadastroAlunoService {
	@Autowired
	private AlunoRepository alunoRepository;
	public Aluno salvar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	public void excluir(Long alunoId) {
		alunoRepository.deleteById(alunoId);
	}
		}


