import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ToastrService } from 'ngx-toastr';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-order-items',
  templateUrl: './order-items.component.html',
  styleUrls: ['./order-items.component.scss']
})
export class OrderItemsComponent implements OnInit {
  @Input() modal_title!: string
  @Input() public order: Order = {};

  ngOnInit(): void {
  
  }

  constructor(
    public activeModal: NgbActiveModal,
    private builder: FormBuilder,
    private toastr: ToastrService,
    // private router: Router
  ) {
  }

}
