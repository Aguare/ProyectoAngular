import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Usuario } from 'src/app/Objetos/Usuario';
import { Backend } from 'src/app/Objetos/Backend';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private conexion: HttpClient) { }

  validarUsuario(nombreUsuario: string, password: string): Observable<Usuario> {
    return this.conexion.post<Usuario>(
      `${Backend.Path}InicioSesion`,
      new Usuario("0", nombreUsuario, password)
    );
  }
}
