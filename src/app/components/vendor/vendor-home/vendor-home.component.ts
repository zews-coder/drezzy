import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-vendor-home',
  templateUrl: './vendor-home.component.html',
  styleUrls: ['./vendor-home.component.css'],
  imports: [ RouterModule, CommonModule ]
})
export class VendorHomeComponent {
  isDropdownOpen = false;

  constructor(private router: Router) {}

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  navigateToOrders(status: string) {
    this.router.navigate([status]);
    this.isDropdownOpen = false; // Close dropdown after clicking
  }
}
