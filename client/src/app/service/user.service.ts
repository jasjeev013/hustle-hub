import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDetails, UserRegisterDetails } from '../model/category';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  private loggedInStatus = new BehaviorSubject<boolean>(false);
  loggedInStatus$ = this.loggedInStatus.asObservable();

  setLoggedIn(status: boolean) {
    this.loggedInStatus.next(status);
  }

  checkLoginStatus(): boolean {
    return !!localStorage.getItem('token');
  }

  private apiUrl = 'http://localhost:8080/api';
  headers = new HttpHeaders({
    'Content-Type': 'application/json'
  });

  constructor(private http: HttpClient) {}

  login(loginDetails:LoginDetails): Observable<any> {
    return this.http.post(`${this.apiUrl}/user/apiLogin`, loginDetails, {
      headers: this.headers,
      withCredentials: true // Ensures that cookies are included in the request
    });
  }

  
  registerUser(UserRegisterDetails:UserRegisterDetails): Observable<any> {
    return this.http.post(`${this.apiUrl}/user/create`, UserRegisterDetails, {
      headers: this.headers,
      withCredentials: true // Ensures that cookies are included in the request
    });
  }

  getUser(emailId: string): Observable<any> {
    const token = localStorage.getItem('token'); 
    console.log(token)
    const headers = new HttpHeaders({
       'Content-Type': 'application/json',
      'Authorization': `${token}`, 
    });

    const url = `${this.apiUrl}/user/get/email/${emailId}`;
    console.log(url);
    return this.http.get(url, { headers });
  }
}
