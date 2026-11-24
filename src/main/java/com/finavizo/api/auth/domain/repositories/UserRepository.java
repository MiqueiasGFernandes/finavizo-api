package com.finavizo.api.auth.domain.repositories;

import com.finavizo.api.auth.domain.entities.UserEntity;

public interface UserRepository {
    int countByEmail(String email);
    UserEntity create(UserEntity newUser);
}
