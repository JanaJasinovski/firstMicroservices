import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";
import { User } from "../models/user";
import { BehaviorSubject, catchError, map, Observable, tap } from "rxjs";
import { HttpClient, HttpHeaders, HttpResponse } from "@angular/common/http";
import { JwtHelperService } from '@auth0/angular-jwt';

const API_URL = environment.BASE_URL + '/api/authentication';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public currentUser: Observable<User>;
  private currentUserSubject: BehaviorSubject<User>;

  constructor(private http: HttpClient) {
    let storageUser;
    const storageUserAsStr = localStorage.getItem('currentUser');

    if (storageUserAsStr) {
      storageUser = JSON.parse(storageUserAsStr);
    }

    this.currentUserSubject = new BehaviorSubject<User>(storageUser);
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  
  public login(user: User): Observable<any>  {    
    var headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post<any>(API_URL + '/signin', user, { headers, responseType: 'text' as 'json' }).pipe(
      map(response => {
        if (response) {   
          // user = this.parseToken(response)
          this.setSessionUser(response);
        }
        return response;
      })
    );
  }

  getToken(): string {
    return localStorage.getItem('currentUser')!;
  }

  parseToken(myRawToken: string) : User {
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(myRawToken);
    return decodedToken;    
  } 

  setSessionUser(user: User) {
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
  }

  logOut() {
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(new User);
  }

  register(user: User): Observable<any> {
    var headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.http.post(API_URL + '/signup', user, { headers, responseType: 'text' as 'json' });
  }

}
