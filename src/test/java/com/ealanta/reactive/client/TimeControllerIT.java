package com.ealanta.reactive.client;

import com.ealanta.reactive.dto.TimeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.test.StepVerifier;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static org.junit.Assert.assertTrue;

public class TimeControllerIT extends BaseIT {

    static final Instant INSTANT = Instant.now();

    @Autowired
    WebTestClient client;

    @Test
    void testGetTime() {
        StepVerifier.create(client.get()
                .uri("/time")
                .exchange()
                        .expectStatus().isOk()
                        .returnResult(TimeInfo.class).getResponseBody().single())
        .expectNext(new TimeInfo(INSTANT)).verifyComplete();
    }

    @Test
    void testGetTimes() {
        long start = System.currentTimeMillis();
        StepVerifier.create(client.get()
                        .uri("/times")
                        .exchange()
                        .expectStatus().isOk()
                        .returnResult(TimeInfo.class).getResponseBody().take(5))
                .expectNext(new TimeInfo(INSTANT))
                .expectNext(new TimeInfo(INSTANT))
                .expectNext(new TimeInfo(INSTANT))
                .expectNext(new TimeInfo(INSTANT))
                .expectNext(new TimeInfo(INSTANT))
                .verifyComplete();
        long end = System.currentTimeMillis();
        assertTrue(end-start >= 5000);
    }


    @TestConfiguration
    static class TestConfig {
        @Bean
        @Primary
        Clock clock() {
            return Clock.fixed(INSTANT, ZoneId.systemDefault());
        }
    }
}
