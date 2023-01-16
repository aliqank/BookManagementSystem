package com.bookmanagement.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "USER_SEQUENCE")
    private Long id;

    @Column(name = "user_name")
    private String username;

    private String password;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String email;

    private Integer age;

    private Integer accessLevel;

    @Enumerated(EnumType.STRING)
    private Role role;
}
