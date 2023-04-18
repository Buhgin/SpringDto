package org.merkulov.payload.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.Post;
import org.merkulov.modell.entity.Role;


import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private  Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;

    private Set<String> setRole ;
}
