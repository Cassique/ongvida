package com.ongvida.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import com.ongvida.dtos.TeacherDTO;
import com.ongvida.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ongvida.api.model.TeacherModel;
import com.ongvida.entities.Teacher;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public List<TeacherModel> findAll() {
        return teacherService.findAll()
                .stream()
                .map(teacherService::parseToTeacherModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{teacherId}")
    public ResponseEntity<TeacherModel> findById(@PathVariable Long teacherId) {

        var teacher = teacherService.findById(teacherId);
        if (teacher.isEmpty()) return ResponseEntity.noContent().build();

        return ResponseEntity.ok().body(teacherService.parseToTeacherModel(teacher.get()));
    }

    @GetMapping("/findByName/{name}")
    public TeacherModel findByName(@PathVariable String name) {
        Teacher teacher = (Teacher) teacherService.findByName(name);
        return teacherService.parseToTeacherModel(teacher);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<TeacherModel>> findByNameContaining(@PathVariable String name) {
        List<Teacher> teachers = teacherService.findByNameContaining(name);

        var t = teachers.stream()
                .map(teacherService::parseToTeacherModel)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(t);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TeacherModel create(@Valid @RequestBody TeacherDTO teacherDTO) {;
        var newTeacher = teacherService.create(teacherService.parseToTeacherEntity(teacherDTO));
        return teacherService.parseToTeacherModel(newTeacher);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> delete(@PathVariable Long teacherId) {
        if (teacherService.findById(teacherId).isEmpty()) return ResponseEntity.notFound().build();
        teacherService.delete(teacherId);
        return ResponseEntity.noContent().build();
    }

}
