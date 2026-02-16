package com.vtr.exercises.mapper;

import com.vtr.exercises.dto.security.PersonalUserRequestDTO;
import com.vtr.exercises.dto.security.PersonalUserResponseDTO;
import com.vtr.exercises.model.Permission;
import com.vtr.exercises.model.PersonalUsers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PersonalUserMapper {

    PersonalUserMapper INSTANCE = Mappers.getMapper(PersonalUserMapper.class);

    @Mapping(target = "roles", source = "permissions", qualifiedByName = "mapPermissionsToRoles")
    PersonalUserResponseDTO toResponseDTO(PersonalUsers entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "permissions", ignore = true)
    @Mapping(target = "active", constant = "true")
    PersonalUsers toEntity(PersonalUserRequestDTO dto);

    @Named("mapPermissionsToRoles")
    default List<String> mapPermissionsToRoles(List<Permission> permissions) {
        if (permissions == null) return null;
        return permissions.stream()
                .map(Permission::getDescription)
                .collect(Collectors.toList());
    }
}