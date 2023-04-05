package org.merkulov.payload.DTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.Post;
import org.merkulov.modell.entity.Role;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class UserDTO {
    private Long id;
    private String login;
    private String password;
    private Set<Role> roles = new HashSet<>();
    private Set<Post> posts = new HashSet<>();

    private List<Comment> comments;

}
