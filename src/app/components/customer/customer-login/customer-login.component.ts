import { Component } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customer-login',
  imports: [HttpClientModule, FormsModule],
  templateUrl: './customer-login.component.html',
  styleUrl: './customer-login.component.css'
})
export class CustomerLoginComponent {
  loginData = {
    email: '',
    password: '',
  };

  constructor(private http: HttpClient, router: Router) {}

  //login function
  onSubmit() {
    const url = 'http://localhost:8080/auth/customer';
    this.http.post<{ token: string }>(url, this.loginData).subscribe({
      next: (response) => {
        if (response.token) {
          // Save the token in session storage
          sessionStorage.setItem('token', response.token);
          console.log('Token saved to session storage:', response.token);
          alert('Login successful!');
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
