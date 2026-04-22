import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../services/usuario.service';
import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './cadastro-usuario.html'
})
export class CadastroUsuario {

  id: any;
  mensagem = '';
  tipo = '';

  novoUsuario: any = {
    nome: '',
    email: '',
    telefone: '',
    enderecos: [
      { cep: '', numero: '' }
    ]
  };

  constructor(
    private service: UsuarioService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  salvar() {
    if (this.id) {
      this.service.atualizar(this.id, this.novoUsuario).subscribe({
        next: () => {
          this.mostrarMensagem('Usuário atualizado com sucesso!', 'success');
          this.router.navigate(['/']);
        },
        error: (err) => {
          if (err.status === 400) {
            this.mostrarMensagem('CEP inválido!', 'error');
          } else {
            this.mostrarMensagem('Erro ao salvar usuário', 'error');
          }
        } 
      });
    } else {
      this.service.criar(this.novoUsuario).subscribe({
        next: () => {
          this.mostrarMensagem('Usuário criado com sucesso!', 'success');
          this.router.navigate(['/']);
        },
        error: () => {
          this.mostrarMensagem('Erro ao criar usuário', 'error');
        }
      });
    }
  }

  ngOnInit() {
    this.id = this.route.snapshot.paramMap.get('id');

    if (this.id) {
      this.service.buscarPorId(this.id).subscribe(res => {
        this.novoUsuario = res;
      });
    }
  }

  mostrarMensagem(msg: string, tipo: string) {
    this.mensagem = msg;
    this.tipo = tipo;

    setTimeout(() => {
      this.mensagem = '';
    }, 3000);
  }

  voltar() {
    this.router.navigate(['/']);
  }


  adicionarEndereco() {
    this.novoUsuario.enderecos.push({ cep: '', numero: '' });
  }

  removerEndereco(index: number) {
    // Garante que sempre haja pelo menos um endereço na lista
    if (this.novoUsuario.enderecos.length > 1) {
      this.novoUsuario.enderecos.splice(index, 1);
    } else {
      this.mostrarMensagem('O usuário deve ter pelo menos um endereço.', 'error');
    }
  }

}