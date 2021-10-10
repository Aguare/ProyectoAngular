import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Backend } from 'src/app/Objetos/Backend';

@Injectable({
  providedIn: 'root'
})
export class ObtenerObjetosService {

  constructor(private conexion: HttpClient) { }

  obtenerEtiquetas(): Observable<Anunciante[]> {
    return this.conexion.post<Anunciante[]>(
      `${Backend.Path}ObtenerAnunciantes`,
      ""
    );
  }
}
