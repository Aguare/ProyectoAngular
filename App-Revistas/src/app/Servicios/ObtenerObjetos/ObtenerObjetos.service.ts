import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Backend } from 'src/app/Objetos/Backend';
import { Cliente } from 'src/app/Objetos/Cliente';
import { Comentario } from 'src/app/Objetos/Comentario';
import { Perfil } from 'src/app/Objetos/Perfil';
import { Reaccion } from 'src/app/Objetos/Reaccion';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { ValorSistema } from 'src/app/Objetos/ValorSistema';
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

  obtenerPerfilUsuario(usuario: string): Observable<Perfil> {
    return this.conexion.get<Perfil>(Backend.Path + "ObtenerPerfil?usuario=" + usuario);
  }

  obtenerCliente(usuario: string): Observable<Cliente> {
    let formData = new FormData();
    formData.append("Usuario", usuario);
    return this.conexion.post<Cliente>(Backend.Path + "ObtenerPerfil", formData);
  }

  obtenerReacciones(idRevista: number): Observable<Reaccion[]> {
    return this.conexion.get<Reaccion[]>(Backend.Path + "RegistrarInteraccion?idRevista=" + idRevista);
  }

  obtenerComentarios(idRevista: number): Observable<Comentario[]> {
    return this.conexion.get<Comentario[]>(Backend.Path + "RegistrarComentario?idRevista=" + idRevista);
  }

  obtenerRevista(idRevista: string): Observable<Revista> {
    return this.conexion.get<Revista>(Backend.Path + "ObtenerRevista?idRevista=" + idRevista);
  }

  obtenerRevistaSuscripciones(nombreUsuario: string): Observable<Revista[]> {
    return this.conexion.get<Revista[]>(Backend.Path + "Suscripciones?usuario=" + nombreUsuario);
  }

  obtenerValorSistema(): Observable<ValorSistema> {
    return this.conexion.get<ValorSistema>(Backend.Path + "ObtenerComision");
  }
}
