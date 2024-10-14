import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDetails, LoginResponse } from 'src/app/model/category';
import { UserService } from 'src/app/service/user.service';
import { NavbarComponent } from '../navbar/navbar.component';
import { Store } from '@ngrx/store';
import { AuthState } from 'src/app/auth/auth.reducer';
import { login } from 'src/app/auth/auth.actions';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  userService = inject(UserService);
  router = inject(Router);

  loginDetails: LoginDetails = new LoginDetails();
  showErrorModel: boolean = false;
  showSuccessModel: boolean = false;

  constructor(private store: Store<{ auth: AuthState }>) {}

  login() {
    this.userService.login(this.loginDetails).subscribe(
      (response: LoginResponse) => {
        if (response.status === 'OK') {
          localStorage.setItem('token', response.jwtToken);
          this.userService.setLoggedIn(true); // Update login status
          this.showErrorModel = false;
          this.showSuccessModel = true;
          let token = response.jwtToken;
          let userDet: any = null;
          this.userService.getUser(this.loginDetails.username).subscribe(
            (response) => {
              if (response.result) {
                userDet = response.object;
                this.store.dispatch(login({ token, userDetails: userDet }));
                this.router.navigate(['dashboard']);
              } else {
                console.log('No user found');
              }
            },
            (error) => {
              console.log(error);
            }
          );
        } else {
          console.log('Login failed');
        }
      },
      (error) => {
        console.log('Invalid Credentials');
        this.showSuccessModel = false;
        this.showErrorModel = true;
      }
    );
  }



  loginSubmit() {
    this.login();
  }
}
