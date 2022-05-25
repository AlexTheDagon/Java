import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
const baseUrl = 'http://localhost:8080/question';
@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any[]> {
    let getAllUrl = `${baseUrl}/getAllQuestions`;
    return this.http.get<any[]>(getAllUrl);
  }
  get(id: any): Observable<any> {
    let getByIDUrl = `${baseUrl}/getQuestion?id=${id}`;
    return this.http.get(getByIDUrl);
  }
  create(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/createQuestion`, data);
  }
  update(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updateQuestion`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deleteQuestion?id=${id}`);
  }

}
