package com.ongvida.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.ongvida.api.model.StudentModel;
import com.ongvida.dtos.StudentDTO;
import com.ongvida.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.ongvida.entities.Student;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/findByName/{name}")
    public List<Student> findByName(@PathVariable String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/{name}")
    public List<Student> findByNameContaining(String name) {
        return studentService.findByNameContaining(name);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> findById(@PathVariable Long studentId) {
        Optional<Student> student = studentService.findById(studentId);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentModel create(@Valid @RequestBody StudentDTO studentDTO) {
        var studentEntity = studentService.parseToStudentEntity(studentDTO);

        return studentService.parseToStudentModel(studentService.create(studentEntity));
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> update(@Valid @PathVariable Long studentId,
                                          @RequestBody Student student) {

        if (studentService.findById(studentId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        student = studentService.update(student);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {

        if (studentService.findById(studentId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }

}


