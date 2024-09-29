import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { ApiResponseObject, ProfileRegisterDetails, UserRegisterDetails } from 'src/app/model/category';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  userService = inject(UserService);
  router = inject(Router);

  userRegisterDetails: UserRegisterDetails = new UserRegisterDetails();

  register(){
    console.log(this.userRegisterDetails);
    this.userService.registerUser(this.userRegisterDetails).subscribe(
      (response: ApiResponseObject) => {
        if (response.result) {
          console.log(response.object)
          this.router.navigate(['login']);
        } else {
          console.log('Login failed');
        }
      },
      (error) => {
        console.log('Invalid Details');
      }
    );
  }
}
