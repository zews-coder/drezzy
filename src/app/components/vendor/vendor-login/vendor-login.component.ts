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
    const url = 'http://localhost:8080/auth/user';
    // const url = 'http://user-service-1:8080/auth/user';
    // const url = 'http://user-service:8080/auth/user';

    if(sessionStorage.getItem("token") !== null){
      sessionStorage.removeItem("token");
    }

    this.http.post<{ token: string }>(url, this.loginData).subscribe({
      next: (response) => {
        if (response.token) {
          sessionStorage.setItem('token', response.token);
          
          this.router.navigate(['/vendor-articles']);
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
