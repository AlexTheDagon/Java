import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
const baseUrl = 'http://localhost:8080/tag';
@Injectable({
  providedIn: 'root'
})
export class TagService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any[]> {
    let getAllUrl = `${baseUrl}/getAllTags`;
    return this.http.get<any[]>(getAllUrl);
  }
  get(id: any): Observable<any> {
    let getByIDUrl = `${baseUrl}/getTag?id=${id}`;
    return this.http.get(getByIDUrl);
  }
  create(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/createTag`, data);
  }
  update(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updateTag`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deleteTag?id=${id}`);
  }
}
