package org.merkulov.payload.DTO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.merkulov.modell.entity.Comment;
import org.merkulov.modell.entity.User;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PostDTO {

    private Long id;
    private String title;
    private String description;
    private String content;

     private User user;

    private Set<Comment> comments = new HashSet<>();

}
