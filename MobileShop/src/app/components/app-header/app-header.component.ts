import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { Router } from "@angular/router";
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { RoleEnum } from "src/app/models/roleEnum.enum";
import { User } from "src/app/models/user";
import { AuthenticationService } from "src/app/services/authentication.service";
import { AdminComponent } from "../admin/admin.component";
import { UserComponent } from "../user/user.component";

@Component({
    selector: 'app-header',
    templateUrl: './app-header.component.html',
    styleUrls: ['./app-header.component.scss']
})
export class AppHeaderComponent implements OnInit {
    @Output() public logOutChange = new EventEmitter<void>
    @Output() public isAdminPage = new EventEmitter<void>

    public currentUser: User = new User();

    constructor(private authenticationService: AuthenticationService,
        private modalService: NgbModal,
        private router: Router) { }

    ngOnInit(): void {
        this.authenticationService.currentUser.subscribe(data => { 
         let data2 = this.authenticationService.parseToken(JSON.stringify(data));         
          this.currentUser = data2;      
        })
      }

      
    public navLinks = [
        {
            name: 'Cart',
            routerLink: '/cart',
        },
        {
            name: 'Order',
            routerLink: '/order',
        },
    ]

    public logOut(): void {
        this.logOutChange.emit()
    }

    public isAdmin(): boolean {
        if (this.currentUser && this.currentUser.roles) {
          const isAdmin = this.currentUser.roles[0].toString() == RoleEnum.ADMIN.toString();
          if (isAdmin) {
            // this.router.navigate(['/admin']);
            return true;
          } else{
            return false;
        }
        } else{
            return false;
        }
    }

    open() {
        const modalRef = this.modalService.open(AdminComponent);
        modalRef.componentInstance.modal_title = 'Создать продукт';
      }

    openUser() {
        const modalRef = this.modalService.open(UserComponent);
        modalRef.componentInstance.modal_title = 'Список пользователей';
    }

    // public isAdminF(): void {
    //     this.isAdmin.emit()
    // }
}
