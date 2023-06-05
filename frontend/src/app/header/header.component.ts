import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { AuthService } from '../auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
  menuItems: MenuItem[];

  constructor(private authService: AuthService) {
    this.menuItems = [
      {
        label: 'Home',
        routerLink: '/'
      },
      { label: 'Beneficiários', routerLink: '/beneficiarios' },
      { label: 'Planos', routerLink: '/planos' },
      // { label: 'Usuários', routerLink: '/usuarios' },
      {
        label: 'Logout',
        icon: 'pi pi-sign-out',
        styleClass: 'p-menuitem-logout',
        command: () => this.logout()
      }
    ];
  }
  
  logout() {
    this.authService.logout();
  }
}
