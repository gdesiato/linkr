import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Domain {
  id: number;
  url: string;
  createdAt: string;
}

@Injectable({
  providedIn: 'root'
})
export class DomainService {
  private apiUrl = 'http://localhost:8080/api/domains';

  constructor(private http: HttpClient) {}

  getDomains(): Observable<Domain[]> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.http.get<Domain[]>(this.apiUrl, { headers });
  }
}
