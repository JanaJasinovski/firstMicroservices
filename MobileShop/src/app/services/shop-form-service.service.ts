import { Injectable } from '@angular/core';
import { Country } from '../models/country';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { State } from '../models/state';

@Injectable({
  providedIn: 'root'
})
export class ShopFormServiceService {

  private countriesUrl = environment.BASE_URL_STATES + '/countries';
  private statesUrl = environment.BASE_URL_STATES + '/states';

  constructor(private httpClient: HttpClient) { }

  getCountries(): Observable<Country[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<Country[]>(this.countriesUrl+ "/all", { headers: headers });
  }

  getStates(theCountryCode: string): Observable<State[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    const searchStatesUrl = `${this.statesUrl}/search/findByCountryCode?code=${theCountryCode}`;

    return this.httpClient.get<State[]>(searchStatesUrl, { headers: headers });
  }


  getCreditCardMonths(startMonth: number): Observable<number[]> {

    let data: number[] = [];

    for (let theMonth = startMonth; theMonth <= 12; theMonth++) {
      data.push(theMonth);
    }

    return of(data);
  }

  getCreditCardYears(): Observable<number[]> {

    let data: number[] = [];

    const startYear: number = new Date().getFullYear();
    const endYear: number = startYear + 10;

    for (let theYear = startYear; theYear <= endYear; theYear++) {
      data.push(theYear);
    }

    return of(data);
  }

}
