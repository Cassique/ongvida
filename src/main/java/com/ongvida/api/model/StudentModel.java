package com.ongvida.api.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentModel {

    private Long id;

    private String name;

    private String rg;

    private String cpf;

    private LocalDate birthDate;

}
