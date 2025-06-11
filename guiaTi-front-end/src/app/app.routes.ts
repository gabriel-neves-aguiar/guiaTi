import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CursosComponent } from './pages/cursos/cursos.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { Error404Component } from './pages/error404/error404.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { CadastroComponent } from './pages/auth/cadastro/cadastro.component';
import { AuthComponent } from './pages/auth/auth.component';
import { PlataformaCriadorComponent } from './pages/plataforma-criador/plataforma-criador.component';
import { AulasComponent } from './pages/cursos/aulas/aulas.component';

export const routes: Routes = [
  {
    path:'',
    component: HomeComponent,
    title:'Aprenda sobre tecnologia da forma correta | GuiaTI'
  },
  {
    path:'inicio',
    component: HomeComponent
  },
  {
    path: 'auth',
    component: AuthComponent, children:[
      {
        path: 'cadastro',
        component: CadastroComponent,
        title:'Cadastro | GuiaTI'

      },
      {
        path: 'login',
        component: LoginComponent,
        title:'Login | GuiaTI'
      },
      { path: '', redirectTo: 'login', pathMatch: 'full' }]
  },
  {
    path: 'cursos',
    loadComponent: () => import('./pages/cursos/cursos.component').then(m => m.CursosComponent),
    title:'Cursos disponíveis | GuiaTI'
  },
  {
    path: 'cursos/:id/aulas',
    component: AulasComponent,
  },
  {
    path:'criador',
    component: PlataformaCriadorComponent,
    title: 'Plataforma do criador | GuiaTI'
  },
  {
    path:'perfil',
    component: PerfilComponent,
    title:'Meu perfil | GuiaTI'
  },
  {
    path:'**',
    component: Error404Component,
    title: "Caminho não encontrado"
  },
];
