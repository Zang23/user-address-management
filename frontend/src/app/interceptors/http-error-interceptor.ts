import { Injectable } from '@angular/core';
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpErrorResponse
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { ToastService } from '../services/toast';

@Injectable()
export class HttpErrorInterceptor implements HttpInterceptor {

  constructor(private toast: ToastService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {

        let mensagem = 'Erro inesperado';

        const backend = err.error;

        if (typeof backend === 'string') {
          mensagem = backend;
        } 
        else if (backend?.message) {
          mensagem = backend.message;
        } 
        else if (backend?.error) {
          mensagem = backend.error;
        }

        this.toast.show(mensagem, 'error');

        return throwError(() => err);
      })
    );
  }
}