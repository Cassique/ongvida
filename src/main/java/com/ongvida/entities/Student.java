package com.ongvida.entities;

import java.time.LocalDate;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;
	
	@NotBlank
	@Size(max = 255)
	private String rg;
	
	@NotBlank
	@Size(max = 255)
	private String cpf;
	
	@NotNull
    private LocalDate birthDate;
	
	@ManyToMany(mappedBy = "students")
	private Set<Teacher> teachers;

	@OneToMany
	@JoinTable(name = "student_subjects")
	private Set<Subject> subjects;
	
}
