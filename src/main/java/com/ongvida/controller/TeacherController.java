package com.ongvida.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.ongvida.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ongvida.api.model.TeacherModel;
import com.ongvida.entities.Teacher;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RestController
@RequestMapping("/api/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;
    private final ModelMapper modelMapper;


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

    @GetMapping("/{name}")
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
    public TeacherModel create(@Valid @RequestBody Teacher teacher) {
        var newTeacher = teacherService.create(teacher);

        return teacherService.parseToTeacherModel(newTeacher);
    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity<Void> delete(@PathVariable Long teacherId) {
        if (teacherService.findById(teacherId).isEmpty()) return ResponseEntity.notFound().build();
        teacherService.delete(teacherId);
        return ResponseEntity.noContent().build();
    }

}
