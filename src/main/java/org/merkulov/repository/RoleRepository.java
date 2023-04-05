package org.merkulov.repository;

import org.merkulov.modell.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    //List<Role> findByuserRole(String name);
}
