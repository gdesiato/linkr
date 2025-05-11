import { Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { DomainListComponent } from './domain/domain-list/domain-list.component';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'domains', component: DomainListComponent }
];
