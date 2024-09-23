import { Component, inject, OnInit } from '@angular/core';
import { LoginDetails, LoginResponse } from 'src/app/model/category';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  userService = inject(UserService);

  loginDetails: LoginDetails = new LoginDetails();
  token: string = '';

  constructor(private authService: UserService) { }



  login(){
   this.authService.login(this.loginDetails.email, this.loginDetails.password).subscribe(
      (response: any) => {
        if (response.status === 'OK') {
          this.token = response.jwtToken;
          console.log('Login successful, JWT Token: ', this.token);
        } else {
          console.log('Login failed');
        }
      },
      (error) => {
        console.error('Error during login:', error);
      }
    );
  }

  loginSubmit(){
    console.log(this.loginDetails);
    this.login();
  }
}
