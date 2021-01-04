package com.eden.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloWebFluxController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }

    @GetMapping("/hello2")
    public Mono<String> hello2() {
        return Mono.just("Hello");
    }

    @GetMapping("/user")
    public User user() {
        return new User("aaaa", 12);
    }

    @GetMapping("/user2")
    public Mono<User> user2() {
        return Mono.just(new User("aaaa", 12));
    }

    @GetMapping("/user3")
    public Flux<User> user3() {
        return Flux.just(new User("aaaa", 12), new User("bbb", 22));
    }
}
