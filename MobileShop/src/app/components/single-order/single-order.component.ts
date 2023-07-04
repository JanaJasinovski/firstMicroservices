import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { CartItem } from 'src/app/models/cart-item';
import { Order } from 'src/app/models/order';
import { OrderService } from 'src/app/services/order.service';
import { OrderComponent } from '../order/order.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { OrderItemsComponent } from '../order-items/order-items.component';

@Component({
  selector: 'app-single-order',
  templateUrl: './single-order.component.html',
  styleUrls: ['./single-order.component.scss']
})
export class SingleOrderComponent implements OnInit {
  @Input() public order: Order;
  showLabel = false;
  public id: string;
  cartItems: CartItem[] = []

  constructor(private orderService: OrderService,
    private modalService: NgbModal,
    private router: Router ) { }
    
  ngOnInit(): void {
  }

  open(orderId: string) {
    const modalRef = this.modalService.open(OrderItemsComponent, {backdrop: 'static' });
    modalRef.componentInstance.modal_title = 'Ваш заказ';
    modalRef.componentInstance.order = this.order;
  }
}
