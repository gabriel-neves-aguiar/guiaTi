import { Title } from '@angular/platform-browser';
import { CommonModule } from '@angular/common';
import { AuthService } from './../../../services/auth/auth.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule,RouterLink,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  constructor(private fb: FormBuilder, private AuthService: AuthService,private router: Router, private titleService: Title) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  ngOnInit(): void {
      this.titleService.setTitle('Login | GuiaTI')
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const { email, senha } = this.loginForm.value;

      this.AuthService.login(email, senha)
        .then(response => {
          console.log('Login bem-sucedido:', response);
          this.router.navigate(['/cursos']);
        })
        .catch(error => {
          console.error('Erro no login:', error);

        });
    } else {
      this.loginForm.markAllAsTouched();
    }
  }
}
