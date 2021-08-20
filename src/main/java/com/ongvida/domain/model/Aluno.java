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
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Aluno {
    
	@Valid
	@ConvertGroup(from = Default.class, to = ValidationGroups.AlunoId.class)
	@NotNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(@Valid Long alunoId) {
		// TODO Auto-generated method stub
		
	}

	@NotBlank
	
	private String nome;
	
	@NotBlank
	@Size(max = 255)
	private String rg;
	
	@NotBlank
	@Size(max = 255)
	private String cpf;
	
	@NotNull
    private LocalDate dataNascimento;
	
	@ManyToMany(mappedBy = "alunos")
	private Set<Professor> professores = new HashSet<>();

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
		Aluno other = (Aluno) obj;
		return Objects.equals(id, other.id);
	}

	
}
