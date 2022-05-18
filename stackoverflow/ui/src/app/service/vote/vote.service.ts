import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
const baseUrl = 'http://localhost:8080/vote';
@Injectable({
  providedIn: 'root'
})
export class VoteService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any[]> {
    let getAllUrl = `${baseUrl}/getAllVotes`;
    return this.http.get<any[]>(getAllUrl);
  }
  get(id: any): Observable<any> {
    let getByIDUrl = `${baseUrl}/getVote?id=${id}`;
    return this.http.get(getByIDUrl);
  }
  create(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/createVote`, data);
  }
  update(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updateVote`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deleteVote?id=${id}`);
  }
}
