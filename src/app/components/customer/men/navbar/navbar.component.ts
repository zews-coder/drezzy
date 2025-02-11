import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../../services/auth.service';

@Component({
  selector: 'app-navbar',
  imports: [ CommonModule, RouterModule ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit{
  isLoggedIn = false;

  constructor(private router: Router, private authService: AuthService) {}

  ngOnInit() {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    this.isLoggedIn = !!this.authService.getJwt();
  }

  login() {
    this.router.navigate(['/customer-login']);
  }

  logout() {
    this.authService.removeJwt();
    this.isLoggedIn = false;
    this.router.navigate(['/']);
  }
}
