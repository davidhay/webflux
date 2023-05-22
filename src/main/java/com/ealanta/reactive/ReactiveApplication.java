package com.ealanta.reactive;

import com.ealanta.reactive.entity.Customer;
import com.ealanta.reactive.service.CustomerService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.jdbc.core.RowMapper;

import java.time.Clock;

@SpringBootApplication
@EnableR2dbcRepositories
public class ReactiveApplication {

    public static final RowMapper<Customer> MAPPER = (rs, rowNum) -> new Customer(rs.getLong("id"), rs.getString("first"), rs.getString("last"));

    public static void main(String[] args) {
        SpringApplication.run(ReactiveApplication.class, args);
    }

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    ApplicationRunner runner(CustomerService service) {
        return args -> service.findAll().subscribe(System.out::println);
    }

}
