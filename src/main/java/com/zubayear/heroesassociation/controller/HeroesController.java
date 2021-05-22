package com.zubayear.heroesassociation.controller;

import com.zubayear.heroesassociation.model.Hero;
import com.zubayear.heroesassociation.service.HeroesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = HeroesController.HERO_V_1)
@CrossOrigin
public class HeroesController {
    public static final String HERO_V_1 = "/hero/v1/";

    private final HeroesService heroesService;

    public HeroesController(HeroesService heroesService) {
        this.heroesService = heroesService;
    }

    @GetMapping(path = "{heroID}")
    public Mono<Hero> getHeroById(@PathVariable String heroID) {
        return heroesService.getHero(heroID);
    }

    @PostMapping(value = "")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Hero> createHero(@RequestBody Mono<Hero> hero) {
        return heroesService.createHero(hero);
    }

    @PutMapping(path = "{heroID}")
    public Mono<Hero> updateHero(@PathVariable String heroID, @RequestBody Mono<Hero> hero) {
        return heroesService.updateHero(heroID, hero);
    }

    @DeleteMapping(path = "{heroID}")
    public Mono<Boolean> removeHero(@PathVariable String heroID) {
        return heroesService.removeHero(heroID);
    }

    @GetMapping(value = "")
    public Flux<Hero> getAllHeroes() {
        return heroesService.getAllHeroes();
    }
}
