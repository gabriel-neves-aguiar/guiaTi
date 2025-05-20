import { AuthService } from './../../../services/auth/auth.service';
import { Router, RouterLink } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-cadastro',
  imports: [ReactiveFormsModule,RouterLink,CommonModule],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.scss'
})
export class CadastroComponent implements OnInit {
  cadastroForm: FormGroup<{
    nome: FormControl<string>;
    email: FormControl<string>;
    senha: FormControl<string>;
    confirmarSenha: FormControl<string>;
  }>;

  mensagemErro: string | null = null;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private titleService: Title,
  ) {
    this.cadastroForm = this.fb.group(
      {
        nome: this.fb.control('', Validators.required),
        email: this.fb.control('', [Validators.required, Validators.email]),
        senha: this.fb.control('', [Validators.required, Validators.minLength(6)]),
        confirmarSenha: this.fb.control('', Validators.required),
      },
      { validators: this.senhasIguais }
    );
  }

  ngOnInit(): void{
    this.titleService.setTitle('Cadastro | GuiaTI')
  }

  senhasIguais(group: FormGroup) {
    const senha = group.get('senha')?.value;
    const confirmarSenha = group.get('confirmarSenha')?.value;
    return senha === confirmarSenha ? null : { senhasDiferentes: true };
  }

  onSubmit() {
    if (this.cadastroForm.valid) {
      const nome = this.cadastroForm.get('nome')?.value ?? '';
      const email = this.cadastroForm.get('email')?.value.trim() ?? '';
      const senha = this.cadastroForm.get('senha')?.value.trim() ?? '';

      this.authService
        .cadastrar(nome, email, senha)
        .then((res) => {
          console.log('Cadastro realizado:', res?.mensagem);
          alert(res?.mensagem ?? 'Cadastro realizado com sucesso!');
          this.router.navigate(['/auth/login']);

        })
        .catch((error) => {
          const erroMsg = this.traduzirErro(error);
          console.error('Erro no cadastro:', erroMsg);
          alert(erroMsg);
        });
    } else {
      this.cadastroForm.markAllAsTouched();
    }
  }

  traduzirErro(error: any): string {
    if (error.status === 400) {
      return error.error?.mensagem || 'Dados inválidos. Verifique os campos.';
    } else if (error.status === 409) {
      return 'Esse e-mail já está em uso.';
    } else if (error.status === 0) {
      return 'Não foi possível conectar ao servidor.';
    }
    return 'Erro desconhecido. Tente novamente mais tarde.';
  }
}
