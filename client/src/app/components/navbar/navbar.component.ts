import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import {
  selectIsLoggedIn,
  selectUserDetails,
} from 'src/app/auth/auth.selectors';
import { AuthState } from 'src/app/auth/auth.reducer';
import { UserService } from 'src/app/service/user.service';
import { AuthService } from 'src/app/auth/auth.services';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  userService = inject(UserService);
  router = inject(Router);

  public isLoggedIn: boolean = false;

  isLoggedIn$: Observable<boolean>;
  userDetails$: Observable<any>;

  constructor(
    private store: Store<AuthState>,
    private authService: AuthService
  ) {
    this.isLoggedIn$ = this.store.select(selectIsLoggedIn);
    this.userDetails$ = this.store.select(selectUserDetails);
  }

  ngOnInit(): void {
    this.isLoggedIn$ = this.store.select(selectIsLoggedIn);
    this.userDetails$ = this.store.select(selectUserDetails);

    this.isLoggedIn = this.userService.checkLoginStatus();
    this.userService.loggedInStatus$.subscribe((status) => {
      this.isLoggedIn = status;
    });
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userDetails');
    this.userService.setLoggedIn(false);
    window.location.href = 'http://localhost:4200/home';
  }
}
