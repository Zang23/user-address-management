import { HttpErrorResponse, HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { catchError, throwError } from 'rxjs';
import { ToastService } from '../services/toast.service';

export const HttpErrorInterceptor: HttpInterceptorFn = (req, next) => {

  const toast = inject(ToastService);

  return next(req).pipe(
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

      toast.show(mensagem, 'error');

      return throwError(() => err);
    })
  );
};