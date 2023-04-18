package org.merkulov.service.impl;

import lombok.RequiredArgsConstructor;
import org.merkulov.modell.entity.Role;
import org.merkulov.modell.entity.User;
import org.merkulov.payload.DTO.RoleDTO;
import org.merkulov.payload.response.RoleResponse;
import org.merkulov.repository.RoleRepository;
import org.merkulov.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final UserRepository userRepository;

public RoleDTO getUsersByRoles(){
    List<User> userList = userRepository.findAll();
    RoleDTO roleDTO = new RoleDTO();

    Map<String,Integer> roleMap = userList.stream()
            .flatMap(user->user.getRoles().stream())
                    .collect(Collectors.groupingBy(Role::getName,Collectors.summingInt(r->1)));
    roleDTO.setRoleMap(roleMap);
return roleDTO;
}


}
