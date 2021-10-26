import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Backend } from 'src/app/Objetos/Backend';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';

@Injectable({
  providedIn: 'root'
})
export class ObtenerEtiquetasService {

  constructor(private conexion: HttpClient) { }

  obtenerEtiquetas(): Observable<Etiqueta[]> {
    return this.conexion.post<Etiqueta[]>(
      `${Backend.Path}ObtenerEtiquetas`,
      ""
    );
  }

}
