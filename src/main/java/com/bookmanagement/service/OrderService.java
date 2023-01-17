package com.bookmanagement.service;

import com.bookmanagement.dto.OrderDto;
import com.bookmanagement.mapper.OrderMapper;
import com.bookmanagement.repository.BookRepository;
import com.bookmanagement.repository.OrderRepository;
import com.bookmanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public List<OrderDto> findAll(){
        return orderRepository.findAll().stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public Optional<OrderDto> findById(Long id){
        return orderRepository.findById(id)
                .map(orderMapper::toDto);
    }

    @Transactional
    public OrderDto create(OrderDto orderDto) {
        return Optional.of(orderDto)
                .map((OrderDto orderDto1) -> orderMapper.toEntity(orderDto1,bookRepository,userRepository))
                .map(orderRepository::save)
                .map(orderMapper::toDto)
                .orElseThrow();

    }

    @Transactional
    public Optional<OrderDto> update(Long id, OrderDto orderDto){
        return orderRepository.findById(id)
                .map(orderRepository::saveAndFlush)
                .map(orderMapper::toDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return orderRepository.findById(id)
                .map(entity -> {
                    orderRepository.delete(entity);
                    orderRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
