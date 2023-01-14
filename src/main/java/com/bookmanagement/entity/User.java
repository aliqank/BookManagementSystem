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

    private String userName;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;

    private Integer accessLevel;

    @Enumerated(EnumType.STRING)
    private Role role;
}
