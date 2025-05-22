import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { DomainService, Domain } from '../../services/domain.service';

@Component({
  standalone: true,
  selector: 'app-domain-list',
  templateUrl: './domain-list.component.html',
  styleUrls: ['./domain-list.component.scss'],
  imports: [CommonModule, FormsModule]
})
export class DomainListComponent implements OnInit {
  domains: Domain[] = [];
  loading = true;
  error = '';
  newDomainUrl = '';

  constructor(
    private domainService: DomainService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.domainService.getDomains().subscribe({
      next: (data) => {
        this.domains = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Could not load domains';
        console.error(err);
        this.loading = false;
      }
    });
  }

  addDomain(): void {
    if (!this.newDomainUrl.trim()) return;

    this.domainService.addDomain(this.newDomainUrl).subscribe({
      next: (domain) => {
        this.domains.push(domain);
        this.newDomainUrl = '';
      },
      error: (err) => {
        this.error = 'Failed to add domain.';
        console.error(err);
      }
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}

