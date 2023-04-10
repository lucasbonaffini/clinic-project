package com.project.clinic.login;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
@ToString
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    private Integer id;

    @Setter
    private String username;

    @Setter
    private String email;

    @Setter
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="UserRolls",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="rol_id")
    )
    private Set<Rol> rolls;
}
