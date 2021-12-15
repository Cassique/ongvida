package com.ongvida.services;

import com.ongvida.dtos.TeacherDTO;
import com.ongvida.entities.Teacher;
import com.ongvida.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Teacher create(Teacher t) {
        return teacherRepository.save(t);
    }

    public void delete(Long id) {
        var t = teacherRepository.findById(id);
        t.ifPresent(teacherRepository::delete);
    }

    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    public List<Teacher> findByNameContaining(String name) {
        return teacherRepository.findByNameContaining(name);
    }

    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    public List<Teacher> findByName(String name) {
        return teacherRepository.findByNameContaining(name);
    }

    public TeacherDTO toDTO(Teacher teacher) {
         return TeacherDTO.builder()
                 .birthDate(teacher.getBirthDate())
                 .cpf(teacher.getCpf())
                 .firstName(teacher.getFirstName())
                 .lastName(teacher.getLastName())
                 .build();
    }

    public Teacher toEntity(TeacherDTO teacherDTO){
        return Teacher.builder()
                .birthDate(teacherDTO.getBirthDate())
                .cpf(teacherDTO.getCpf())
                .firstName(teacherDTO.getFirstName())
                .lastName(teacherDTO.getLastName())
                .build();
    }
}
