import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';

import { AulaDTO, CursoService, CursoDTO, ModuloDTO } from './../../../services/curso/curso.service';
import { IconeExpandirComponent } from '../../../shared/icons/icone-expandir/icone-expandir.component';


@Component({
  selector: 'app-aulas',
  imports: [CommonModule, RouterLink, IconeExpandirComponent],
  templateUrl: './aulas.component.html',
  styleUrl: './aulas.component.scss'
})
export class AulasComponent implements OnInit {

  curso!: CursoDTO;
  aulaSelecionada: AulaDTO | null = null;

  constructor(
    private route: ActivatedRoute,
    private cursoService: CursoService
  ) {}

ngOnInit(): void {
    const cursoId = Number(this.route.snapshot.paramMap.get('id'));
    if (cursoId) {
      this.carregarCurso(cursoId);
    }
  }

carregarCurso(id: number): void {
    this.cursoService.buscarPorId(id).subscribe((curso: CursoDTO) => {
      // adicionando a propriedade `expanded` dinamicamente
      curso.modulos.forEach((modulo: ModuloDTO & { expanded?: boolean }) => {
        modulo.expanded = false;
      });

      this.curso = curso;

      const primeiraAula = curso.modulos[0]?.aulas[0];
      if (primeiraAula) {
        this.selecionarAula(primeiraAula);
      }
    });
  }



  // ngOnInit(): void {
  //   const id = Number(this.route.snapshot.paramMap.get('id'));
  //   this.cursoService.buscarPorId(id).subscribe((curso) => {
  //     this.curso = curso;
  //     const primeiraAula = this.curso.modulos[0]?.aulas[0];
  //     if (primeiraAula) {
  //       this.aulaSelecionada = primeiraAula;
  //     }
  //   });
  // }

  @ViewChild('videoPlayer') videoPlayer!: ElementRef<HTMLVideoElement>;

  selecionarAula(aula: AulaDTO): void {
    this.aulaSelecionada = aula;
    setTimeout(() => {
    this.videoPlayer?.nativeElement.load();
  });
  }

  getUrlVideo(): string {
    if (!this.aulaSelecionada) {
      console.log('URL inválida')
    return '';
  }
    const arquivo = this.aulaSelecionada?.arquivoPath;
    return `http://localhost:8080/api/aulas/video/${arquivo}`;
    }

  onVideoError(){
    console.log('Não foi capaz exibir o video!')
  }
}
