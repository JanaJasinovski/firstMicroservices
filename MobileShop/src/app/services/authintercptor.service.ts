import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import {Router} from "@angular/router";


@Injectable()
export class JwtInterceptor implements HttpInterceptor {

  constructor(private http: HttpClient, private router: Router) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('currentUser');
    if (token) {
        
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${token.replaceAll(`\"`, '')}`
        }
      });
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 401) {
            this.router.navigate(['/login']);
        }
        return throwError(error);
      })
    );
  }

}
