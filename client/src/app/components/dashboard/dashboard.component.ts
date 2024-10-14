import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiResponseData, ApiResponseObject, Category, createCategory } from 'src/app/model/category';
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
  router = inject(Router);


  newCategory: createCategory = new createCategory();
  CatWithTasks: Category[] = []

  openPopupAddTask(){
    const modal = document.getElementById('addTaskModal');
    if (modal != null) {
      modal.style.display = 'block';  // Make the modal visible
      modal.classList.add('show');    // Add Bootstrap's 'show' class to make it appear
      modal.style.opacity = '1';      // Set the opacity for smooth transition
    }
    }
    closeModal() {
      const modal = document.getElementById('addTaskModal');
      if (modal != null) {
        modal.style.display = 'none';  // Hide the modal
        modal.classList.remove('show');
      }
    }
  

  submitTask() {
    this.categoryService.createNewCategory(this.newCategory)?.subscribe(
        (response: any) => {
          if (response.result) {
            this.CatWithTasks.push(response.object);
            this.newCategory = new createCategory();
            this.router.navigate(['dashboard']);
          }
        },
        (error) => {
          console.log(error);
        }
      );

  
    this.closeModal();
  }

  ngOnInit(): void {
    if(this.userService.checkLoginStatus()){
      this.categoryService.getAllCategories().subscribe(
        (response:any) => {
          if(response.result){

            this.CatWithTasks = response.data[0];
          }
        },
        (error) => {
          console.log(error);
        }
      )
      
    }
  }
  
}
