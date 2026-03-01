package com.vtr.exercises.mapper;

import com.vtr.exercises.dto.ExerciseDTO;
import com.vtr.exercises.model.Exercises;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    Exercises toEntity (ExerciseDTO exerciseDTO);
    ExerciseDTO toDTO (Exercises exercises);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void UpdatedExerciseEntityForDTO(ExerciseDTO exerciseDTO, @MappingTarget Exercises exercises);
}
