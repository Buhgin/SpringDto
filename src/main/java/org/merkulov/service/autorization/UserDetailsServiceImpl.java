package org.merkulov.service.autorization;



import lombok.RequiredArgsConstructor;
import org.merkulov.modell.entity.Role;
import org.merkulov.modell.entity.User;
import org.merkulov.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(loginOrEmail, loginOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: "
                        + loginOrEmail));


        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return  new org.springframework.security.core.userdetails.User(user.getEmail()
                , user.getPassword()
                , grantedAuthorities);

    }

    }