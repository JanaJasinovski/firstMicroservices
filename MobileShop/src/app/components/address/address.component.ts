import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Country } from 'src/app/models/country';
import { AddressService } from 'src/app/services/address.service';
import { CartService } from 'src/app/services/cart.service';
import { ShopFormServiceService } from 'src/app/services/shop-form-service.service';
import { ShopValidators } from '../validators/ShopValidators';
import { OrderService } from 'src/app/services/order.service';
import { State } from 'src/app/models/state';
import { Address } from 'src/app/models/address';
import { Order } from 'src/app/models/order';
import { environment } from 'src/environments/environment';
import { PaymentInfo } from 'src/app/models/payment-info';
import { CartItem } from 'src/app/models/cart-item';

@Component({
  selector: 'app-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.scss']
})
export class AddressComponent implements OnInit {

  checkoutFormGroup: FormGroup;
  creditCardYears: number[] = [];
  creditCardMonths: number[] = [];

  countries: Country[] = [];
  address: Address = new Address();
  shippingAddressStates: State[] = [];
  isDisabled: boolean = false;
  public totalPrice: number =   0;
  public totalQuantity: number = 1;

  stripe = Stripe(environment.stripePublishableKey);
  paymentInfo: PaymentInfo = new PaymentInfo();
  cardElement: any;
  displayError: any = "";
  
  constructor(private formBuilder: FormBuilder,
    private luv2ShopFormService: ShopFormServiceService,
    private cartService: CartService,
    private orderService: OrderService,
    private addressSearvice: AddressService,
    private router: Router) {
     }

  ngOnInit(): void {
    this.setupStripePaymentForm();

    this.checkoutFormGroup = this.formBuilder.group({
      shippingAddress: this.formBuilder.group({
        street: new FormControl('', [Validators.required, Validators.minLength(2), 
          ShopValidators.notOnlyWhitespace]),
        city: new FormControl('', [Validators.required, Validators.minLength(2), 
          ShopValidators.notOnlyWhitespace]),
        state: new FormControl('', [Validators.required]),
        country: new FormControl('', [Validators.required]),
        zipCode: new FormControl('', [Validators.required, Validators.minLength(2), 
          ShopValidators.notOnlyWhitespace])
      }),
      creditCard: this.formBuilder.group({
        cardType: new FormControl('', [Validators.required]),
        nameOnCard:  new FormControl('', [Validators.required, Validators.minLength(2), 
                                          ShopValidators.notOnlyWhitespace]),
        cardNumber: new FormControl('', [Validators.required, Validators.pattern('[0-9]{16}')]),
        securityCode: new FormControl('', [Validators.required, Validators.pattern('[0-9]{3}')]),
        expirationMonth: [''],
        expirationYear: ['']
      })
    });

    // populate credit card months

    const startMonth: number = new Date().getMonth() + 1;
    console.log("startMonth: " + startMonth);

    this.luv2ShopFormService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );

    this.luv2ShopFormService.getCreditCardYears().subscribe(
      data => {
        console.log("Retrieved credit card years: " + JSON.stringify(data));
        this.creditCardYears = data;
      }
    );

    this.luv2ShopFormService.getCountries().subscribe(
      data => {
        console.log("Retrieved countries: " + JSON.stringify(data));
        this.countries = data;
      }
    );

    this.cartService.getTotalQuanity2().subscribe(
      data => {
        this.totalQuantity = data;
      }
    )

    this.cartService.getTotalPrice2().subscribe(
      data => {
        this.totalPrice = data;
      }
    )
  }

  get shippingAddressStreet() { return this.checkoutFormGroup.get('shippingAddress.street'); }
  get shippingAddressCity() { return this.checkoutFormGroup.get('shippingAddress.city'); }
  get shippingAddressState() { return this.checkoutFormGroup.get('shippingAddress.state'); }
  get shippingAddressZipCode() { return this.checkoutFormGroup.get('shippingAddress.zipCode'); }
  get shippingAddressCountry() { return this.checkoutFormGroup.get('shippingAddress.country'); }
  
  resetCart() {
    this.cartService.cartItems = [];
    this.cartService.totalPrice.next(0);
    this.cartService.totalQuantity.next(0);
    // this.cartService.persistCartItems();
    
    this.checkoutFormGroup.reset();

    this.router.navigateByUrl("/products");
  }

  handleMonthsAndYears() {

    const creditCardFormGroup = this.checkoutFormGroup.get('creditCard');

    const currentYear: number = new Date().getFullYear();
    const selectedYear: number = Number(creditCardFormGroup?.value.expirationYear);

    let startMonth: number;

    if (currentYear === selectedYear) {
      startMonth = new Date().getMonth() + 1;
    }
    else {
      startMonth = 1;
    }

    this.luv2ShopFormService.getCreditCardMonths(startMonth).subscribe(
      data => {
        console.log("Retrieved credit card months: " + JSON.stringify(data));
        this.creditCardMonths = data;
      }
    );
  }

  getStates(formGroupName: string) {

    const formGroup = this.checkoutFormGroup.get(formGroupName);

    const countryCode = formGroup?.value.country.code;
    const countryName = formGroup?.value.country.name;

    console.log(`${formGroupName} country code: ${countryCode}`);
    console.log(`${formGroupName} country name: ${countryName}`);

    this.luv2ShopFormService.getStates(countryCode).subscribe(
      data => {

        if (formGroupName === 'shippingAddress') {
          this.shippingAddressStates = data; 
        }

        formGroup?.get('state')?.setValue(data[0]);
      }
    );
  }

  newAddress: Address = new Address();
  public postAddress() {
    const address = this.checkoutFormGroup.getRawValue().shippingAddress;
    console.log(address.country.name);
    
    this.newAddress.city = address.city
    this.newAddress.country = address.country.name
    this.newAddress.state = address.state.name
    this.newAddress.street = address.street
    this.newAddress.zipCode = address.zipCode

    this.addressSearvice.createAddress(this.newAddress).subscribe();
  }

  public orderCreate() {
    this.orderService.order().subscribe(data => {
      this.router.navigate(['/order']);
    })
  }

  setupStripePaymentForm() {

    var elements = this.stripe.elements();

    this.cardElement = elements.create('card', { hidePostalCode: true });

    this.cardElement.mount('#card-element');

    this.cardElement.on('change', (event : any) => {

      this.displayError = document.getElementById('card-errors');

      if (event.complete) {
        this.displayError.textContent = "";
      } else if (event.error) {
        this.displayError.textContent = event.error.message;
      }

    });

  }


  onSubmit() {
    console.log("Handling the submit button");

    if (this.checkoutFormGroup.invalid) {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }

    let order = new Order();
    order.totalPrice = this.totalPrice;
    order.totalAmount = this.totalQuantity;

    const cartItems = this.cartService.cartItems;
  
    let purchase = new Order();
    
    purchase.userId = this.checkoutFormGroup.controls['customer'].value;
    
    purchase.shippingAddress = this.checkoutFormGroup.controls['shippingAddress'].value;
    const shippingState: State = JSON.parse(JSON.stringify(purchase.shippingAddress.state));
    const shippingCountry: Country = JSON.parse(JSON.stringify(purchase.shippingAddress.country));
    purchase.shippingAddress.state = shippingState.name;
    purchase.shippingAddress.country = shippingCountry.name;
  
    this.paymentInfo.amount = Math.round(this.totalPrice * 100);
    this.paymentInfo.currency = "USD"; 
    this.paymentInfo.receiptEmail = purchase.userId;

    if (!this.checkoutFormGroup.invalid && this.displayError.textContent === "") {

      this.isDisabled = true;

      this.addressSearvice.createPaymentIntent(this.paymentInfo).subscribe(
        (paymentIntentResponse) => {
          this.stripe.confirmCardPayment(paymentIntentResponse.client_secret,
            {
              payment_method: {
                card: this.cardElement,
                billing_details: {
                  email: purchase.userId,
                  name: `${purchase.userId}`,
                  address: {
                    line1: purchase.shippingAddress.street,
                    city: purchase.shippingAddress.city,
                    state: purchase.shippingAddress.state,
                    postal_code: purchase.shippingAddress.zipCode,
                    country: this.address
                  }
                }
              }
            }, { handleActions: false })
          .then((result : any) => {
            if (result.error) {
              alert(`There was an error: ${result.error.message}`);
              this.isDisabled = false;
            }             
           }) //.bind(this));
        }
      );
    } else {
      this.checkoutFormGroup.markAllAsTouched();
      return;
    }
  }


}