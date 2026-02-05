package com.vtr.exercises.service;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.mapper.StudentMapper;
import com.vtr.exercises.model.Student;
import com.vtr.exercises.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    @Transactional
    public StudentDTO addStudent(Student student){
       return mapper.toDTo(repository.save(student));
    }

    @Transactional(readOnly = true)
    public Page<StudentDTO> findAllStudents(Pageable pageable){
        var student = repository.findAll(pageable);
        return student.map(mapper::toDTo);
    }

    @Transactional
    public StudentDTO putStudent(Long id, StudentDTO studentDTO){
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not Found"));
        mapper.updatedEntityFromDto(studentDTO, student);
        return mapper.toDTo(repository.save(student));
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        repository.delete(student);
    }

    @Transactional(readOnly = true)
    public StudentDTO findByIdStudent(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        return mapper.toDTo(student);
    }

    @Transactional
    public StudentDTO disableStudent(Long id) {
        Student student = repository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        repository.disableStudent(id);
        return mapper.toDTo(student);
    }
}
