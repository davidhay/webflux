package com.ealanta.reactive.client;

import com.ealanta.reactive.config.DatabaseConfig;
import com.ealanta.reactive.entity.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Import(DatabaseConfig.class)
public class CustomerControllerIT extends BaseIT {

    private static final Customer NEIL =new Customer(1L,"Neil","Armstrong");
    private static final Customer BUZZ =new Customer(2L,"Buzz","Aldrin");
    private static final Customer MIKE =new Customer(3L,"Michael","Collins");

    @Autowired
    WebTestClient client;

    @Test
    void testCustomers(){
       Set<Customer> customers = client.get()
                        .uri("/customers")
                        .exchange()
                        .expectStatus().isOk()
                        .returnResult(Customer.class).getResponseBody()
               .toStream().collect(Collectors.toSet());

       assertEquals(3, customers.size());
       assertTrue(customers.contains(NEIL));
       assertTrue(customers.contains(BUZZ));
       assertTrue(customers.contains(MIKE));
    }

    @Test
    void testFindById(){
        StepVerifier.create(client.get()
                .uri("/customers/1")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Customer.class).getResponseBody().single())
                .expectNext(NEIL)
                .verifyComplete();
    }
    @Test
    void testFindByFirst(){
        StepVerifier.create(client.get()
                .uri("/customers?first=Buzz")
                .exchange()
                .expectStatus().isOk()
                .returnResult(Customer.class).getResponseBody().single())
                .expectNext(BUZZ)
                .verifyComplete();
    }

    @Test
    void testFindByLast() {
        StepVerifier.create(client.get()
                        .uri("/customers?last=Collins")
                        .exchange()
                        .expectStatus().isOk()
                        .returnResult(Customer.class).getResponseBody().single())
                .expectNext(MIKE)
                .verifyComplete();
    }
}
