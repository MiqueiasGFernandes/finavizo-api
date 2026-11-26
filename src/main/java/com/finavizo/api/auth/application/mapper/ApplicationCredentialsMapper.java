package com.finavizo.api.auth.application.mapper;

import org.mapstruct.Mapper;

import com.finavizo.api.auth.application.command.LoginCommand;
import com.finavizo.api.auth.domain.entities.CredentialsEntity;

@Mapper(componentModel = "spring")
public interface ApplicationCredentialsMapper {
    CredentialsEntity toDomain(LoginCommand input);
}
