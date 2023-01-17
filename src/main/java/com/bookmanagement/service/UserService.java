package com.bookmanagement.service;

import com.bookmanagement.dto.UserCreateDto;
import com.bookmanagement.dto.UserDto;
import com.bookmanagement.entity.Role;
import com.bookmanagement.entity.User;
import com.bookmanagement.mapper.UserCreateMapper;
import com.bookmanagement.mapper.UserMapper;
import com.bookmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserCreateMapper userCreateMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDto> findAll(){
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    public Optional<UserDto> findById(Long id){
        return userRepository.findById(id)
                .map(userMapper::toDto);
    }

    @Transactional
    public UserDto create(UserCreateDto userCreateDto) {
        return Optional.of(userCreateDto)
                .map(userCreateDto1 -> {
                    Optional.of(userCreateDto.getPassword())
                            .filter(StringUtils::hasText)
                            .map(passwordEncoder::encode)
                            .ifPresent(userCreateDto1::setPassword);
                    return userCreateMapper.toEntity(userCreateDto1);
                })
                .map(entity -> {
                    entity.setRole(Role.USER);
                    return userRepository.save(entity);
                })
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserDto> update(Long id, UserDto userDto){
        return userRepository.findById(id)
                .map(userRepository::saveAndFlush)
                .map(userMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return userRepository.findById(id)
                .map(entity -> {
                    userRepository.delete(entity);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singleton(user.getRole())
                ))
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user: " + username));
    }

    public Optional<String> getCurrentUser() {
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .filter(authentication -> !(authentication instanceof AnonymousAuthenticationToken))
                .map(Authentication::getName);
    }




}
