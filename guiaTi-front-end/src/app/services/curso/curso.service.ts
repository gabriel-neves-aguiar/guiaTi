import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Curso } from '../../interfaces/curso';

export interface AulaDTO {
  id: number;
  nome: string;
  arquivoPath: string;
}

export interface ModuloDTO {
  id:number;
  nome: string;
  aulas: AulaDTO[];
  expanded:boolean;
}

export interface CursoDTO {
  id:number;
  titulo: string;
  descricao: string;
  categoria: string;
  modulos: ModuloDTO[];
}

@Injectable({ providedIn: 'root' })
export class CursoService {
  private apiUrl = 'http://localhost:8080/api/cursos';

  constructor(private http: HttpClient) {}

  listarCursos(): Observable<Curso[]> {
    return this.http.get<Curso[]>(this.apiUrl);
  }

  getCursos(): Observable<Curso[]> {
    return this.http.get<Curso[]>('http://localhost:8080/api/cursos');
  }
  buscarPorId(id: number): Observable<CursoDTO> {
    return this.http.get<CursoDTO>(`${this.apiUrl}/${id}`);
  }
}
