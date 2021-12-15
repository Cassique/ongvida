package com.ongvida.entities;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 60 )
	private String firstName;

	@NotBlank
	@Size(max = 60 )
	private String lastName;
	
	@NotBlank
	@Size(max = 255)
	private String rg;
	
	@NotBlank
	@Size(max = 255)
	private String cpf;

    private LocalDate birthDate;
	
	@ManyToMany
	private Set<Student> students;

	@OneToMany
	private Set<Subject> subjects;

	}
