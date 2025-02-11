import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root' // This makes the service available globally
})
export class AuthService {
  private jwt: string = ''; // Private field to store JWT

  constructor() {}

  // Function to set the JWT token
  setJwt(token: string): void {
    this.jwt = token;
  }

  // Function to get the JWT token
  getJwt(): string {
    return this.jwt;
  }

  removeJwt(){
    this.jwt = '';
  }
}

