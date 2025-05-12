import { Injectable, PLATFORM_ID, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({
  providedIn: 'root' // This makes the service available globally
})
export class AuthService {
  private jwt: string = '';

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  // Function to set the JWT token
  setJwt(token: string): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.setItem("token", token);
    }
    this.jwt = token;
  }

  // Function to get the JWT token
  getJwt(): string {
    if (isPlatformBrowser(this.platformId)) {
      return sessionStorage.getItem("token") ?? this.jwt;
    }
    return this.jwt;
  }

  // Function to remove the JWT token
  removeJwt(): void {
    if (isPlatformBrowser(this.platformId)) {
      sessionStorage.removeItem("token");
    }
    this.jwt = '';
  }
}
