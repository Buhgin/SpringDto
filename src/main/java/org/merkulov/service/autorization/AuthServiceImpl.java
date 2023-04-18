package org.merkulov.service.autorization;

import lombok.RequiredArgsConstructor;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.Role;
import org.merkulov.modell.entity.User;
import org.merkulov.payload.DTO.CommentDTO;
import org.merkulov.payload.DTO.LoginDTO;
import org.merkulov.payload.DTO.UserDTO;
import org.merkulov.repository.RoleRepository;
import org.merkulov.repository.UserRepository;
import org.merkulov.service.AuthService;
import org.merkulov.service.autorization.JWT.JwtTokenProvider;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public String login(LoginDTO loginDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsernameOrEmail());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              userDetails, loginDto.getPassword(),userDetails.getAuthorities()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);


       return token;
    }

    @Override
    public String registration(UserDTO userDTO) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Role> role = roleRepository.findByName(userDTO.getRole());
        User registrUser = new User();
        registrUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        registrUser.setName(userDTO.getName());
        registrUser.setEmail(userDTO.getEmail());
        registrUser.setUsername(userDTO.getUsername());
        registrUser.setRoles(role.stream().collect(Collectors.toSet()));
          userRepository.save(registrUser);
        return "User successfully registered ";
    }



}