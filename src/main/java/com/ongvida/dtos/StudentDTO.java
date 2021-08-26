package com.ongvida.dtos;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@Valid
public class StudentDTO {
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
}
