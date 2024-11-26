import { Component } from '@angular/core';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  userName: string = 'John Doe';
  userDescription: string = 'A passionate software developer who loves to build cool projects.';
  categoriesCount: number = 5;
  tasksCompletedCount: number = 12;
  tasksPendingCount: number = 3;
}
