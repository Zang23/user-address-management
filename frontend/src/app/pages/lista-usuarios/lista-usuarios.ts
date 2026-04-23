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

  loading = false;

  constructor(
    private service: UsuarioService,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.carregar();
  }

 

  carregar() {
    this.loading = true;

    this.service.listar().pipe(
      // delay(1000), // Remova ou comente esta linha para parar de simular atraso
      finalize(() => {
        this.loading = false;
        this.cdr.detectChanges(); 
      })
    ).subscribe({
      next: (res) => {
        this.usuarios = res as any[];
      },
      error: (err) => {
        console.error('Erro ao carregar usuários', err);
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
    this.service.deletar(id).subscribe(() => {
      this.carregar(); 
    });
  }

  editar(id: number) {
    this.router.navigate(['/editar', id]);
  }
}