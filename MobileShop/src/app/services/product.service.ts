import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Product } from '../models/product';
import { ProductCategory } from '../models/product-category';

const BASE_URL_PR = environment.BASE_URL_PRODUCT + '/products';
const CATEGORY_URL = environment.BASE_URL_CATEGORY + '/category';
const SEARCH_URL = environment.BASE_URL_SEARCH + '/searches';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  getProductList(theCategoryId: number): Observable<Product[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<Product[]>(BASE_URL_PR + "/search/findByCategoryId?id=" + theCategoryId, { headers: headers })
  }

  searchProducts(theKeyword: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(SEARCH_URL + "/search/findByNameContaining?name=" + theKeyword, { withCredentials: true })
  }

  searchProductsPriceBetween(startPrice: number, endPrice: number): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${SEARCH_URL}/search/priceBetween?startPrice=${startPrice}&endPrice=${endPrice}`
                                        , { withCredentials: true })
  }


  getProductCategories(): Observable<ProductCategory[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<ProductCategory[]>(CATEGORY_URL + "/allCategories", { headers: headers });
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(searchUrl, { withCredentials: true });
  }

  getProduct(theProductId: number): Observable<Product> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<Product>(BASE_URL_PR + "/product/id/" + theProductId, { headers: headers });
  }

  searchProductsPaginateBetween(thePage: number,
    thePageSize: number,
    minPrice: string,
    maxPrice: string): Observable<Product[]> {

    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<Product[]>(SEARCH_URL + "/priceBetween?startPrice="
      + minPrice + "&endPrice="+ maxPrice + "&page=" + thePage + "&size=" + thePageSize, { headers: headers });
  }

  searchProductsPaginate(thePage: number,
    thePageSize: number,
    theKeyword: string): Observable<Product[]> {

    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.get<Product[]>(SEARCH_URL + "/search/findByNameContaining?name="
      + theKeyword + "&page=" + thePage + "&size=" + thePageSize, { headers: headers });
  }

  getProductListPaginate(thePage: number,
    thePageSize: number,
    theCategoryId: number): Observable<GetResponseProducts> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.httpClient.get<GetResponseProducts>(
      `${BASE_URL_PR}/search/findByCategoryId?id=${theCategoryId}&page=${thePage}&size=${thePageSize}`, { headers: headers })

  }

  deleteProduct(id: number): Observable<Product[]> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.delete<Product[]>(`${BASE_URL_PR}/delete/product/userId?productId=${id}`, { headers: headers })
  }

  createProduct(product: Product): Observable<any> {
    const token = localStorage.getItem('currentUser')!;
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.httpClient.post(BASE_URL_PR + '/create', product, { headers: headers });
  }


}

interface GetResponseProducts {
  _embedded: {
    products: Product[];
  },
  page: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}