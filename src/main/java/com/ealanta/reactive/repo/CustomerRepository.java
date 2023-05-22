package com.ealanta.reactive.repo;

import com.ealanta.reactive.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Long>
{
    Flux<Customer> findByFirst(String first);
    Flux<Customer> findByLast(String first);
}
