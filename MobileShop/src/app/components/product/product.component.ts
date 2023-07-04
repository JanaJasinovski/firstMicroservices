import {Component, OnInit} from '@angular/core';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import { Subject, takeUntil } from 'rxjs';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CartService } from 'src/app/services/cart.service';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RoleEnum } from 'src/app/models/roleEnum.enum';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  products: Product[] = []
  currentCategoryId: number = 1;
  previousCategoryId: number = 1;
  searchMode: boolean = false;
  searchMode1: boolean = false;
  searchMode2: boolean = false;

  previousKeyword: string = "";
  previousKeyword1: string = "";
  previousKeyword2: string = "";

  public currentUser: User = new User();

  public stars = [
    {
      class: 'fa fa-star checked',
    },
    {
      class: 'fa fa-star checked',
    },
    {
      class: 'fa fa-star checked',
    },
    {
      class: 'fa fa-star checked',
    },
    {
      class: 'fa fa-star checked',
    },
  ]

  private unsubscription = new Subject<void>;

  public thePageNumber: number = 1;
  public thePageSize: number = 5;
  public theTotalElements: number = 0;

  constructor(private productService: ProductService,
    private cartService: CartService,
    private authenticationService: AuthenticationService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
      this.authenticationService.currentUser.subscribe(data => { 
      let data2 = this.authenticationService.parseToken(JSON.stringify(data));         
       this.currentUser = data2;      
     });     

      this.route.paramMap.subscribe(() => {
        this.listProducts();
      });
    }
  

    listProducts() {
      this.searchMode = this.route.snapshot.paramMap.has('keyword');
      this.searchMode1 = this.route.snapshot.paramMap.has('minPrice');
      this.searchMode2 = this.route.snapshot.paramMap.has('maxPrice');

      if (this.searchMode) {
        this.handleSearchProducts();
      }
      else if(this.searchMode1 || this.searchMode2) {
        this.handleSearchProductsBetween();
      }
      else {
        this.handleListProducts();
      }
    }

    handleSearchProductsBetween() {

      const theKeyword1: string = this.route.snapshot.paramMap.get('minPrice')!;
      const theKeyword2: string = this.route.snapshot.paramMap.get('maxPrice')!;

      if (this.previousKeyword1 != theKeyword1 || this.previousKeyword2 != theKeyword2) {
        this.thePageNumber = 1;
      }
  
      this.previousKeyword1 = theKeyword1;
      this.previousKeyword2 = theKeyword2;

      this.productService.searchProductsPaginateBetween(this.thePageNumber - 1,
                                                 this.thePageSize,
                                                 theKeyword1,
                                                 theKeyword2).subscribe(this.processResult());
                                                 
    }

    handleSearchProducts() {

      const theKeyword: string = this.route.snapshot.paramMap.get('keyword')!;
  
      if (this.previousKeyword != theKeyword) {
        this.thePageNumber = 1;
      }
  
      this.previousKeyword = theKeyword;
      this.productService.searchProductsPaginate(this.thePageNumber - 1,
                                                 this.thePageSize,
                                                 theKeyword).subscribe(this.processResult());
                                                 
    }
  
    handleListProducts() {
      const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
  
      if (hasCategoryId) {
        this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
      }
      else {
        this.currentCategoryId = 1;
      }
  
      if (this.previousCategoryId != this.currentCategoryId) {
        this.thePageNumber = 1;
      }
  
      this.previousCategoryId = this.currentCategoryId;  
      this.productService.getProductListPaginate(this.thePageNumber - 1,
                                                 this.thePageSize,
                                                 this.currentCategoryId)
                                                 .subscribe(this.processResult());      
    }

    updatePageSize(pageSize: string) {
      this.thePageSize = +pageSize;
      this.thePageNumber = 1;
      this.listProducts();
    }

    processResult() {
      return (data: any) => {                
        this.products = data.content;
        this.thePageNumber = data.number + 1;
        this.thePageSize = data.size;
        this.theTotalElements = data.totalElements;
      };
    }

    public isAdmin(): boolean {
      if (this.currentUser && this.currentUser.roles) {
        const isAdmin = this.currentUser.roles[0].toString() == RoleEnum.ADMIN.toString();
        if (isAdmin) {        
          return true;
        } else{
          return false;
      }
      } else{
          return false;
      }
    }

    addToCart(name: string) {
      console.log(localStorage.getItem('currentUser'));
      
      this.cartService.addToCart(name).pipe(
        takeUntil(this.unsubscription)
      ).subscribe(data => {
           this.router.navigate(['/cart']);
      })
    }

    deleteProduct(id: number) {
      this.productService.deleteProduct(id).subscribe(
        data => {          
          this.products = data;
          this.listProducts();
        }
      )

    }
  
}