import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { createCategory } from '../model/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient, private cookieService: CookieService) {}

  testApi() {
    const token = localStorage.getItem('token');
    console.log(token);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
    });
    return this.http.get('http://localhost:8080/test',{
      headers: headers,
      withCredentials:true
    });
  }

  getAllCategories() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
    });

    return this.http.get('http://localhost:8080/api/category/get/all/2', {
      headers: headers,
      withCredentials: true,
    });
  }

  createNewCategory(newCat: any) {
    const token = localStorage.getItem('token');
    const xsrfToken = this.cookieService.get('XSRF-TOKEN');
    if (!xsrfToken) {
      this.testApi().subscribe();
      // console.log('XSRF Token not found');
      // return;
    }
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
      'X-XSRF-TOKEN': `${xsrfToken}`,
    });

    return this.http.post(
      'http://localhost:8080/api/category/create/2',
      newCat,
      { headers, withCredentials: true }
    );
  }
}
