import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { LoginDetails, LoginResponse } from '../model/category';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/user/apiLogin';
  
  constructor(private http: HttpClient) {}

  login(username: String, password: String): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    const body = {
      username: username,
      password: password
    };

    return this.http.post(this.apiUrl, body, {
      headers: headers,
      withCredentials: true // Ensures that cookies are included in the request
    });
  }
}
