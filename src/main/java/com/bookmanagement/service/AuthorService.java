package com.bookmanagement.service;

import com.bookmanagement.dto.AuthorDto;
import com.bookmanagement.mapper.AuthorMapper;
import com.bookmanagement.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public List<AuthorDto> findAll(){
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .toList();
    }

    public Optional<AuthorDto> findById(Long id){
        return authorRepository.findById(id)
                .map(authorMapper::toDto);
    }

    @Transactional
    public AuthorDto create(AuthorDto authorDto) {
        return Optional.of(authorDto)
                .map(authorMapper::toEntity)
                .map(authorRepository::save)
                .map(authorMapper::toDto)
                .orElseThrow();

    }

    @Transactional
    public Optional<AuthorDto> update(Long id, AuthorDto authorDto){
        return authorRepository.findById(id)
                .map(authorRepository::saveAndFlush)
                .map(authorMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return authorRepository.findById(id)
                .map(entity -> {
                    authorRepository.delete(entity);
                    authorRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
