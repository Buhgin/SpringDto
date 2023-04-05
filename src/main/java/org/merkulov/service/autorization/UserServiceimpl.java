package org.merkulov.service.autorization;

import lombok.RequiredArgsConstructor;
import org.merkulov.exception.ResourceNotFoundException;
import org.merkulov.modell.entity.Role;
import org.merkulov.modell.entity.User;
import org.merkulov.payload.DTO.UserDTO;
import org.merkulov.payload.response.UserResponse;
import org.merkulov.repository.RoleRepository;
import org.merkulov.repository.UserRepository;
import org.merkulov.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserServiceimpl implements  UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    @Override
    public UserDTO createUser(UserDTO userDTO, Long roleId) {
        User user = mapToEntity(userDTO);
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findById(roleId).orElseThrow(()->
                new ResourceNotFoundException("role","id",roleId));
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return mapToDTO(user);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long id) {

        User user= userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
    return mapToDTO(user);}

    @Override
    public void deletePost(Long id) {
        User user= userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepository.delete(user);
    }

    @Override
    public UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    private User mapToEntity(UserDTO userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDTO mapToDTO(User newUser) {return modelMapper.map(newUser, UserDTO.class);
    }
}


