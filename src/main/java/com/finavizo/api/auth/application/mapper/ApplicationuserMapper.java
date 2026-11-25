package com.finavizo.api.auth.application.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.finavizo.api.auth.application.command.AddUserCommand;
import com.finavizo.api.auth.application.dto.AddUserOutputDto;
import com.finavizo.api.auth.domain.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface ApplicationuserMapper {
    UserEntity toDomain(AddUserCommand user);
    AddUserOutputDto toDto(UserEntity user);
}
