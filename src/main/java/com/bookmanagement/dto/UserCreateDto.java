package com.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private Integer age;
    private Integer accessLevel;
    private String password;
}
