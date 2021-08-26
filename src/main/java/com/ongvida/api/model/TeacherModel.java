package com.ongvida.api.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherModel {
	private Long id;
	private String name;
	private String rg;
	private String cpf;
	private LocalDate birthDate;
			
}
