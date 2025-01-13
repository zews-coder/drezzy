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
export class InterceptorService implements HttpInterceptor{

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const authToken = 'Bearer ' + sessionStorage.getItem('token');;

    // Clone the request and add the authorization header
    const authReq = req.clone({
      headers: req.headers.set('Authorization', authToken),
    });

    // Pass the modified request to the next handler
    return next.handle(authReq);
  }
}
