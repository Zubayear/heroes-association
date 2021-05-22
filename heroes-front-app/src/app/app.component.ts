import { Component } from '@angular/core';
import {HeroesRequest, HeroService} from "./hero.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'heroes-front-app';

  constructor(private heroService: HeroService) {
  }

  ngOnInit() {
    // this.createHero();
  //  Get all heroes
  //   this.getHeroes();
  //  Get Genos
  //   this.getAHero();

  //  Delete Genos
  //   this.removeHero()

  //  update 60a916a76b98f449f8c32887
  //   this.updateHero()
  }

  updateHero() {
    let body = new HeroesRequest("King", "S", 7, new Date("2021-01-09"));
    let heroID = '60a916a76b98f449f8c32887';
    this.heroService.updateHero(heroID, body).subscribe(r => {
      console.log(r)
    })
  }

  createHero() {
    let body = new HeroesRequest("Genos", "S", 7, new Date("2021-01-09"));
    this.heroService.createHeroes(body).subscribe(result => {
      console.log(result.name, result.className, result.rank, result.registerDate);
    });
  }

  getHeroes() {
    this.heroService.getAllHeroes().subscribe(result => {
      console.log(result)
    })
  }

  getAHero() {
    this.heroService.getSpecificHero('60a91cf36b98f449f8c3288a').subscribe(r => {
      console.log(r)
    })
  }

  removeHero() {
    this.heroService.removeAHero('60a91cf36b98f449f8c3288a').subscribe(r => {
      console.log(r)
    })
  }


}
