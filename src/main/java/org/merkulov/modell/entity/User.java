package org.merkulov.modell.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;
import org.merkulov.enume.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;
 private String name;
 @Column(nullable = false, unique = true)
 private String username;
 @Column(nullable = false, unique = true)
 private String email;
 @JsonIgnore
 @Column(nullable = false)
 private String password;

/* @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 @ToString.Exclude

 private Set<Post> posts = new HashSet<>();

 @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
 @ToString.Exclude
 private List<Comment> comments;*/


 @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
 @JoinTable(name = "users_roles",
         joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
         inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
 )
 private Set<Role> roles;

}

