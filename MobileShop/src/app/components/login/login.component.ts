import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  user: User = new User();
  errorMessage: string = "";
  private unsubscription = new Subject<void>;

  constructor(private authenticationService: AuthenticationService,
    private router: Router) {
  }

  ngOnInit(): void {
    if (this.authenticationService.currentUserValue?.userId) {
      this.router.navigate(['/profile']);
    }
  }

  regirectToRegiter() {
    this.router.navigate(['/registration']);
  }

  public login(): void {
    this.authenticationService.login(this.user).subscribe(data => {
      this.router.navigate(['/profile']);
    }, error => {
      this.errorMessage = 'Username or password is incorrect.';
      console.log(error);
    });
  }

  ngOnDestroy(): void {
    this.unsubscription.next();
    this.unsubscription.complete();
  }


}
