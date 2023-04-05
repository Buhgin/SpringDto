package org.merkulov.payload.DTO;

import lombok.Data;
import org.merkulov.modell.entity.Post;
import org.merkulov.modell.entity.User;

import javax.validation.constraints.Email;

@Data
public class CommentDTO {
    private Long id;

    private String name;
    @Email
    private String email;

    private String body;

    private Post post;
    private User user;
}
