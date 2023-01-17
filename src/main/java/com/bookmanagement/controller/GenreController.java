package com.bookmanagement.controller;

import com.bookmanagement.dto.GenreDto;
import com.bookmanagement.service.GenreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/genres")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping()
    public List<GenreDto> findAll() {
       return genreService.findAll();
    }

    @GetMapping("/{id}")
    public GenreDto findById(@PathVariable("id") Long id) {
        return genreService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public GenreDto create(@RequestBody GenreDto genreDto) {
        return genreService.create(genreDto);
    }

    @PutMapping("/{id}")
    public GenreDto update(@PathVariable("id") Long id,
                              @RequestBody GenreDto genreDto) {
        return genreService.update(id, genreDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return genreService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
