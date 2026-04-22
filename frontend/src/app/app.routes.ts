import { Routes } from '@angular/router';
import { ListaUsuarios } from './pages/lista-usuarios/lista-usuarios';
import { CadastroUsuario } from './pages/cadastro-usuario/cadastro-usuario';
import { DetalheUsuario } from './pages/detalhe-usuario/detalhe-usuario';


export const routes: Routes = [

    { path: '', component: ListaUsuarios},
    { path: 'cadastro', component: CadastroUsuario },
    { path: 'detalhe/:id', component: DetalheUsuario },
    { path: 'editar/:id', component: CadastroUsuario }

];
