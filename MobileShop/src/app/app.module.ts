import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { LoginComponent } from './components/login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { ProfileComponent } from './components/profile/profile.component';
import { DetailComponent } from './components/detail/detail.component';
import { ProductComponent } from './components/product/product.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppHeaderComponent } from './components/app-header/app-header.component';
import { BrowserModule } from '@angular/platform-browser';
import { JwtInterceptor } from './services/authintercptor.service';
import { SearchComponent } from './components/search/search.component';
import { ProductCategoryComponent } from './components/product-category/product-category.component';
import { ProductService } from './services/product.service';
import { NgbActiveModal, NgbPaginationModule } from '@ng-bootstrap/ng-bootstrap';
import { CartComponent } from './components/cart/cart.component';
import { OrderComponent } from './components/order/order.component';
import { AdminComponent } from './components/admin/admin.component';
import { ToastrModule } from 'ngx-toastr';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { SingleOrderComponent } from './components/single-order/single-order.component';
import { OrderItemsComponent } from './components/order-items/order-items.component';
import { SearchBetweenComponent } from './components/search-between/search-between.component';
import { CommentarComponent } from './components/commentar/commentar.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserComponent } from './components/user/user.component';
import { PaymentComponent } from './components/payment/payment.component';
import { AddressComponent } from './components/address/address.component';


@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    AppHeaderComponent,
    LoginComponent,
    ProfileComponent,
    DetailComponent,
    ProductComponent,
    SearchComponent,
    ProductCategoryComponent,
    CartComponent,
    OrderComponent,
    AdminComponent,
    SingleOrderComponent,
    OrderItemsComponent,
    SearchBetweenComponent,
    CommentarComponent,
    UserComponent,
    PaymentComponent,
    AddressComponent
  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
    NgbPaginationModule,
    NgbModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
  ],
  providers: [ {provide: HTTP_INTERCEPTORS, useClass:JwtInterceptor, multi: true }, ProductService, NgbActiveModal],
  bootstrap: [AppComponent]
})
export class AppModule { }
