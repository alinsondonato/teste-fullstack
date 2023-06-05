import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { JwtHelperService } from '@auth0/angular-jwt';
import { environment } from 'src/environments/environment';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  
  private apiUrl = `${environment.apiUrl}login/auth`;
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  showHeader = false;
  token: any = undefined;

  constructor(
    private http: HttpClient, 
    private jwtHelper: JwtHelperService,
    private router: Router
    ) { 
      this.carregarToken();
  }

  private carregarToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.armazenarToken(token);
    }
  }

  login(login: string, senha: string): Observable<any> {
    const credentials = { login, senha };
    return this.http.post<any>(`${this.apiUrl}`, credentials, { headers: this.headers })
      .pipe(
        map(response => {
          this.armazenarToken(response.token);
          return response.token;
        })
      );
  }

  private armazenarToken(token: string) {
    this.token = this.jwtHelper.decodeToken(token);
    // this.dataExpiracao = new Date(this.token.dataExpiracao);
    localStorage.setItem('token', token);
  }

  isLoggedIn(): boolean {
    const token = localStorage.getItem('token');
    // Verificar se o token existe e não está expirado
    return !!token;
  }
  
  logout() {
    this.showHeader = false;
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}