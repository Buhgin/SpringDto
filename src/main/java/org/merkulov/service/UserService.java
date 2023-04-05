package org.merkulov.service;

import org.merkulov.payload.response.UserResponse;
import org.merkulov.payload.DTO.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO,Long roleId);
    UserDTO  updateUser(Long id, UserDTO  userDTO);
    UserDTO getUserById(Long id);
    void deletePost(Long id);
    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);

}
