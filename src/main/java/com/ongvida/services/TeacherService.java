package com.ongvida.services;

import com.ongvida.api.model.TeacherModel;
import com.ongvida.dtos.TeacherDTO;
import com.ongvida.entities.Teacher;
import com.ongvida.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final ModelMapper modelMapper;

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

    public TeacherModel parseToTeacherModel(Teacher teacher) {
        return modelMapper.map(teacher, TeacherModel.class);
    }

    public Teacher parseToTeacherEntity(TeacherDTO teacherDTO) {
        return modelMapper.map(teacherDTO, Teacher.class);
    }
}
