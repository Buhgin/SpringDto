package org.merkulov.controller;

import lombok.RequiredArgsConstructor;
import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.DTO.RoleDTO;
import org.merkulov.service.impl.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
public class RoleController {
    private final RoleService roleService;
    @GetMapping()
    public ResponseEntity<RoleDTO> getTheNumberOfUsersByRole(){
        RoleDTO roleDTO = roleService.getUsersByRoles();

    return new ResponseEntity<>(roleDTO, HttpStatus.OK);}
}
