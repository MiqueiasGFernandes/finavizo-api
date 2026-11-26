package com.finavizo.api.auth.domain.use_cases;

import com.finavizo.api.auth.application.command.LoginCommand;
import com.finavizo.api.auth.application.dto.SessionOutputDto;

public interface AddSessionUseCase {
    public SessionOutputDto execute(LoginCommand input);
}
