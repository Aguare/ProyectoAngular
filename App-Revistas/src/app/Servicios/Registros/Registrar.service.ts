import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Backend } from 'src/app/Objetos/Backend';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';

@Injectable({
  providedIn: 'root'
})
export class RegistrarService {

  constructor(private conexion: HttpClient) { }

  registrarAnunciante(anunciante: Anunciante): Observable<Info> {
    return this.conexion.post<Info>(
      `${Backend.Path}RegistrarAnunciante`,
      anunciante
    );
  }

  registrarEtiqueta(etiqueta: string): Observable<Info> {
    return this.conexion.get<Info>(
      Backend.Path + "ObtenerEtiquetas?Etiqueta=" + etiqueta
    );
  }
}
