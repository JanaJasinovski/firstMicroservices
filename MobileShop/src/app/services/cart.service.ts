import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { CartItem } from '../models/cart-item';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Cart } from '../models/cart';

const BASE_URL_CART = environment.BASE_URL_CART + '/carts';
const BASE_URL_CART_ITEM = environment.BASE_URL_CART + '/cartItems';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor(private http: HttpClient) { }

  addToCart(name: string) : Observable<any> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    
    return this.http.post(`${BASE_URL_CART}/cart/create?name=${name}`, { headers: headers});
  }

  public getCart(): Observable<CartItem[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<CartItem[]>(BASE_URL_CART_ITEM + "/cartItem/userId", { headers: headers });
  }

  public getCartN(): Observable<Cart> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<Cart>(BASE_URL_CART + "/cart/userId", { headers: headers });
  }

  computeCartTotals() {

    let totalPriceValue: number = 0;
    let totalQuantityValue: number = 0;

    for (let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.amount * currentCartItem.productPrice;
      totalQuantityValue += currentCartItem.amount;
    }

    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }

  incrementAmount(name: string) : Observable<any> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    
    return this.http.post(`${BASE_URL_CART_ITEM}/cartItem/increment?name=${name}`, { headers: headers});
  }


  decrementQuantity(name: string) : Observable<any> {

    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    
    return this.http.post(`${BASE_URL_CART_ITEM}/cartItem/decrement?name=${name}`, { headers: headers});
  }

  public getTotalPrice(): Observable<CartItem[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<CartItem[]>(BASE_URL_CART_ITEM + "/cartItem/totalPrice", { headers: headers });
  }

  public getTotalQuanity2(): Observable<number> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<number>(BASE_URL_CART + "/totalQuaninty", { headers: headers });
  }

  public getTotalPrice2(): Observable<number> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<number>(BASE_URL_CART + "/totalPrice", { headers: headers });
  }

  remove(id: string) : Observable<CartItem[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.delete<CartItem[]>(`${BASE_URL_CART_ITEM}/cartItem?id=${id}`, { headers: headers});
  }

  



}
