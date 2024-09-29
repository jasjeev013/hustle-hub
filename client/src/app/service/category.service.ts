import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http:HttpClient) { }

  getAllCategories(){
    const token = localStorage.getItem('token'); 
    console.log(token)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
     'Authorization': `${token}`, 
   });

    return this.http.get('http://localhost:8080/api/category/get/all/2',{ headers });
  }
}
