package com.ongvida.controllers;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.ongvida.dtos.TeacherDTO;
import com.ongvida.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ongvida.entities.Teacher;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/")
    public ResponseEntity<List<TeacherDTO>> findAll() {
        var allTeachers =teacherService.findAll()
                .stream()
                .map(teacherService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(allTeachers);
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable Long teacherId) {
        var teacher = teacherService.findById(teacherId);
        if (teacher.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok().body(teacherService.toDTO(teacher.get()));
    }

    @GetMapping("/findByName/{name}")
    public TeacherDTO findByName(@PathVariable String name) {
        Teacher teacher = (Teacher) teacherService.findByName(name);
        return teacherService.toDTO(teacher);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<TeacherDTO>> findByNameContaining(@PathVariable String name) {
        List<Teacher> teachers = teacherService.findByNameContaining(name);
        var t = teachers.stream()
                .map(teacherService::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(t);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody TeacherDTO teacherDTO) {;
        var newTeacher = teacherService.create(teacherService.toEntity(teacherDTO));
        return ResponseEntity.ok().body(teacherService.toDTO(newTeacher));
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> delete(@PathVariable Long teacherId) {
        if (teacherService.findById(teacherId).isEmpty()) return ResponseEntity.notFound().build();
        teacherService.delete(teacherId);
        return ResponseEntity.noContent().build();
    }

}
