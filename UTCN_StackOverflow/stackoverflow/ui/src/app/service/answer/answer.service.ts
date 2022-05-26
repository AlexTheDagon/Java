import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
const baseUrl = 'http://localhost:8080/answer';
@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(`${baseUrl}/getAllAnswers`);
  }
  get(id: any): Observable<any> {
    return this.http.get(`${baseUrl}/getAnswer?id=${id}`);
  }
  create(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/createAnswer`, data);
  }
  update(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updateAnswer`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deleteAnswer?id=${id}`);
  }

}
