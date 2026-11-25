package com.finavizo.api.auth.application.use_cases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finavizo.api.auth.application.command.AddUserCommand;
import com.finavizo.api.auth.application.dto.AddUserOutputDto;
import com.finavizo.api.auth.application.mapper.ApplicationuserMapper;
import com.finavizo.api.auth.domain.entities.UserEntity;
import com.finavizo.api.auth.domain.exceptions.UserAlreadyExistsException;
import com.finavizo.api.auth.domain.repositories.UserRepository;
import com.finavizo.api.auth.domain.use_cases.AddUserUseCase;

@Service
public class AddUserUseCaseImpl implements AddUserUseCase {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ApplicationuserMapper userMapper;

    private boolean _hasConflict(String username) {
        int user = this.userRepository.countByUsername(username);
        return user > 0;
    }

    @Override
    public AddUserOutputDto execute(AddUserCommand newUser) {
        UserEntity user = this.userMapper.toDomain(newUser);

        if (this._hasConflict(user.username)) {
            throw new UserAlreadyExistsException(user.username);
        }

        return this.userMapper.toDto(this.userRepository.create(user));
    }

}
