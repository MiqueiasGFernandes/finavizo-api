package com.finavizo.api.auth.infraestructure.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finavizo.api.auth.domain.entities.UserEntity;
import com.finavizo.api.auth.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserJpaRepository jpaRepository;

    @Autowired
    private PersistenceUserMapper mapper;

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
}
