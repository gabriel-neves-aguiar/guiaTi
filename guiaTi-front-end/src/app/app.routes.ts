import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { CursosComponent } from './pages/cursos/cursos.component';
import { PerfilComponent } from './pages/perfil/perfil.component';
import { Error404Component } from './pages/error404/error404.component';
import { LoginComponent } from './pages/auth/login/login.component';
import { CadastroComponent } from './pages/auth/cadastro/cadastro.component';
import { AuthComponent } from './pages/auth/auth.component';
import { PlataformaCriadorComponent } from './pages/plataforma-criador/plataforma-criador.component';

export const routes: Routes = [
  {
    path:'',
    component: HomeComponent
  },
  {
    path: 'auth',
    component: AuthComponent, children:[
      {
        path: 'cadastro',
        component: CadastroComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      { path: '', redirectTo: 'login', pathMatch: 'full' }]
  },
  {
    path:'cursos',
    component: CursosComponent
  },
  {
    path:'criador',
    component: PlataformaCriadorComponent
  },
  {
    path:'perfil',
    component: PerfilComponent
  },
  {
    path:'**',
    component: Error404Component
  },
];
