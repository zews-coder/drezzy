import { Component } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../../services/auth.service';
import { NavbarComponent } from "../men/navbar/navbar.component";

@Component({
  selector: 'app-customer-login',
  imports: [HttpClientModule, FormsModule, NavbarComponent, RouterModule],
  templateUrl: './customer-login.component.html',
  styleUrl: './customer-login.component.css'
})
export class CustomerLoginComponent {
  loginData = {
    email: '',
    password: '',
  };

  constructor(private http: HttpClient, private router: Router, private authService: AuthService) {}

  //login function
  onSubmit() {
    const url = 'http://localhost:8080/auth/customer';
    if (this.authService.getJwt() === '') {
      this.authService.removeJwt();
    }
    
    this.http.post<{ token: string }>(url, this.loginData).subscribe({
      next: (response) => {
        if (response.token) {
          this.authService.setJwt(response.token);
          this.router.navigate(['/']);
        } else {
          console.error('Token not found in response');
        }
      },
      error: (error) => {
        console.error('Login failed', error);
        alert('Login failed. Please try again.');
      },
    });
  }
}
