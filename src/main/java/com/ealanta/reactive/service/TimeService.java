package com.ealanta.reactive.service;

import com.ealanta.reactive.dto.TimeInfo;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.Clock;

@Service
public class TimeService {
    final Clock clock;

    public TimeService(Clock clock) {
        this.clock = clock;
    }

    public Mono<TimeInfo> getTime() {
        return Mono.fromCallable(() -> new TimeInfo(clock.instant()));
    }
}
