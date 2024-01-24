package com.exmaple.project.JWT.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;


@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    int id;
    String email;
    String password;

//    @Lob
//    @Column(length = 500)
//    private byte[] profilePicture;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

}