import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { State } from '../models/state';
import { Address } from '../models/address';
import { PaymentInfo } from '../models/payment-info';

const BASE_URL_STATE = environment.BASE_URL_STATES + '/states';
const BASE_URL_ADRESSES = environment.BASE_URL_STATES + '/addresses';
const BASE_AGREGATOR_URL = environment.AGREGATOR_URL + '/payments/process';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private httpClient: HttpClient) { }

  getStates(): Observable<State[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<State[]>(BASE_URL_STATE + "/all", { headers: headers });
  }

  createAddress(address: Address) {
    var headers = new HttpHeaders().set('Content-Type', 'application/json');

    return this.httpClient.post(BASE_URL_ADRESSES + '/create', address,  { headers, responseType: 'text' as 'json' });
  }

  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any> {
    return this.httpClient.post<PaymentInfo>(BASE_AGREGATOR_URL, paymentInfo);
  }
}
