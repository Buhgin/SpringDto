package org.merkulov.service.impl;

import lombok.RequiredArgsConstructor;
import org.merkulov.modell.entity.Post;
import org.merkulov.modell.entity.Role;
import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.DTO.RoleDTO;
import org.merkulov.payload.response.RoleResponse;
import org.merkulov.repository.RoleRepository;
import org.merkulov.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceimpl implements RoleService {

    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
       return mapToDTO( roleRepository.save(mapToEntity(roleDTO)));

    }

    @Override
    public RoleDTO updateRole(Long userId, Long roleId, RoleDTO roleDTO) {
        return null;
    }

    @Override
    public RoleResponse getRoles(int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public RoleResponse getUsersByRoleId(Long roleId, int pageNo, int pageSize, String sortBy, String sortDir) {
        return null;
    }

    @Override
    public void deleteComment(Long id) {


    }

    private Role mapToEntity(RoleDTO roleDTO) {
        return modelMapper.map(roleDTO, Role.class);
    }

    private RoleDTO mapToDTO(Role newRole) {return modelMapper.map(newRole, RoleDTO.class);
    }
}
