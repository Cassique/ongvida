package com.ongvida.dtos;

import com.ongvida.entities.Student;
import lombok.Data;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
public class TeacherDTO {

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

    private LocalDate birthDate;

    @ManyToMany
    private Set<Student> students;
}
