import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Cart } from 'src/app/models/cart';
import { CartItem } from 'src/app/models/cart-item';
import { Order } from 'src/app/models/order';
import { CartService } from 'src/app/services/cart.service';
import { OrderService } from 'src/app/services/order.service';
import { PaymentComponent } from '../payment/payment.component';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  public cartItems: CartItem[] = [];
  public cart: Cart;
  orderItems: Order = new Order();
  public totalPrice: number =   0;
  public totalQuantity: number = 1;

  constructor(private cartService: CartService,
    private orderService: OrderService,
    private modalService: NgbModal,
    private router: Router ) { }

  ngOnInit(): void {
    this.listCartDetails();
    this.listCartDetailsN();
  }

  listCartDetails() {
    this.cartService.getCart().subscribe(
      data => {
        this.cartItems = data;
      }
    )
  }

  listCartDetailsN() {
    this.cartService.getCartN().subscribe(
      data => {
        this.cart = data;   
      }
    )
  }


  incrementQuantity(name: string) {    
    this.cartService.incrementAmount(name).subscribe(
      data => {
        this.cartItems = data;
        this.totalQuantity = this.cartItems.reduce((sum, item) => sum + (item.amount), 0);
        this.totalPrice = this.cartItems.reduce((sum, item) => sum + (item.amount * item.productPrice), 0);
      }
    );
  }

  decrementQuantity(name: string) {
    this.cartService.decrementQuantity(name).subscribe(
      data => {
        this.cartItems = data;
        this.totalQuantity = this.cartItems.reduce((sum, item) => (item.amount) - sum, 0);
        this.totalPrice = this.cartItems.reduce((sum, item) => (item.amount * item.productPrice) - sum, 0);
      }
    );
  }

  getTotoalQuantity() {
    this.totalQuantity = this.cart.totalQuantity;
    this.listCartDetails();    
  }

  remove(id: string) {
    this.cartService.remove(id).subscribe(
      data => {
        this.cartItems = data;
        this.listCartDetails();    
      }
    );
  }

  
  public order() {
    this.router.navigate(['/address']);

    // this.orderService.order().subscribe(data => {
    //   this.router.navigate(['/order']);

    // })
  }

  open() {
    const modalRef = this.modalService.open(PaymentComponent);
    modalRef.componentInstance.modal_title = 'Введите свою карту';
  }
}
