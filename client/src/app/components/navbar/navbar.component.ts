import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  userService = inject(UserService);
  router = inject(Router);

  public isLoggedIn: boolean = false;

  // constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.userService.checkLoginStatus();
    console.log(this.isLoggedIn)
    this.userService.loggedInStatus$.subscribe(status => {
      this.isLoggedIn = status;
    });
  }

  logout() {
    localStorage.removeItem('token');
    this.userService.setLoggedIn(false);
    this.router.navigate(['home']);
  }

}
