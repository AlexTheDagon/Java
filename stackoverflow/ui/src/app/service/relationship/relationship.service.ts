import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
const baseUrl = 'http://localhost:8080/relationship';
@Injectable({
  providedIn: 'root'
})
export class RelationshipService {

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<any[]> {
    let getAllUrl = `${baseUrl}/getAllRelationships`;
    return this.http.get<any[]>(getAllUrl);
  }
  get(id: any): Observable<any> {
    let getByIDUrl = `${baseUrl}/getRelationship?id=${id}`;
    return this.http.get(getByIDUrl);
  }
  create(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/createRelationship`, data);
  }
  update(data: any): Observable<any> {
    return this.http.post(`${baseUrl}/updateRelationship`, data);
  }
  delete(id: any): Observable<any> {
    return this.http.delete(`${baseUrl}/deleteRelationship?id=${id}`);
  }
}
