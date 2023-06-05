import { Component } from '@angular/core';
import { AuthService } from './auth/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'teste-unimed';

  constructor(
    private authService: AuthService) {

    if (authService.token) {
      this.authService.showHeader = true;
    }
  }

  get showHeader() {
    return this.authService.showHeader;
  }
}
