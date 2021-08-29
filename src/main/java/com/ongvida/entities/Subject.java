package com.ongvida.entities;

import com.ongvida.enums.SubjectCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private SubjectCode code;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "subject")
    private Set<Lesson> lesson;

}
