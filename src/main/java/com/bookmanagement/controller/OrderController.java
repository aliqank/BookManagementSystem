package com.bookmanagement.controller;

import com.bookmanagement.dto.OrderDto;
import com.bookmanagement.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.notFound;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping()
    public List<OrderDto> findAll() {
       return orderService.findAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public OrderDto findById(@PathVariable("id") Long id) {
        return orderService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PostMapping()
    public OrderDto create(@RequestBody OrderDto orderDto) {
        return orderService.create(orderDto);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    @PutMapping("/{id}")
    public OrderDto update(@PathVariable("id") Long id,
                              @RequestBody OrderDto orderDto) {
        return orderService.update(id, orderDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        return orderService.delete(id)
                ? noContent().build()
                : notFound().build();
    }

}
