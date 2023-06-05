import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth/auth.service';
import { FormBuilder, Validators } from '@angular/forms';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  carregando = false;
  temErro = false;
  messages: Message[] = [];

  formGroup = this.formBuilder.group({
    username: ['', [Validators.required]],
    password: ['', [Validators.required]]
  });

  constructor(private router: Router, private authService: AuthService,
    private formBuilder: FormBuilder) { 
      this.messages = [{ severity: 'error', summary: 'Erro ao logar', detail: 'Erro de login. Verifique suas credenciais.' }];
  }

  ngOnInit() {
    this.temErro = false;
  }

  login() {
    this.carregando = true;
    this.authService.login(this.username, this.password).subscribe(
      (response) => {
        this.carregando = false;
        const token = response.token;
        this.authService.showHeader = true;
        this.router.navigate(['/beneficiarios']);
      },
      (error) => {
        this.carregando = false;
        console.error('Falha no login:', error);
        this.temErro = true;
        this.username = '';
        this.password = '';
      }
    );
  }
}
