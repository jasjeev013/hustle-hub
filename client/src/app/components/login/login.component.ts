import { Component, inject, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginDetails, LoginResponse } from 'src/app/model/category';
import { UserService } from 'src/app/service/user.service';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent{
  userService = inject(UserService);
  router = inject(Router);


  loginDetails: LoginDetails = new LoginDetails();
  showErrorModel: boolean = false;
  showSuccessModel: boolean = false;

  login() {
    this.userService.login(this.loginDetails).subscribe(
      (response: LoginResponse) => {
        if (response.status === 'OK') {
          localStorage.setItem('token', response.jwtToken);
          this.userService.setLoggedIn(true); // Update login status
          this.showErrorModel = false;
          this.showSuccessModel = true;
          this.router.navigate(['dashboard']);
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
    console.log(this.loginDetails);
    this.login();
  }
}
