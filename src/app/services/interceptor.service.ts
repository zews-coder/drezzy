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
    const authToken = 'Bearer ' + sessionStorage.getItem('token');
    if (!authToken) {
      console.error('No auth token found!');
    }

    const authReq = req.clone({
      setHeaders: {
        Authorization: authToken,
      },
    });

    return next.handle(authReq);
  }
}
