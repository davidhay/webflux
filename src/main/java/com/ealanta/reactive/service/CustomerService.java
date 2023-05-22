package com.ealanta.reactive.service;

import com.ealanta.reactive.entity.Customer;
import com.ealanta.reactive.repo.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repo;

    public Flux<Customer> findAll(){
        return repo.findAll();
    }

    public Mono<Customer> findById(Long id){
        return repo.findById(id);
    }

    public Flux<Customer> findByFirst(String first){
        return repo.findByFirst(first);
    }
}
