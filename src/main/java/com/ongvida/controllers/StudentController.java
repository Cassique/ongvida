package com.ongvida.controllers;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import com.ongvida.dtos.StudentDTO;
import com.ongvida.services.StudentService;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ongvida.entities.Student;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @ApiOperation(value = " obter listagem com todos os estudantes")
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/findByName/{name}")
    @ApiOperation(value = " obter listagem com estudantes encontrados com o nome solicitado")
    public List<Student> findByName(@PathVariable String name) {
        return studentService.findByName(name);
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = " obter listagem com estudantes encontrados com a mesma cadeia de caracteres passada")
    public ResponseEntity<List<Student>> findByNameContaining(@PathVariable String name) throws NotFoundException {
        return ResponseEntity.ok().body(studentService.findByNameContaining(name));
    }

    @GetMapping("/{studentId}")
    @ApiOperation(value = " obter estudante por id passado")
    public ResponseEntity<Student> findById(@PathVariable Long studentId) {
        Optional<Student> student = studentService.findById(studentId);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "cadastrar novo estudante")
    public ResponseEntity<?> create(@Valid @RequestBody StudentDTO studentDTO) {
        var studentEntity = studentService.toEntity(studentDTO);
        return ResponseEntity.ok().body(studentService.create(studentEntity));
    }

    @PatchMapping("/{studentId}")
    @ApiOperation(value = "atualizar estudante")
    public ResponseEntity<Student> update(@Valid @PathVariable Long studentId,
                                          @RequestBody Student student) {
        if (studentService.findById(studentId).isEmpty()) return ResponseEntity.notFound().build();
        student = studentService.update(student);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{studentId}")
    @ApiOperation(value = "deletar estudante do cadastro")
    public ResponseEntity<Void> delete(@PathVariable Long studentId) {
        if (studentService.findById(studentId).isEmpty()) return ResponseEntity.notFound().build();
        studentService.delete(studentId);
        return ResponseEntity.noContent().build();
    }

}


