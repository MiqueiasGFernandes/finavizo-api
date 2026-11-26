package com.finavizo.api.auth.infraestructure.persistence;

import org.springframework.stereotype.Repository;

import com.finavizo.api.auth.domain.entities.UserEntity;
import com.finavizo.api.auth.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository jpaRepository;

    private final PersistenceUserMapper mapper;

    UserRepositoryImpl(UserJpaRepository jpaRepository, PersistenceUserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public int countByUsername(String username) {
        return this.jpaRepository.countByUsername(username);
    }

    @Override
    public UserEntity create(UserEntity newUser) {
        UserModel newUserToModel = this.mapper.toModel(newUser);
        newUserToModel = this.jpaRepository.save(newUserToModel);
        return this.mapper.toDomain(newUserToModel);
    }

    @Override
    public UserEntity findOneByUsername(String username) {
        UserModel user = this.jpaRepository.findOneByUsername(username);
        return this.mapper.toDomain(user);
    }
}
