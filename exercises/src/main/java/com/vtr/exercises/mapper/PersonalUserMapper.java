package com.vtr.exercises.mapper;
import com.vtr.exercises.dto.security.AccountCredentialsDTO;
import com.vtr.exercises.model.PersonalUsers;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonalUserMapper {
    @Mapping(target = "gender", source = "gender")
    PersonalUsers toEntity(AccountCredentialsDTO dto);

    AccountCredentialsDTO toDTO(PersonalUsers personalUsers);
}
