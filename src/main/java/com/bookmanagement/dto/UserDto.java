package com.bookmanagement.dto;

import com.bookmanagement.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Integer accessLevel;
    private Role roles;
}
