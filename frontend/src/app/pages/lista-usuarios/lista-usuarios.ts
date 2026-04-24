import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioService } from '../../services/usuario.service';
import { Router } from '@angular/router';
import { ChangeDetectorRef } from '@angular/core';
import { delay, finalize } from 'rxjs/operators';

@Component({
  selector: 'app-lista-usuarios',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lista-usuarios.html'
})

export class ListaUsuarios implements OnInit {

  usuarios: any[] = [];
  mensagem = '';
  tipo = '';

  loading = false;

  constructor(
    private service: UsuarioService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.carregar();
  }

  mostrarMensagem(msg: string, tipo: string) {
    this.mensagem = msg;
    this.tipo = tipo;

    setTimeout(() => {
      this.mensagem = '';
    }, 3000);
  }
 

  carregar() {
    this.loading = true;

    this.service.listar().pipe(
      finalize(() => {
        this.loading = false;
        this.cdr.detectChanges(); 
      })
    ).subscribe({
      next: (res) => {
        this.usuarios = res as any[];
      },
      error: (err) => {
        const mensagemBackend = err.error;

        if(mensagemBackend){
          this.mostrarMensagem(mensagemBackend, 'error');
        }else{
          this.mostrarMensagem('Erro ao carregar usuarios', 'error');
        }

      }
    });
  }

  verDetalhe(id: number) {
    this.router.navigate(['/detalhe', id]);
  }

  irCadastro() {
    this.router.navigate(['/cadastro']);
  }

  deletar(id: number) {
    this.service.deletar(id).subscribe({
      next: () => {
        this.mostrarMensagem('Usuário deletado com sucesso!', 'success');
        this.carregar();
      },
      error: (err) => {
        const mensagemBackend = err.error;

        if (mensagemBackend) {
          this.mostrarMensagem(mensagemBackend, 'error');
        } else {
          this.mostrarMensagem('Erro ao deletar usuário', 'error');
        }
      }
    });
  }

  editar(id: number) {
    this.router.navigate(['/editar', id]);
  }
}