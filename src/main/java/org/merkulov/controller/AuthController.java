package org.merkulov.controller;

import lombok.RequiredArgsConstructor;
import org.merkulov.payload.DTO.LoginDTO;
import org.merkulov.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
private final AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO){
    String response =  authService.login(loginDTO);
return ResponseEntity.ok(response);}


}
