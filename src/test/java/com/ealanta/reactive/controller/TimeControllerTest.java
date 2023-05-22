package com.ealanta.reactive.controller;

import com.ealanta.reactive.dto.TimeInfo;
import com.ealanta.reactive.service.TimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TimeControllerTest {

    @Mock
    TimeInfo mTimeInfo1,mTimeInfo2,mTimeInfo3,mTimeInfo4,mTimeInfo5;

    @Mock
    TimeService mService;
    private TimeController controller;

    @BeforeEach
    void setup() {
        this.controller = new TimeController(mService);
    }

    @Test
    void testGetTimeMono() {
        Mono<TimeInfo> fromService = Mono.just(mTimeInfo1);
        when(mService.getTime()).thenReturn(fromService);

        Mono<TimeInfo> result = controller.getTime();
        assertEquals(result, fromService);
    }


    @Test
    void testGetTimesFlux() {
        //the mock timeInfos are all different
        assertEquals(5, Set.of(mTimeInfo1, mTimeInfo2, mTimeInfo3, mTimeInfo4, mTimeInfo5).size());

        Mono<TimeInfo> fromService1 = Mono.just(mTimeInfo1);
        Mono<TimeInfo> fromService2 = Mono.just(mTimeInfo2);
        Mono<TimeInfo> fromService3 = Mono.just(mTimeInfo3);
        Mono<TimeInfo> fromService4 = Mono.just(mTimeInfo4);
        Mono<TimeInfo> fromService5 = Mono.just(mTimeInfo5);

        when(mService.getTime()).thenReturn(fromService1, fromService2, fromService3, fromService4, fromService5);

        Flux<TimeInfo> results = controller.getTimes().take(5);
        StepVerifier.create(results)
                .assertNext(ti -> assertEquals(ti, mTimeInfo1))
                .assertNext(ti -> assertEquals(ti, mTimeInfo2))
                .assertNext(ti -> assertEquals(ti, mTimeInfo3))
                .assertNext(ti -> assertEquals(ti, mTimeInfo4))
                .assertNext(ti -> assertEquals(ti, mTimeInfo5))
                .verifyComplete();
    }
}
