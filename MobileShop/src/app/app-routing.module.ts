import {NgModule} from '@angular/core';
import {Router, RouterModule, Routes} from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { ProfileComponent } from './components/profile/profile.component';
import {RoleEnum} from "./models/roleEnum.enum";
import { ProductComponent } from './components/product/product.component';
import { AuthGuard } from './guards/auth.guard';
import { DetailComponent } from './components/detail/detail.component';
import { CartComponent } from './components/cart/cart.component';
import { OrderComponent } from './components/order/order.component';
import { AdminComponent } from './components/admin/admin.component';
import { AddressComponent } from './components/address/address.component';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'search/:keyword', component: ProfileComponent},
  {path: 'search/:minPrice/:maxPrice', component: ProfileComponent },
  {path: 'category/:id', component: ProfileComponent},
  {path: 'category', component: ProductComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'address', component: AddressComponent},
  {path: 'product', component: ProductComponent},
  {path: 'products/:id', component: DetailComponent},
  {path: 'products', component: ProfileComponent},
  {path: 'cart', component: CartComponent},
  {path: 'order', component: OrderComponent},
  {path: 'admin', component: AdminComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(private router: Router) {
    this.router.errorHandler = (error: any) => {
      console.error('Navigation Error:', error);
    };
  }
}
