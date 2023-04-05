package org.merkulov.payload.DTO;

import lombok.Data;
import org.merkulov.enume.UserRole;
import org.merkulov.modell.entity.User;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDTO {

    private Long id;

    private UserRole role;

    private Set<User> users = new HashSet<>();
}
