import {Injectable} from '@angular/core';
import {HttpService} from '@shared/service/http.service';
import {Observable} from "rxjs";
import {ApiResponse} from "@shared/model";
import {map} from "rxjs/operators";

class Payload {
}

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  baseUrl = 'http://localhost:2023/api/';
  constructor(public http: HttpService) {
  }
  get(partUrl: string): Observable<ApiResponse> {
    return this.http.get(`${this.baseUrl}${partUrl}`).pipe(map((response: any) => response as ApiResponse));
  }

  post(partUrl: string, payload: Payload, showToaster = false): Observable<ApiResponse> {
    return this.http.post(`${this.baseUrl}${partUrl}`, payload).pipe(map((response: any) => response as ApiResponse));
  }

  put(partUrl: string, payload: Payload, showToaster = false): Observable<ApiResponse> {
    return this.http.put(`${this.baseUrl}${partUrl}`, payload).pipe(map((response: any) => response as ApiResponse));
  }

  delete(partUrl: string, showToaster = false): Observable<ApiResponse> {
    return this.http.delete(`${this.baseUrl}${partUrl}`).pipe(map((response: any) => response as ApiResponse));
  }
}
