import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Backend } from 'src/app/Objetos/Backend';
import { Cliente } from 'src/app/Objetos/Cliente';
import { Info } from 'src/app/Objetos/Info';

@Injectable({
  providedIn: 'root'
})
export class RegistrarUsuarioService {

  constructor(private conexion: HttpClient) { }

  registrarUsuario(cliente: Cliente): Observable<Info> {
    return this.conexion.post<Info>(
      `${Backend.Path}RegistrarUsuario`,
      cliente
    );
  }

}
