import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BeneficiarioListComponent } from './beneficiarios/beneficiario-list/beneficiario-list.component';
import { BeneficiarioItemComponent } from './beneficiarios/beneficiario-item/beneficiario-item.component';
import { BeneficiariosComponent } from './beneficiarios/beneficiarios.component';
import { HeaderComponent } from './header/header.component';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { ToastModule } from 'primeng/toast';
import { MessagesModule } from 'primeng/messages';
import { LoginComponent } from './public/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MenubarModule } from 'primeng/menubar';
import { InputNumberModule } from 'primeng/inputnumber';
import { DropdownModule } from 'primeng/dropdown';
import { AuthModule } from './auth/auth.module';
import { PlanoListComponent } from './planos/plano-list/plano-list.component';
import { PlanoItemComponent } from './planos/plano-item/plano-item.component';
import { PlanosComponent } from './planos/planos.component';

@NgModule({
  declarations: [
    AppComponent,
    BeneficiarioListComponent,
    BeneficiarioItemComponent,
    BeneficiariosComponent,
    HeaderComponent,
    LoginComponent,
    PlanoListComponent,
    PlanoItemComponent,
    PlanosComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    TableModule,
    MessagesModule,
    DropdownModule,
    InputNumberModule,
    InputTextModule,
    PasswordModule,
    ToastModule,
    ProgressSpinnerModule,
    ButtonModule,
    CardModule,
    MenubarModule,
    HttpClientModule,
    AuthModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
