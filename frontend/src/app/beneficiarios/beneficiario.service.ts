import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Beneficiario } from './beneficiario.model';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BeneficiarioService {

  private apiUrl = `${environment.apiUrl}beneficiarios`;

  constructor(private http: HttpClient) { }

  private getHeaders(): HttpHeaders {
    const token = localStorage.getItem('token');
    
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return headers;
  }

  getBeneficiarios(): Observable<Beneficiario[]> {
    const headers = this.getHeaders();
    return this.http.get<Beneficiario[]>(this.apiUrl, { headers });
  }

  getBeneficiarioById(id: number): Observable<Beneficiario> {
    const headers = this.getHeaders();
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Beneficiario>(url, { headers });
  }

  adicionarBeneficiario(beneficiario: Beneficiario): Observable<Beneficiario> {
    const headers = this.getHeaders();
    return this.http.post<Beneficiario>(this.apiUrl, beneficiario, { headers });
  }

  atualizarBeneficiario(beneficiario: Beneficiario): Observable<Beneficiario> {
    const headers = this.getHeaders();
    const url = `${this.apiUrl}/${beneficiario.id}`;
    return this.http.put<Beneficiario>(url, beneficiario, { headers });
  }

  excluirBeneficiario(id: number): Observable<void> {
    const headers = this.getHeaders();
    const url = `${this.apiUrl}/${id}`;
    return this.http.delete<void>(url, { headers });
  }
}
