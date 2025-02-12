import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { SignUpDTO } from '../../../models/User';
import { NavbarComponent } from "../men/navbar/navbar.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-in',
  imports: [NavbarComponent, CommonModule, FormsModule],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.css'
})
export class SignInComponent {
  user: SignUpDTO = {
    username: '',
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    address: {
      street: '',
      city: '',
      state: '',
      zip: '',
      phone: '',
    },
    cardInfo: {
      cardHolder: '',
      cardNumber: '',
      expiryDate: '',
      cvv: '',
    }
  };

  confirmPassword: string = '';

  constructor(private http: HttpClient, private router: Router) {}

  onSubmit() {
    if (this.user.password !== this.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    const apiUrl = "http://localhost:8080/api/v1/customer/createOne";
    this.http.post(apiUrl, this.user).subscribe({
      next: (response) => {
        console.log("User registered successfully!", response);
        this.router.navigate(['/customer-login']);
      },
      error: (error) => {
        console.error("Error during registration:", error);
        alert("Registration failed!");
      }
    });
  }
}
