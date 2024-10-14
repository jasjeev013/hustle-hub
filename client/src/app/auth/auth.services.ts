import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { login, logout } from './auth.actions';
import { AuthState } from './auth.reducer';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private store: Store<AuthState>) {}

  checkTokenValidity(): void {
    const token = localStorage.getItem('token'); // Fetch token from localStorage
    if (token && !this.isTokenExpired(token)) {
      const userDetails = JSON.parse(localStorage.getItem('userDetails')!);
      this.store.dispatch(login({ token, userDetails }));
    } else {
      this.store.dispatch(logout());
    }
  }

  isTokenExpired(token: string): boolean {
    // Logic to check if token is expired (use a library like jwt-decode)
    return false; // Replace with actual check
  }
}
