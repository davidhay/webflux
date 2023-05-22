package com.ealanta.reactive.controller;

import com.ealanta.reactive.entity.Customer;
import com.ealanta.reactive.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/customers")
    public Flux<Customer> customers() {
        return service.findAll();
    }

    @GetMapping(value = "/customers", params = "first")
    public Flux<Customer> customersByFirst(@RequestParam String first) {
        return service.findByFirst(first);
    }

    @GetMapping(value = "/customers", params ="last")
    public Flux<Customer> customersByLast(@RequestParam String last) {
        return service.findByLast(last);
    }

    @GetMapping("/customers/{id}")
    public Mono<Customer> customerById(@PathVariable Long id) {
        return service.findById(id);
    }

}
