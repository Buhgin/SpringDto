package org.merkulov.payload.DTO;

import lombok.Data;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.User;

import java.util.HashSet;
import java.util.Set;

@Data
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private String content;
    private Set<Comment> comments = new HashSet<>();
    private User user;
}
