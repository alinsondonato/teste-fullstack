import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { Plano } from './plano.model';

@Injectable({
  providedIn: 'root'
})
export class PlanoService {

  private apiUrl = `${environment.apiUrl}planos`;

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return headers;
  }

  getPlanos(): Observable<Plano[]> {
    const headers = this.getHeaders();
    return this.http.get<Plano[]>(this.apiUrl, { headers });
  }

  getPlanoById(id: number): Observable<Plano> {
    const headers = this.getHeaders();
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Plano>(url, { headers });
  }

  adicionarPlano(plano: Plano): Observable<Plano> {
    const headers = this.getHeaders();
    return this.http.post<Plano>(this.apiUrl, plano, { headers });
  }

  atualizarPlano(plano: Plano): Observable<Plano> {
    const headers = this.getHeaders();
    const url = `${this.apiUrl}/${plano.id}`;
    return this.http.put<Plano>(url, plano, { headers });
  }

  excluirPlano(id: number): Observable<void> {
    const headers = this.getHeaders();
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url, { headers });
  }
}
