import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Order } from '../models/order';
import { CartItem } from '../models/cart-item';


const BASE_URL_ORDERS = environment.BASE_URL_ORDER + '/order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }
  

  public order() : Observable<Order> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post(`${BASE_URL_ORDERS}/create`, { headers: headers});

  }

  public getOrder(): Observable<Order[]> {
    const token = localStorage.getItem('currentUser')!;
    console.log(token);
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<Order[]>(BASE_URL_ORDERS + "/getOrders", { headers: headers });
  }

  public getCartItems(id: string): Observable<CartItem[]> {
    const token = localStorage.getItem('currentUser')!;
    console.log(token);
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<CartItem[]>(`${BASE_URL_ORDERS}/getCartItems?cartId=${id}`, { headers: headers });
  }

}
