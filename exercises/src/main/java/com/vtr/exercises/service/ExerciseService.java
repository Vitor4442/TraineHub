package com.vtr.exercises.service;

import com.vtr.exercises.dto.ExerciseDTO;
import com.vtr.exercises.mapper.ExerciseMapper;
import com.vtr.exercises.model.Exercises;
import com.vtr.exercises.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseMapper mapper;
    private final ExerciseRepository repository;
    private final VideoStorageService videoStorageService;

    @Transactional(readOnly = true)
    public Page<ExerciseDTO> getAllExercises (Pageable pageable){
        var exercises = repository.findAll(pageable);
        return exercises.map(mapper::toDTO);
    }

    @Transactional
    public ExerciseDTO AddExercise(ExerciseDTO exerciseDTO){
        return mapper.toDTO(repository.save(mapper.toEntity(exerciseDTO))) ;
    }

    @Transactional
    public ExerciseDTO attExercise(Long id, ExerciseDTO exerciseDTO) {
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        mapper.UpdatedExerciseEntityForDTO(exerciseDTO, exercises);
        return mapper.toDTO(repository.save(exercises));
    }

    @Transactional
    public void deleteExercise(Long id) {
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        repository.delete(exercises);
    }

    @Transactional(readOnly = true)
    public ExerciseDTO findById(Long id) {
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        return mapper.toDTO(exercises);
    }

    @Transactional
    public ExerciseDTO uploadVideoToExercise(Long id, MultipartFile videoFile) {
        String fileName = videoStorageService.storeFile(videoFile);
        String fileDownloadUri = "/alunos/video/download/" + fileName;
        Exercises exercises = repository.findById(id).orElseThrow(() -> new RuntimeException("Exercise not found"));
        exercises.setVideo_url(fileDownloadUri);
        return mapper.toDTO(exercises);
    }
}
