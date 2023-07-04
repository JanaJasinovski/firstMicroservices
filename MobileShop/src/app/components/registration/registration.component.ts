import {Component, OnInit} from '@angular/core';
import {User} from "../../models/user";
import {AuthenticationService} from "../../services/authentication.service";
import {Router} from "@angular/router";
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent implements OnInit {

  user: User = new User();
  errorMessage: string;
  private unsubscription = new Subject<void>;

  constructor(private authenticationService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.authenticationService.currentUserValue?.userId) {
      this.router.navigate(['/profile']);
    }
  }

  regirectToLogin(): void  {
    this.router.navigate(['/login']);
  }

  register() {
    this.authenticationService.register(this.user).pipe(
      takeUntil(this.unsubscription)
    ).subscribe(data => {
         this.router.navigate(['/login']);
    }, error => {
      if (error?.status === 409) {
        this.errorMessage = 'Username already exist.';
      } else {
        this.errorMessage = 'Unexpected error occurred.';
      }
    })
  }

  ngOnDestroy(): void {
    this.unsubscription.next();
    this.unsubscription.complete();
  }

}

