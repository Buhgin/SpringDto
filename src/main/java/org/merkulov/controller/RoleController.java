package org.merkulov.controller;

import lombok.RequiredArgsConstructor;
import org.merkulov.payload.DTO.RoleDTO;
import org.merkulov.payload.DTO.UserDTO;
import org.merkulov.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {

    private RoleService roleService;
    @PostMapping
    public ResponseEntity<RoleDTO> createPost(@RequestBody RoleDTO roleDTO) {
        RoleDTO role= roleService.createRole(roleDTO);

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }
}
