package com.vtr.exercises.mapper;

import com.vtr.exercises.dto.FichaDTO;
import com.vtr.exercises.dto.StudentDTO;
import com.vtr.exercises.model.Ficha;
import com.vtr.exercises.model.Student;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface FichaMapper {

    Ficha toEntity (FichaDTO dto);
    FichaDTO toDto (Ficha ficha);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatedEntityFromDto(FichaDTO dto, @MappingTarget Ficha entity);
}
