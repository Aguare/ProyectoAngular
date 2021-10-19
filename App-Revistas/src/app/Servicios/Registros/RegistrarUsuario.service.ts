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

  registrarUsuario(cliente: Cliente, imagen: File): Observable<Info> {
    let formData = new FormData();
    formData.append("Cliente", JSON.stringify(cliente));
    formData.append("Foto", imagen, imagen.name);
    return this.conexion.post<Info>(Backend.Path + "RegistrarUsuario", formData);
  }

}
