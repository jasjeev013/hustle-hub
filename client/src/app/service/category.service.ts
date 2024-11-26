import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { createCategory } from '../model/category';
import { NgModel } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  constructor(private http: HttpClient, private cookieService: CookieService) {}

  testApi() {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
      'X-XSRF-TOKEN': '',
    });
    return this.http.post(
      'http://localhost:8080/api/test/',
      {},
      {
        headers: headers,
        withCredentials: true,
      }
    );
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

  deleteCategory(catId: number) {
    const token = localStorage.getItem('token');
    let xsrfToken = this.cookieService.get('XSRF-TOKEN');
    if (!xsrfToken) {
      console.log('XSRF Token not found');
      return;
    }
    // console.log(xsrfToken);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
      'X-XSRF-TOKEN': `${xsrfToken}`,
    });

    return this.http.delete(
      `http://localhost:8080/api/category/delete/${catId}`,
      {
        headers: headers,
        withCredentials: true,
      }
    );
  }

  editCategory(editedCat:any){
    const token = localStorage.getItem('token');
    let xsrfToken = this.cookieService.get('XSRF-TOKEN');
    if (!xsrfToken) {
      console.log('XSRF Token not found');
      return;
    }
    // console.log(xsrfToken);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
      'X-XSRF-TOKEN': `${xsrfToken}`,
    });

    return this.http.put(
      `http://localhost:8080/api/category/update/${editedCat.id}`,
      editedCat,
      {
        headers: headers,
        withCredentials: true,
      }
    );
  }

  createNewCategory(newCat: any) {
    const token = localStorage.getItem('token');
    let xsrfToken = this.cookieService.get('XSRF-TOKEN');

    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `${token}`,
      'X-XSRF-TOKEN': `${xsrfToken}`,
    });
    if (!xsrfToken) {
      console.log('XSRF TOken not found');
      //  this.testApi().subscribe(
      //     (response: any) => {
      //       console.log(response);
      //       xsrfToken = this.cookieService.get('XSRF-TOKEN');
      //       console.log(xsrfToken);
      //     },
      //     (error) => {
      //       console.log(error);
      //     }
      //  )
      return;
    }

    return this.http.post(
      'http://localhost:8080/api/category/create/2',
      newCat,
      { headers, withCredentials: true }
    );
  }
}
