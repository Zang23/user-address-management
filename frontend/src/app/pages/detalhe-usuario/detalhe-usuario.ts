import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UsuarioService } from '../../services/usuario.service';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef } from '@angular/core';


@Component({
  standalone: true,
  imports: [CommonModule],
  templateUrl: './detalhe-usuario.html'
})
export class DetalheUsuario implements OnInit {

  usuario: any;

  constructor(
    private route: ActivatedRoute,
    private service: UsuarioService,
    private cdr: ChangeDetectorRef,
    private router: Router
    
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.service.buscarPorId(id).subscribe(res => {
      this.usuario = res;
      this.cdr.detectChanges();
    });
  }

  voltar() {
    this.router.navigate(['/']);
  }

}