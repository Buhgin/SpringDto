package org.merkulov.service;

import org.merkulov.payload.DTO.RoleDTO;
import org.merkulov.payload.response.RoleResponse;

public interface RoleService {
    RoleDTO createRole( RoleDTO roleDTO);

    RoleDTO updateRole(Long userId,Long roleId, RoleDTO roleDTO);

    RoleResponse getRoles( int pageNo, int pageSize, String sortBy, String sortDir);
    RoleResponse getUsersByRoleId(Long roleId, int pageNo, int pageSize, String sortBy, String sortDir);

    void deleteComment(Long id);
}
