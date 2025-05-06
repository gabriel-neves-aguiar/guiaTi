import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CursoService } from '../../services/curso/curso.service';
import { CommonModule } from '@angular/common';
import { Curso } from '../../interfaces/curso';
import { FormsModule} from '@angular/forms';

@Component({
  selector: 'app-cursos',
  imports: [CommonModule, RouterLink, FormsModule],
  templateUrl: './cursos.component.html',
  styleUrl: './cursos.component.scss'
})
export class CursosComponent {
  cursos: Curso[] = [];
  filtro = '';

  constructor(private cursoService: CursoService) {}

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
}

