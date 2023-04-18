package org.merkulov.payload.DTO;
import lombok.*;
import org.merkulov.modell.entity.Post;
import org.merkulov.modell.entity.User;
import javax.validation.constraints.Email;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;

    private String name;
    @Email
    private String email;

    private String body;

    private Post post;

    private User user;


}
