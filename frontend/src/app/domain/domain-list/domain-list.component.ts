import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DomainService, Domain } from '../../services/domain.service';

@Component({
  standalone: true,
  selector: 'app-domain-list',
  templateUrl: './domain-list.component.html',
  styleUrls: ['./domain-list.component.scss'],
  imports: [CommonModule]
})
export class DomainListComponent implements OnInit {
  domains: Domain[] = [];
  loading = true;
  error = '';

  constructor(private domainService: DomainService) {}

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
}
