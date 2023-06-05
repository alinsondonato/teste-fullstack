import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BeneficiarioListComponent } from './beneficiarios/beneficiario-list/beneficiario-list.component';
import { BeneficiarioItemComponent } from './beneficiarios/beneficiario-item/beneficiario-item.component';
import { LoginComponent } from './public/login/login.component';
import { AuthGuard } from './auth/auth.guard';
import { PlanoListComponent } from './planos/plano-list/plano-list.component';
import { PlanoItemComponent } from './planos/plano-item/plano-item.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: '', redirectTo: '/beneficiarios', pathMatch: 'full' },
  { path: 'beneficiarios/novo', component: BeneficiarioItemComponent, canActivate: [AuthGuard]  },
  { path: 'beneficiarios/:id', component: BeneficiarioItemComponent, canActivate: [AuthGuard]  },
  { path: 'beneficiarios', component: BeneficiarioListComponent, canActivate: [AuthGuard] },
  { path: 'planos/novo', component: PlanoItemComponent, canActivate: [AuthGuard]  },
  { path: 'planos/:id', component: PlanoItemComponent, canActivate: [AuthGuard]  },
  { path: 'planos', component: PlanoListComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
