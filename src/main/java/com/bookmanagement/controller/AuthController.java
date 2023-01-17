package com.bookmanagement.controller;

import com.bookmanagement.dto.UserCreateDto;
import com.bookmanagement.dto.UserDto;
import com.bookmanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/create")
    public UserDto create(@RequestBody UserCreateDto userDto){
        return userService.create(userDto);
    }

}
