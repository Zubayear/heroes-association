package com.zubayear.heroesassociation.service;

import com.zubayear.heroesassociation.model.Hero;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface HeroesService {
    Mono<Hero> getHero(String id);
    Mono<Hero> createHero(Mono<Hero> hero);
    Mono<Hero> updateHero(String id, Mono<Hero> hero);
    Mono<Boolean> removeHero(String id);
    Flux<Hero> getAllHeroes();
}
