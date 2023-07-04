import { Component } from '@angular/core';
import { RoleEnum } from './models/roleEnum.enum';
import { User } from './models/user';
import { Router } from '@angular/router';
import { AuthenticationService } from './services/authentication.service';
import { Subject, takeUntil } from 'rxjs';
import { Role } from './models/role';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  public currentUser: User = new User();

  private unsubscription = new Subject<void>;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
    this.authenticationService.currentUser.pipe(
      takeUntil(this.unsubscription)
    ).subscribe(data => {
      let data2 = this.authenticationService.parseToken( JSON.stringify(data))
      this.currentUser = { ...data2 };
    });
  }

  ngOnInit(): void {

  }

  public logOut(): void {
    this.currentUser.userId = undefined;
    
    this.authenticationService.logOut();
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.unsubscription.next();
    this.unsubscription.complete();
  }

}
