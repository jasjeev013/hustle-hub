import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private cookieService: CookieService) {}


  xsrfTokenGenerator(){
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
    });
    return this.http.post('http://localhost:8080/api/test/', { headers, withCredentials: true });
  }

  createNewTask(newTask: any,categoryId: number) {
    const token = localStorage.getItem('token');
    let xsrfToken = this.cookieService.get('XSRF-TOKEN');
    if (!xsrfToken) {
      console.log('XSRF Token not found');
      return;
    }
    console.log(xsrfToken);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
      'X-XSRF-TOKEN': `${xsrfToken}`,
    });
    
    return this.http.post(
      `http://localhost:8080/api/task/create/${categoryId}`,
      newTask,
      { headers, withCredentials: true }
    );
  }
}
