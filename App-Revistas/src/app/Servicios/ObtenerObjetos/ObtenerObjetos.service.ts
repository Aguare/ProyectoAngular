import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Backend } from 'src/app/Objetos/Backend';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from '../Almacenamiento/AlmacenamientoLocal.service';

@Injectable({
  providedIn: 'root'
})
export class ObtenerObjetosService {

  constructor(
    private conexion: HttpClient
  ) { }

  obtenerEtiquetas(): Observable<Anunciante[]> {
    return this.conexion.post<Anunciante[]>(
      `${Backend.Path}ObtenerAnunciantes`,
      ""
    );
  }

  obtenerRevistasPorEditor(usuario: Usuario): Observable<Revista[]> {
    return this.conexion.get<Revista[]>(Backend.Path + "RegistrarRevista?usuario=" + usuario.nombreUsuario);
  }

  obtenerRevistasLector(usuario: Usuario): Observable<Revista[]> {
    return this.conexion.get<Revista[]>(Backend.Path + "RevistasInicio?usuario=" + usuario.nombreUsuario);
  }
}
