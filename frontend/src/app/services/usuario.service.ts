import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {

  private api = 'http://localhost:8080/usuarios';

  constructor(private http: HttpClient) {}

  listar() {
    return this.http.get(this.api);
  }

  criar(usuario: any){
    return this.http.post(this.api, usuario);
  }

  buscarPorId(id: any){
    return this.http.get(`${this.api}/${id}`);
  }

  deletar(id: number){
    return this.http.delete(`${this.api}/${id}`);
  }

  atualizar(id: number, usuario: any) {
    return this.http.put(`${this.api}/${id}`, usuario);
  }

}