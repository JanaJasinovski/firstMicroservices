import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {UserService} from "../../services/user.service";
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: User = new User();
  public errorMessage: string = "";

  constructor(
              private authenticationService: AuthenticationService,
              private router: Router) { }

  ngOnInit(): void {
    this.authenticationService.currentUser.subscribe(data => { 
      this.currentUser = data;      
    })
  }

  logOut() {
    this.authenticationService.logOut();
    this.router.navigate(['/login']);
  }

}
