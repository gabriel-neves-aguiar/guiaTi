import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';



@Component({
  selector: 'app-plataforma-criador',
  imports: [CommonModule,FormsModule,ReactiveFormsModule],
  templateUrl: './plataforma-criador.component.html',
  styleUrl: './plataforma-criador.component.scss'
})
export class PlataformaCriadorComponent {
  form: FormGroup;

  constructor(private fb: FormBuilder, private http: HttpClient) {
    this.form = this.fb.group({
      titulo: ['', Validators.required],
      descricao: ['', Validators.required],
      categoria: ['', Validators.required],
      modulos: this.fb.array([]),
    });
  }

  getModulos(): FormArray {
    return this.form.get('modulos') as FormArray;
  }

  getAulas(mIndex: number): FormArray {
    return this.getModulos().at(mIndex).get('aulas') as FormArray;
  }

  adicionarModulo(): void {
    this.getModulos().push(this.fb.group({
      nome: ['', Validators.required],
      aulas: this.fb.array([this.novaAula()])
    }));
  }

  removerModulo(mIndex: number): void {
    this.getModulos().removeAt(mIndex);
  }

  adicionarAula(mIndex: number): void {
    this.getAulas(mIndex).push(this.novaAula());
  }

  novaAula(): FormGroup {
    return this.fb.group({
      nome: ['', Validators.required],
      arquivo: [null, Validators.required]
    });
  }

  onArquivoSelecionado(event: any, mIndex: number, aIndex: number): void {
    const file = event.target.files[0];
    const aulas = this.getModulos().at(mIndex).get('aulas') as FormArray;
    aulas.at(aIndex).patchValue({ arquivo: file });
  }

  enviarCurso(): void {
    const formData = new FormData();
    const valor = this.form.value;

    formData.append('titulo', valor.titulo);
    formData.append('descricao', valor.descricao);
    formData.append('categoria', valor.categoria);

    valor.modulos.forEach((modulo: any, mIndex: number) => {
      formData.append(`modulos[${mIndex}].nome`, modulo.nome);

      modulo.aulas.forEach((aula: any, aIndex: number) => {
        formData.append(`modulos[${mIndex}].aulas[${aIndex}].nome`, aula.nome);
        formData.append(`modulos[${mIndex}].aulas[${aIndex}].arquivo`, aula.arquivo);
      });
    });

    this.http.post('http://localhost:8080/cursos', formData, { responseType: 'text' })
  .subscribe(
    (response: string) => {
      console.log('Resposta do backend: ', response);
      // Aqui você pode tratar a resposta como uma string
      alert(response); // Exemplo de exibição
    },
    (error: any) => {
      console.error('Erro ao enviar curso: ', error);
      alert('Erro ao enviar curso: ' + error.message);
    }
  );
  }
}
