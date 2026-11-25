package com.finavizo.api.auth.domain.repositories;

import com.finavizo.api.auth.domain.entities.UserEntity;

public interface UserRepository {
    int countByUsername(String username);

    UserEntity create(UserEntity newUser);
}
