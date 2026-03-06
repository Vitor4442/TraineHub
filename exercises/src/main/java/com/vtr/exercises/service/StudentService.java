package com.vtr.exercises.service;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.exception.FileStorageException;
import com.vtr.exercises.file.importer.contract.FileImporter;
import com.vtr.exercises.file.importer.factory.FileImporterFactory;
import com.vtr.exercises.mapper.StudentMapper;
import com.vtr.exercises.model.Student;
import com.vtr.exercises.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;
    private final FileImporterFactory importer;

    @Transactional
    public StudentDTO addStudent(StudentDTO studentDTO){
       return mapper.toDTo( repository.save(mapper.toEntity(studentDTO)));
    }

    @Transactional(readOnly = true)
    public Page<StudentDTO> findAllStudents(Pageable pageable){
        var student = repository.findAll(pageable);
        return student.map(mapper::toDTo);
    }

    public List<StudentDTO> massCreation (MultipartFile file) throws BadRequestException {
        if(file.isEmpty()) throw new BadRequestException("Please set a Valid File!");

        try(InputStream inputStream = file.getInputStream()){
            String filename = Optional.ofNullable(file.getOriginalFilename()).orElseThrow(() -> new BadRequestException("File name cannot be null"));
            FileImporter importer = this.importer.getImporter(filename);
            List<Student> entities = importer.importFile(inputStream).stream().map( dto -> repository.save(mapper.toEntity(dto))).toList();

            return entities.stream().map(mapper::toDTo).toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new FileStorageException("Error processing the file");
        }

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

    @Transactional(readOnly = true)
    public Page<StudentDTO> findByName(String name, Pageable pageable){
        var student = repository.findStudentByName(name, pageable);
        return student.map(mapper::toDTo);
    }
}
