package com.finavizo.api.auth.infraestructure.persistence;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserModel, UUID> {
    int countByUsername(String username);

}
