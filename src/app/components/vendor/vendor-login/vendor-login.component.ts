import { Component } from '@angular/core';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-vendor-login',
  imports: [HttpClientModule, FormsModule],
  templateUrl: './vendor-login.component.html',
  styleUrl: './vendor-login.component.css'
})
export class VendorLoginComponent {
  loginData = {
    email: '',
    password: '',
  };

  constructor(private http: HttpClient, private router: Router) {}

  //login function
  onSubmit() {
    const url = 'http://localhost:8080/auth/vendor';
    this.http.post<{ token: string }>(url, this.loginData).subscribe({
      next: (response) => {
        if (response.token) {
          // Save the token in session storage
          sessionStorage.setItem('token', response.token);
          console.log('Token saved to session storage:', response.token);
          this.router.navigate(['/vendor-home']);
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
