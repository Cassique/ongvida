package com.ongvida.domain.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import com.ongvida.domain.ValidationGroups;
import com.sun.istack.NotNull;

@Entity

public class Professor {
	
    @Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.ProfessorId.class)
	@NotNull
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotBlank
	@Size(max = 60 )

    private String name;
	
	@NotBlank
	@Size(max = 255)
	private String rg;
	
	@NotBlank
	@Size(max = 255)
	private String cpf;
	
	@NotNull
    private LocalDate dataNascimento;
	
	@ManyToMany
	private Set<Aluno> alunos = new HashSet<>();
	
	public Professor(){
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public LocalDate getData_nascimento() {
		return dataNascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.dataNascimento = data_nascimento;
	}

	public Set<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Aluno> alunos) {
		this.alunos = alunos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		return Objects.equals(id, other.id);
	}

	}
