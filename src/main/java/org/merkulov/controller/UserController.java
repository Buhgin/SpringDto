package org.merkulov.controller;

import lombok.RequiredArgsConstructor;
import org.merkulov.payload.DTO.PostDTO;
import org.merkulov.payload.DTO.UserDTO;
import org.merkulov.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
@Autowired
    private  UserService userService;
    @PostMapping("roles/{id}")
    public ResponseEntity<UserDTO> createPost(@PathVariable(value = "id") Long roleId,
                                              @Valid @RequestBody UserDTO userDTO) {
        UserDTO user = userService.createUser(userDTO,roleId);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
