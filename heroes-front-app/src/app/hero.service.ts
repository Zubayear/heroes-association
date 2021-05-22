import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HeroService {

  constructor(private httpClient: HttpClient) {
  }

  private baseUrl = 'http://localhost:8080';
  private heroUrl = this.baseUrl + '/hero/v1/';
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  createHeroes(body: HeroesRequest): Observable<Hero> {
    return this.httpClient.post<Hero>(this.heroUrl, body, this.httpOptions);
  }

  getAllHeroes(): Observable<Hero[]> {
    return this.httpClient.get<Hero[]>(this.heroUrl);
  }

  getSpecificHero(heroID: string): Observable<Hero> {
    const url = `${this.heroUrl}${heroID}`;
    return this.httpClient.get<Hero>(url);
  }

  removeAHero(heroID: string) {
    const url = `${this.heroUrl}${heroID}`;
    return this.httpClient.delete(url)
  }

  updateHero(heroID: string, body: HeroesRequest): Observable<Hero> {
    const url = `${this.heroUrl}${heroID}`;
    return this.httpClient.put<Hero>(url, body, this.httpOptions)
  }

}

export class HeroesRequest {
  constructor(public name: string, public className: string, public rank: number, public registerDate: Date) {
  }
}

//this will be the result we get from rest api
export interface Hero {
  name: string,
  className: string,
  rank: number,
  registerDate: Date
}
