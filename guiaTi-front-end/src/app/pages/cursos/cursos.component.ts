import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CursoService } from '../../services/curso/curso.service';
import { CommonModule } from '@angular/common';
import { Curso } from '../../interfaces/curso';
import { FormsModule} from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-cursos',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent implements OnInit {
  cursos: Curso[] = [];
  filtro = '';

  constructor(private cursoService: CursoService, private authService: AuthService, private router: Router) {}

  ngOnInit() {
    this.cursoService.listarCursos().subscribe((res) => {
      this.cursos = res;
    });

  }

  get cursosFiltrados(): Curso[] {
    return this.cursos.filter((curso) =>
      curso.titulo.toLowerCase().includes(this.filtro.toLowerCase())
    );
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['']);
  }
}

