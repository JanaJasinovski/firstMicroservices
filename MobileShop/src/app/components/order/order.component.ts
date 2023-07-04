import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/models/cart-item';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  public orders: Order[] = []
  cartItems: CartItem[] = []
  public id: string;
  showLabel = false;
  openedOrderId = null;
  public openedOrder: Order = {};

  constructor(private orderService: OrderService,
    private router: Router) { }

  ngOnInit(): void {
    this.getOrder();
    // this.getCartItems(this.id)
  }

  public toggleModalWindow(orderId: string) {
    this.openedOrderId = orderId;
console.log(orderId, this.orders.find((order) => 
  order.id === orderId
));

    if (this.openedOrderId !== null) {
      this.openedOrder = {
        ...this.orders.find((order) => 
          order.id === orderId
        )
      }
      console.log(this.openedOrder);
      
    } else {
      this.openedOrder = {}
    }
  }

  getOrder() {
    this.orderService.getOrder().subscribe(
      data => {
        this.orders = data;
      }
    )
  }


  toggleLabel(order: Order) {
    this.showLabel = !this.showLabel;
    this.id = order.id;
  }
}
