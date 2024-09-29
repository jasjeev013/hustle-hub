import { Component, inject, OnInit } from '@angular/core';
import { ApiResponseData, Category } from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userService = inject(UserService);
  categoryService = inject(CategoryService);

  CatWithTasks: Category[] = []
  ngOnInit(): void {
    console.log(this.userService.checkLoginStatus());
    if(this.userService.checkLoginStatus()){
      this.userService.getUser("jasjeev99@gmail.com").subscribe(
        (response) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      )

      this.categoryService.getAllCategories().subscribe(
        (response:any) => {
          if(response.result){

            this.CatWithTasks = response.data[0];
            console.log(this.CatWithTasks)
          }
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      )
      
    }
  }
  
}
