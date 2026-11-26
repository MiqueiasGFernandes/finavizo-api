package com.finavizo.api.auth.application.use_cases;

import org.springframework.stereotype.Service;

import com.finavizo.api.auth.application.command.LoginCommand;
import com.finavizo.api.auth.application.dto.SessionOutputDto;
import com.finavizo.api.auth.application.mapper.ApplicationCredentialsMapper;
import com.finavizo.api.auth.domain.entities.CredentialsEntity;
import com.finavizo.api.auth.domain.entities.UserEntity;
import com.finavizo.api.auth.domain.exceptions.IncorrectUsernameOrPasswordException;
import com.finavizo.api.auth.domain.ports.JwtEncoderPort;
import com.finavizo.api.auth.domain.ports.PasswordHasherPort;
import com.finavizo.api.auth.domain.repositories.UserRepository;
import com.finavizo.api.auth.domain.use_cases.AddSessionUseCase;

@Service
public class AddSessionUseCaseImpl implements AddSessionUseCase {

    private final UserRepository userRepository;

    private final ApplicationCredentialsMapper mapper;

    private final PasswordHasherPort passwordHasherPort;

    private final JwtEncoderPort jwtEncoderPort;

    public AddSessionUseCaseImpl(
            UserRepository userRepository,
            ApplicationCredentialsMapper mapper,
            PasswordHasherPort passwordHasherPort,
            JwtEncoderPort jwtEncoderPort) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.passwordHasherPort = passwordHasherPort;
        this.jwtEncoderPort = jwtEncoderPort;
    }

    @Override
    public SessionOutputDto execute(LoginCommand input) {
        CredentialsEntity credentials = this.mapper.toDomain(input);

        UserEntity user = this.userRepository.findOneByUsername(input.username);

        if (user == null) {
            throw new IncorrectUsernameOrPasswordException();
        }

        boolean isMatchingPasswordHashes = this.passwordHasherPort.matches(credentials.password, user.password);

        if (!isMatchingPasswordHashes) {
            throw new IncorrectUsernameOrPasswordException();
        }

        credentials.accessToken = this.jwtEncoderPort.sign(user.id);

        return new SessionOutputDto(credentials.accessToken);

    }

}
