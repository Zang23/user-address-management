import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';
import { finalize } from 'rxjs/operators';


@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalhe-usuario.html'
})
export class DetalheUsuario implements OnInit {

  usuario: any;
  loading = false;
  mensagem = '';
  tipo = '';

  constructor(
    private route: ActivatedRoute,
    private service: UsuarioService,
    private cdr: ChangeDetectorRef,
    private router: Router
    
  ) {}

   mostrarMensagem(msg: string, tipo: string) {
    this.mensagem = msg;
    this.tipo = tipo;

    setTimeout(() => {
      this.mensagem = '';
    }, 3000);
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.loading = true;

    this.service.buscarPorId(id).subscribe({
      next: (res) => {
        this.usuario = res;
        this.loading = false;
        this.cdr.detectChanges();
      },
      error: (err) => {
        const mensagemBackend = typeof err.error === 'string' ? err.error : null;

        if (mensagemBackend) {
          this.mostrarMensagem(mensagemBackend, 'error');
        } else {
          this.mostrarMensagem('Erro ao carregar usuário', 'error');
        }

        this.loading = false;

        setTimeout(() => {
          this.router.navigate(['/']);
        }, 2000);
      }
    });
  }
  voltar() {
    this.router.navigate(['/']);
  }

}