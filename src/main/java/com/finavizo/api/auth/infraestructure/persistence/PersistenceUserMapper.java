package com.finavizo.api.auth.infraestructure.persistence;

import org.mapstruct.Mapper;

import com.finavizo.api.auth.domain.entities.UserEntity;

@Mapper(componentModel = "spring")
public interface PersistenceUserMapper {
    UserModel toModel(UserEntity user);
    UserEntity toDomain(UserModel user);
}
