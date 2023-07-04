import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Product } from 'src/app/models/product';
import { ProductService } from 'src/app/services/product.service';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ToastrService } from "ngx-toastr";
import { Location } from '@angular/common';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  @Input() modal_title!: string

  public product: Product;
  public form!: FormGroup;
  public currentUser: User = new User();

  constructor(
    public activeModal: NgbActiveModal,
    private builder: FormBuilder,
    private toastr: ToastrService,
    private productService: ProductService,
    private authenticationService: AuthenticationService,
    private location: Location,
    private router: Router
  ) {
    this.product = {
      name: '',
      price: 0.00,
      amount: 0,
      picture: '',
      categoryName: ''
    }
  }

  ngOnInit(): void {
    this.createForm();
    this.authenticationService.currentUser.subscribe(data => {
      let data2 = this.authenticationService.parseToken(JSON.stringify(data));
      this.currentUser = data2;
    })

  }

  private createForm(): void {
    this.form = this.builder.group({
      name: new FormControl('', [
        Validators.required
      ]),
      price: new FormControl('', [Validators.required]),
      amount: new FormControl('', [
        Validators.required
      ]),
      picture: new FormControl('', [
        Validators.required,
      ]),
      categoryName: new FormControl('', [
        Validators.required,
      ]),
    }
  )

  }

  createProduct() {
    const formData = { ...this.form?.value }
    this.product.name = formData.name;
    this.product.price = formData.price;
    this.product.amount = formData.amount;
    this.product.picture = formData.picture;
    this.product.categoryName = formData.categoryName;

    this.productService.createProduct(this.product).subscribe(
      (resp => {
        this.form.reset()
        this.toastr.success('Create product successful')
        this.router.navigate["/category"]
        window.location.reload();
        // this.productService.broadcast(this.product)
      }), error => {
        this.toastr.error('Create failed! Please try again')
      }
    )
  }
}
