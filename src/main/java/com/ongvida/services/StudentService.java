package com.ongvida.services;

import com.ongvida.dtos.StudentDTO;
import com.ongvida.entities.Student;
import com.ongvida.repositories.StudentRepository;
import javassist.NotFoundException;
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

    public List<Student> findByNameContaining(String name) throws NotFoundException {
         var students =studentRepository.findByNameContaining(name);
         if(students.isEmpty()) throw new NotFoundException("There are no char matches for this");
         return students;
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    public List<Student> findByName(String name) {
        return studentRepository.findByName(name);
    }


    public Student toEntity(StudentDTO studentDTO) {
        return Student.builder()
                .birthDate(studentDTO.getBirthDate())
                .cpf(studentDTO.getCpf())
                .name(studentDTO.getName())
                .rg(studentDTO.getRg())
                .build();
    }
}
