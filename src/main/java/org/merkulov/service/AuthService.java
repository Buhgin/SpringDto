package org.merkulov.service;

import org.merkulov.payload.DTO.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
}
