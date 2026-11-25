package com.finavizo.api.auth.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.finavizo.api.auth.application.command.AddUserCommand;
import com.finavizo.api.auth.application.dto.AddUserOutputDto;
import com.finavizo.api.auth.domain.use_cases.AddUserUseCase;

@RestController
@RequestMapping("/api/auth")
public class RestAuthController {
    private final AddUserUseCase addUserUseCase;

    RestAuthController(AddUserUseCase addUserUseCase) {
        this.addUserUseCase = addUserUseCase;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AddUserOutputDto createUser(@RequestBody AddUserCommand entity) {
        return this.addUserUseCase.execute(entity);
    }

}