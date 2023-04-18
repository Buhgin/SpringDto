package org.merkulov.service;

import org.merkulov.payload.DTO.LoginDTO;
import org.merkulov.payload.DTO.UserDTO;

public interface AuthService {
    String login(LoginDTO loginDto);
    String registration(UserDTO userDTO);
}
