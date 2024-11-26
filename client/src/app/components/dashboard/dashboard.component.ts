import { Component, inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { CookieService } from 'ngx-cookie-service';
import { AuthState } from 'src/app/auth/auth.reducer';
import { selectIsLoggedIn } from 'src/app/auth/auth.selectors';
import {
  ApiResponseData,
  ApiResponseObject,
  Category,
  createCategory,
  createTask,
} from 'src/app/model/category';
import { CategoryService } from 'src/app/service/category.service';
import { TaskService } from 'src/app/service/task.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  userService = inject(UserService);
  categoryService = inject(CategoryService);
  taskService = inject(TaskService);
  router = inject(Router);

  editMakeCategory: createCategory = new createCategory();

  newCategory: createCategory = new createCategory();
  CatWithTasks: Category[] = [];

  categoryInWhichToBeAdded: number = 0;
  newTask: createTask = new createTask();

  constructor(
    private cookieService: CookieService,
    private store: Store<AuthState>
  ) {}

  openPopupAddCategory() {
    const modal = document.getElementById('addCategoryModal');
    if (modal != null) {
      modal.style.display = 'block'; // Make the modal visible
      modal.classList.add('show'); // Add Bootstrap's 'show' class to make it appear
      modal.style.opacity = '1'; // Set the opacity for smooth transition
    }
  }

  closeModalAddCategory() {
    const modal = document.getElementById('addCategoryModal');
    if (modal != null) {
      modal.style.display = 'none'; // Hide the modal
      modal.classList.remove('show');
    }
  }
  openPopupEditCategory(cat: Category) {
    this.editMakeCategory = cat;

    const modal = document.getElementById('editCategoryModal');
    if (modal != null) {
      modal.style.display = 'block'; // Make the modal visible
      modal.classList.add('show'); // Add Bootstrap's 'show' class to make it appear
      modal.style.opacity = '1'; // Set the opacity for smooth transition
    }
  }

  closeModalEditCategory() {
    const modal = document.getElementById('editCategoryModal');
    if (modal != null) {
      modal.style.display = 'none'; // Hide the modal
      modal.classList.remove('show');
    }
  }

  openPopupAddTask(catNum: number) {
    this.categoryInWhichToBeAdded = catNum;
    const modal = document.getElementById('addTaskModal');
    if (modal != null) {
      modal.style.display = 'block'; // Make the modal visible
      modal.classList.add('show'); // Add Bootstrap's 'show' class to make it appear
      modal.style.opacity = '1'; // Set the opacity for smooth transition
    }
  }

  closeModalAddTask() {
    const modal = document.getElementById('addTaskModal');
    if (modal != null) {
      modal.style.display = 'none'; // Hide the modal
      modal.classList.remove('show');
    }
  }

  submitCategory() {
    // const xsrfToken = this.cookieService.get('XSRF-TOKEN');
    // if(!xsrfToken){
    //   this.categoryService.testApi().subscribe(
    //     (response: any) => {
    //       console.log(response);
    //     },
    //     (error) => {
    //       console.log(error);
    //     }
    //   );
    // }
    // console.log(this.cookieService.get('XSRF-TOKEN'))

    this.categoryService.createNewCategory(this.newCategory)?.subscribe(
      (response: any) => {
        if (response.result) {
          this.CatWithTasks.push(response.object);
          this.newCategory = new createCategory();

          // this.router.navigate(['dashboard']);
        }
      },
      (error: any) => {
        console.log(error);
      }
    );

    this.closeModalAddCategory();
  }

  submitEditedCategory() {
    this.categoryService.editCategory(this.editMakeCategory)?.subscribe(
      (response: any) => {
        if (response.result) {
          this.CatWithTasks = this.CatWithTasks.map((category) => {
            if (category.id === this.editMakeCategory.id) {
              category.name = this.editMakeCategory.name;
              category.color = this.editMakeCategory.color;
            }
            return category;
          });
          this.editMakeCategory = new createCategory();
        }
      },
      (error: any) => {
        console.log(error);
      }
    );
    this.closeModalEditCategory();
  }

  ngOnInit(): void {
    this.store.select(selectIsLoggedIn).subscribe((isLoggedIn) => {
      console.log('Is Logged In:', isLoggedIn);
      if (isLoggedIn) {
        this.categoryService.getAllCategories().subscribe(
          (response: any) => {
            if (response.result) {
              this.CatWithTasks = response.data[0];
              console.log(this.CatWithTasks);
            }
          },
          (error) => {
            console.log(error);
          }
        );
      }
    });
  }

  submitTask() {
    console.log(this.categoryInWhichToBeAdded, ' ', this.newTask);
    this.taskService
      .createNewTask(this.newTask, this.categoryInWhichToBeAdded)
      ?.subscribe(
        (response: any) => {
          if (response.result) {
            console.log(response.object);
            this.CatWithTasks = this.CatWithTasks.map((category) => {
              if (category.id === this.categoryInWhichToBeAdded) {
                category.tasks.push(response.object);
                this.newTask = new createTask();
              }
              return category;
              // this.CatWithTasks = response.data[0];
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );

    this.closeModalAddTask();
  }

  deleteCategory(cat: Category) {
    console.log(cat);
    this.categoryService.deleteCategory(cat.id)?.subscribe(
      (response: any) => {
        if (response.result) {
          this.CatWithTasks = this.CatWithTasks.filter(
            (category) => category.id !== cat.id
          );
          console.log(response);
        }
      },
      (error) => {
        console.log(error);
      }
    );

    let xsrfToken = this.cookieService.get('XSRF-TOKEN');
    if (xsrfToken) {
      console.log(xsrfToken);

      this.categoryService.testApi().subscribe(
        (response: any) => {
          console.log(response);
        },
        (error) => {
          console.log(error);
        }
      );
    }
  }
  editCategory(cat: Category) {
    console.log(cat);
  }

  getProgressPercentage(createdDate: string | Date, dueDate: string | Date): number {
    const createdTimestamp = new Date(createdDate).getTime(); // Convert to Date object if string
    const dueTimestamp = new Date(dueDate).getTime(); // Convert to Date object if string
    const currentTimestamp = Date.now();
  
    if (currentTimestamp <= createdTimestamp) return 0; // Task hasn't started yet
    if (currentTimestamp >= dueTimestamp) return 100; // Task is overdue or complete
  
    const totalDuration = dueTimestamp - createdTimestamp;
    const elapsedDuration = currentTimestamp - createdTimestamp;
  
    // Return an integer percentage
    return Math.min(Math.floor((elapsedDuration / totalDuration) * 100), 100);
  }
}
