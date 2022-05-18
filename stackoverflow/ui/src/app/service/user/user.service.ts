import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
const baseUrl = 'http://localhost:8080/user';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any[]> {
    let getAllUrl = `${baseUrl}/getAllUsers`;
    return this.http.get<any[]>(getAllUrl);
  }
  get(id: any): Observable<any> {
    let getByIDUrl = `${baseUrl}/getUser?id=${id}`;
    return this.http.get(getByIDUrl);
  }
  create(data: any): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  update(id: any, data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updateUser`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
}
/// https://www.bezkoder.com/spring-boot-angular-13-mysql/#Define_Model_Class
