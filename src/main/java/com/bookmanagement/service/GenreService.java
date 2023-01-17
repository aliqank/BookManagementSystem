package com.bookmanagement.service;

import com.bookmanagement.dto.GenreDto;
import com.bookmanagement.mapper.GenreMapper;
import com.bookmanagement.repository.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public List<GenreDto> findAll(){
        return genreRepository.findAll().stream()
                .map(genreMapper::toDto)
                .toList();
    }

    public Optional<GenreDto> findById(Long id){
        return genreRepository.findById(id)
                .map(genreMapper::toDto);
    }

    @Transactional
    public GenreDto create(GenreDto genreDto) {
        return Optional.of(genreDto)
                .map(genreMapper::toEntity)
                .map(genreRepository::save)
                .map(genreMapper::toDto)
                .orElseThrow();

    }

    @Transactional
    public Optional<GenreDto> update(Long id, GenreDto genreDto){
        return genreRepository.findById(id)
                .map(genreRepository::saveAndFlush)
                .map(genreMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return genreRepository.findById(id)
                .map(entity -> {
                    genreRepository.delete(entity);
                    genreRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
