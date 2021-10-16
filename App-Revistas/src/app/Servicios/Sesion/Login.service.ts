import { ErrorHandler, Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Usuario } from 'src/app/Objetos/Usuario';
import { Backend } from 'src/app/Objetos/Backend';
import { RedireccionarService } from '../Redireccionar.service';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(
    private conexion: HttpClient
  ) { }

  validarUsuario(nombreUsuario: string, password: string): Observable<Usuario> {
    return this.conexion.post<Usuario>(
      `${Backend.Path}InicioSesion`,
      new Usuario("0", nombreUsuario, password)
    );
  }

}
