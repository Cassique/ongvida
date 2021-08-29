package com.ongvida.services;

import com.ongvida.api.model.StudentModel;
import com.ongvida.dtos.StudentDTO;
import com.ongvida.entities.Student;
import com.ongvida.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    public Student create(Student student) {
        return studentRepository.save(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }

    public void delete(Long id) {
        var t = studentRepository.findById(id);
        t.ifPresent(studentRepository::delete);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByNameContaining(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }

    public StudentModel parseToStudentModel(Student student) {
        return modelMapper.map(student, StudentModel.class);
    }

    public Student parseToStudentEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }
}
