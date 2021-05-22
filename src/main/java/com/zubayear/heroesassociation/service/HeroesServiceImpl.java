package com.zubayear.heroesassociation.service;

import com.zubayear.heroesassociation.model.Hero;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class HeroesServiceImpl implements HeroesService{
    private final ReactiveMongoOperations reactiveMongoOperations;

    public HeroesServiceImpl(ReactiveMongoOperations reactiveMongoOperations) {
        this.reactiveMongoOperations = reactiveMongoOperations;
    }

    @Override
    public Mono<Hero> getHero(String id) {
        return reactiveMongoOperations.findById(id, Hero.class);
    }

    @Override
    public Mono<Hero> createHero(Mono<Hero> hero) {
        return reactiveMongoOperations.save(hero);
    }

    @Override
    public Mono<Hero> updateHero(String id, Mono<Hero> hero) {
        return hero.flatMap(h -> reactiveMongoOperations
                .findAndModify(Query.query(Criteria.where("id").is(id)), Update.update("name", h.getName()), Hero.class)
                .flatMap(result -> {
                    result.setName(h.getName());
                    return Mono.just(result);
                }));
    }

    @Override
    public Mono<Boolean> removeHero(String id) {
        return reactiveMongoOperations.remove(Query.query(Criteria.where("id").is(id)), Hero.class)
                .flatMap(deleteResult -> Mono.just(deleteResult.wasAcknowledged()));
    }

    @Override
    public Flux<Hero> getAllHeroes() {
        return reactiveMongoOperations.findAll(Hero.class);
    }
}
