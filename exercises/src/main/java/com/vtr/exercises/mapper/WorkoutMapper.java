package com.vtr.exercises.mapper;

import com.vtr.exercises.dto.WorkoutDTO;
import com.vtr.exercises.model.Workout;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    WorkoutDTO toDTO (Workout workout);
    Workout toEntity(WorkoutDTO workoutDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedEntityFromDto(WorkoutDTO dto, @MappingTarget Workout workout);
}
