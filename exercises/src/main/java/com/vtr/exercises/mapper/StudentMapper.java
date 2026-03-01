package com.vtr.exercises.mapper;

import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDTO toDTo (Student student);
    Student toEntity (StudentDTO studentDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedEntityFromDto(StudentDTO dto, @MappingTarget Student student);
}
