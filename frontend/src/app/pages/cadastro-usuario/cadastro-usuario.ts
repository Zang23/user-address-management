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

  validarCampos(): boolean {
    const { nome, email, telefone, enderecos } = this.novoUsuario;

    // Validação de Nome (não pode estar em branco)
    if (!nome || nome.trim().length === 0) {
      this.mostrarMensagem('O nome é obrigatório.', 'error');
      return false;
    }

    // Validação de E-mail (deve conter @)
    // Nota: O backend deve ter uma regex mais rigorosa, mas aqui fazemos o check simples solicitado
    if (!email || !email.includes('@')) {
      this.mostrarMensagem('Insira um e-mail válido (ex: usuario@email.com).', 'error');
      return false;
    }

    // Validação de Telefone (exatamente 11 caracteres numéricos)
    // Removemos espaços ou parênteses que o usuário possa ter digitado para contar apenas números
    const telefoneLimpo = telefone.replace(/\D/g, ''); 
    if (telefoneLimpo.length !== 11) {
      this.mostrarMensagem('O telefone deve ter 11 dígitos (DDD + Número).', 'error');
      return false;
    }

    // Validação extra: Pelo menos um endereço preenchido
    for (let end of enderecos) {
      if (!end.cep || !end.numero) {
        this.mostrarMensagem('Preencha o CEP e o Número de todos os endereços.', 'error');
        return false;
      }
    }

    return true;
  }

  salvar() {

    if (!this.validarCampos()) {
      return; // Interrompe a execução se algo estiver errado
    } 

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