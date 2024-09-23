import { Component } from '@angular/core';
import { Category } from 'src/app/model/category';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {

  categories: String[] = ["College","Web Development","Data Structures","NSS","SIH"]
  CatWithTasks: Category[] = [
    {
      name: 'College',
      tasks: [
        {
          taskId: 1,
          title: 'Attend Lectures',
          description: 'Attend all the lectures',
          dueDate: new Date('2021-08-31'),
          priority: 'High',
          status: 'In Progress'
        },
        {
          taskId: 2,
          title: 'Submit Assignments',
          description: 'Submit all the assignments',
          dueDate: new Date('2021-08-31'),
          priority: 'Medium',
          status: 'In Progress'
        },
        {
          taskId: 3,
          title: 'Prepare for Exams',
          description: 'Prepare for all the exams',
          dueDate: new Date('2021-08-31'),
          priority: 'Low',
          status: 'In Progress'
        }
      ]
    },
    {
      name: 'Web Development',
      tasks: [
        {
          taskId: 1,
          title: 'Learn Angular',
          description: 'Learn Angular from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'High',
          status: 'In Progress'
        },
        {
          taskId: 2,
          title: 'Learn React',
          description: 'Learn React from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Medium',
          status: 'In Progress'
        },
        {
          taskId: 3,
          title: 'Learn Node.js',
          description: 'Learn Node.js from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Low',
          status: 'In Progress'
        }
      ]
    },
    {
      name: 'Data Structures',
      tasks: [
        {
          taskId: 1,
          title: 'Learn Arrays',
          description: 'Learn Arrays from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'High',
          status: 'In Progress'
        },
        {
          taskId: 2,
          title: 'Learn Linked List',
          description: 'Learn Linked List from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Medium',
          status: 'In Progress'
        },
        {
          taskId: 3,
          title: 'Learn Stacks',
          description: 'Learn Stacks from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Low',
          status: 'In Progress'
        }
      ]
    },
    {
      name: 'Data Structures',
      tasks: [
        {
          taskId: 1,
          title: 'Learn Arrays',
          description: 'Learn Arrays from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'High',
          status: 'In Progress'
        },
        {
          taskId: 2,
          title: 'Learn Linked List',
          description: 'Learn Linked List from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Medium',
          status: 'In Progress'
        },
        {
          taskId: 3,
          title: 'Learn Stacks',
          description: 'Learn Stacks from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Low',
          status: 'In Progress'
        }
      ]
    },
    {
      name: 'Data Structures',
      tasks: [
        {
          taskId: 1,
          title: 'Learn Arrays',
          description: 'Learn Arrays from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'High',
          status: 'In Progress'
        },
        {
          taskId: 2,
          title: 'Learn Linked List',
          description: 'Learn Linked List from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Medium',
          status: 'In Progress'
        },
        {
          taskId: 3,
          title: 'Learn Stacks',
          description: 'Learn Stacks from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Low',
          status: 'In Progress'
        }
      ]
    },
    {
      name: 'Data Structures',
      tasks: [
        {
          taskId: 1,
          title: 'Learn Arrays',
          description: 'Learn Arrays from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'High',
          status: 'In Progress'
        },
        {
          taskId: 2,
          title: 'Learn Linked List',
          description: 'Learn Linked List from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Medium',
          status: 'In Progress'
        },
        {
          taskId: 3,
          title: 'Learn Stacks',
          description: 'Learn Stacks from scratch',
          dueDate: new Date('2021-08-31'),
          priority: 'Low',
          status: 'In Progress'
        }
      ]
    }
  ];
  
}
