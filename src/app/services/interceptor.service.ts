import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
  HttpEvent,
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = sessionStorage.getItem('token');
  
    if (!token) {
      console.warn('No token found in session storage.');
      return next.handle(req); // Pass the request unmodified if no token is present.
    }
  
    const authToken = 'Bearer ' + token;
  
  
  
    const authReq = req.clone({
      headers: req.headers.set('Authorization', authToken),
    });
  
    return next.handle(authReq);
  }
  
}
