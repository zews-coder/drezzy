import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [ CommonModule, RouterModule ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  isLoggedIn = false;

  constructor(private router: Router) {}

  ngOnInit() {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    this.isLoggedIn = !!sessionStorage.getItem('token'); // Check if token exists
  }

  login() {
    this.router.navigate(['/customer-login']);
  }

  logout() {
    sessionStorage.removeItem('token'); // Remove token on logout
    this.isLoggedIn = false;
    this.router.navigate(['/']); // Redirect to home or login page
  }

}
