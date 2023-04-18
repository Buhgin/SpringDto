package org.merkulov.controller;

import lombok.RequiredArgsConstructor;
import org.merkulov.payload.DTO.LoginDTO;
import org.merkulov.payload.DTO.UserDTO;
import org.merkulov.service.AuthService;
import org.merkulov.service.autorization.JWT.JWTAuthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth/")
@RequiredArgsConstructor
public class AuthController {
private final AuthService authService;

    @PostMapping(value = {"/login", "/signin"})
public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDTO loginDTO){
    String token =  authService.login(loginDTO);
        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);


return ResponseEntity.ok(jwtAuthResponse);}

  @PostMapping(value = {"/registration"})
public ResponseEntity<String> registration(@RequestBody UserDTO userDTO){
    String response =  authService.registration(userDTO);
return ResponseEntity.ok(response);}


}
