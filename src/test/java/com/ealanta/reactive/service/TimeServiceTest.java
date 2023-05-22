package com.ealanta.reactive.service;

import com.ealanta.reactive.dto.TimeInfo;
import com.ealanta.reactive.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Clock;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {

    @Mock
    Clock mClock;

    @Mock
    Instant mInstant;

    private TimeService service;

    @BeforeEach
    void setup() {
        this.service = new TimeService(mClock);
    }

    @Test
    void testGetTime() {
        when(mClock.instant()).thenReturn(mInstant);

        Mono<TimeInfo> result = service.getTime();

        StepVerifier.create(result)
                .assertNext(ti -> assertEquals(ti.time(), mInstant))
                .verifyComplete();

    }
}
