import { Component, OnInit } from '@angular/core';
import { AuthService } from './auth/auth.services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'client';
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.checkTokenValidity(); // Check token validity when app starts
  }
}
