import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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

  constructor(private domainService: DomainService) {}

  newDomainUrl = '';

addDomain(): void {
  if (!this.newDomainUrl.trim()) return;

  this.domainService.addDomain(this.newDomainUrl).subscribe({
    next: (domain) => {
      this.domains.push(domain); // Add it to the list
      this.newDomainUrl = '';    // Clear the input
    },
    error: (err) => {
      this.error = 'Failed to add domain.';
      console.error(err);
    }
  });
}


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
