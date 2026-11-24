package com.finavizo.api.auth.domain.use_cases;

import com.finavizo.api.auth.application.command.AddUserCommand;
import com.finavizo.api.auth.application.dto.AddUserOutputDto;

public interface AddUserUseCase {
    public AddUserOutputDto execute(AddUserCommand newUser);
}
