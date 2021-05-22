package com.zubayear.heroesassociation.controller;

import com.zubayear.heroesassociation.model.Hero;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static com.zubayear.heroesassociation.controller.HeroesController.HERO_V_1;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HeroesControllerTest {

    @Autowired
    private ApplicationContext applicationContext;
    private WebTestClient webTestClient;
    private Hero hero;

    @BeforeEach
    void setUp() {
        webTestClient = WebTestClient
                .bindToApplicationContext(this.applicationContext)
                .build();
        hero = new Hero("Saitama", 'B', 5, LocalDate.now());
    }

    @Test
    void createHero() {
        webTestClient.post()
                .uri(HERO_V_1)
                .body(Mono.just(hero), Hero.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Hero.class);
    }

    @Test
    void getAllHeroes() {
        webTestClient.get()
                .uri(HERO_V_1)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Hero.class);
    }
}