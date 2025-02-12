import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NavbarComponent } from '../navbar/navbar.component';
import { Router } from '@angular/router';
import { AuthService } from '../../../../services/auth.service';
import { SignUpDTO } from '../../../../models/User';

@Component({
  selector: 'app-user',
  imports: [NavbarComponent, CommonModule, FormsModule],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent implements OnInit{
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
      phone: ''
    },
    cardInfo: {
      cardHolder: '',
      cardNumber: '',
      expiryDate: '',
      cvv: ''
    }
  };
  
  constructor(private http: HttpClient, private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if(this.authService.getJwt() == ''){
      this.router.navigate(['/customer-login']);
    }else {
      this.fetchUserData();
    }
  }

  private getAuthHeaders() {
    const token = this.authService.getJwt();
      return new HttpHeaders({
       'Authorization': token ? `Bearer ${token}` : '',
     });
  }

  fetchUserData(): void {
    this.http.get<SignUpDTO>('http://localhost:8080/api/v1/customer/getOne', { headers: this.getAuthHeaders() }).subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (err) => {
        console.error('Error fetching user data:', err);
      }
    });
  }

  saveChanges(): void {
    this.http.put<SignUpDTO>('http://localhost:8080/api/v1/customer/updateOne', this.user, { headers: this.getAuthHeaders() }).subscribe({
      next: (response) => {
        console.log('User data updated successfully:', response);
        alert('Changes saved successfully!');
      },
      error: (err) => {
        console.error('Error updating user data:', err);
        alert('Failed to save changes.');
      }
    });
  }
}
