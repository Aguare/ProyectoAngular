import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Usuario } from 'src/app/Objetos/Usuario';
import { Backend } from 'src/app/Objetos/Backend';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient) { }

  validarUsuario(nombreUsuario: string, _password: string): Observable<Usuario> {
    return this._http.post<Usuario>(
      `${Backend.Path}InicioSesion`,
      new Usuario(nombreUsuario, _password)
    );
  }

}
