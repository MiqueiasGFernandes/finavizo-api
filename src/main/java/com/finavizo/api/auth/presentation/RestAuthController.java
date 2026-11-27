package com.finavizo.api.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finavizo.api.auth.application.command.AddUserCommand;
import com.finavizo.api.auth.application.command.LoginCommand;
import com.finavizo.api.auth.application.dto.AddUserOutputDto;
import com.finavizo.api.auth.application.dto.SessionOutputDto;
import com.finavizo.api.auth.domain.use_cases.AddSessionUseCase;
import com.finavizo.api.auth.domain.use_cases.AddUserUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class RestAuthController {
    private final AddUserUseCase addUserUseCase;

    private final AddSessionUseCase addSessionuseCase;

    RestAuthController(
            AddUserUseCase addUserUseCase,
            AddSessionUseCase addSessionUseCase) {
        this.addUserUseCase = addUserUseCase;
        this.addSessionuseCase = addSessionUseCase;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AddUserOutputDto createUser(@Valid @RequestBody AddUserCommand entity) {
        return this.addUserUseCase.execute(entity);
    }

    @PostMapping("/login")
    public SessionOutputDto login(@Valid @RequestBody LoginCommand input) {
        return this.addSessionuseCase.execute(input);
    }

}