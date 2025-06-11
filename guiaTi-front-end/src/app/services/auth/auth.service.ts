import { HttpClient } from '@angular/common/http';
import { inject, Injectable,  } from '@angular/core';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth';

  cadastrar(nome: string, email: string, senha: string): Promise<{ mensagem: string }> {
    const body = { nome, email, senha };
    return firstValueFrom(
      this.http.post<{ mensagem: string }>(`${this.apiUrl}/cadastro`, body)
    );
  }

  login(email: string, senha: string) {
    return firstValueFrom(
      this.http.post<{ mensagem: string }>(`${this.apiUrl}/login`, { email, senha })
    ).then(response => {
      localStorage.setItem('usuarioLogado', 'true');;
     return response;
  });
  }

  isLogado(): boolean {
  return localStorage.getItem('usuarioLogado') === 'true';
  }

  logout(): void {
    localStorage.removeItem('usuarioLogado');
  }
}
