package com.ongvida.dtos;

import com.ongvida.entities.Student;
import lombok.Builder;
import lombok.Data;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class TeacherDTO {

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
}
