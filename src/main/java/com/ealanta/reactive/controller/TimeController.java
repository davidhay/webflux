package com.ealanta.reactive.controller;

import com.ealanta.reactive.dto.TimeInfo;
import com.ealanta.reactive.service.TimeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class TimeController {

    private final TimeService timeService;

    private Flux<Long> ticker = Flux.interval(Duration.ofSeconds(1));

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/time")
    public Mono<TimeInfo> getTime() {
        return timeService.getTime();
    }

    @GetMapping(path = "/times", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TimeInfo> getTimes() {
        return ticker.flatMap(e -> timeService.getTime());
    }
}
