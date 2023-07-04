import { Component, Input, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {
  @Input() modal_title!: string

  public users: User[] = []
  constructor(private userService: UserService,
    public activeModal: NgbActiveModal,
    private router: Router) { }

    ngOnInit(): void {
      this.getUsers();
    }

    getUsers() {
    this.userService.getUsers().subscribe(
      data => {
        this.users = data;
      }
    )
  }
}
