import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from '../../interfaces/curso';

export interface AulaDTO {
  titulo: string;
}

export interface ModuloDTO {
  nome: string;
  aulas: AulaDTO[];
}

export interface CursoDTO {
  titulo: string;
  descricao: string;
  categoria: string;
  modulos: ModuloDTO[];
}

@Injectable({ providedIn: 'root' })
export class CursoService {
  private apiUrl = 'http://localhost:8080/cursos';

  constructor(private http: HttpClient) {}

  listarCursos(): Observable<Curso[]> {
    return this.http.get<Curso[]>(this.apiUrl);
  }

  getCursos(): Observable<Curso[]> {
    return this.http.get<Curso[]>('http://localhost:8080/cursos');
  }
  buscarPorId(id: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}
