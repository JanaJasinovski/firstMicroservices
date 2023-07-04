import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Commentar } from '../models/Commentar';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

const BASE_URL_COMMENTARS = environment.BASE_URL_COMMENTAR + '/commentars';

@Injectable({
  providedIn: 'root'
})
export class CommentarService {

  constructor(private http: HttpClient) { }
  

  public createCommentar(commentar: string, id: number) : Observable<any> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(`${BASE_URL_COMMENTARS}/create?commentar=${commentar}&productId=${id}`, { headers: headers});

  }

  public getCommentars(id: number)  : Observable<Commentar[]>{
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get<Commentar[]>( `${BASE_URL_COMMENTARS}/all?productId=${id}`, { headers: headers });

  }
}
