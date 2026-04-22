import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from './services/usuario.service';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {

  usuarios: any[] = [];

  novoUsuario: any = {
    nome: '',
    email: '',
    telefone: '',
    enderecos: [
      {
        cep: '',
        numero: ''
      }
    ]
  };

  constructor(
    private usuarioService: UsuarioService,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.carregarUsuarios();
  }

  carregarUsuarios() {
    this.usuarioService.listar().subscribe(res => {
      this.usuarios = res as any[];
      this.cdr.detectChanges();
    });
  }

  salvar() { 
    this.usuarioService.criar(this.novoUsuario).subscribe({
      next: () => {
        console.log('Usuário criado');

        // limpa formulário
        this.novoUsuario = {
          nome: '',
          email: '',
          telefone: '',
          enderecos: [
            {
              cep: '',
              numero: ''
            }
          ]
        };

        // recarrega lista
        this.carregarUsuarios();

        this.cdr.detectChanges();

      },
      error: (err) => console.log(err)
    });
  }
}