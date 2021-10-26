import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Anuncio } from 'src/app/Objetos/Anuncio';
import { Backend } from 'src/app/Objetos/Backend';
import { Comentario } from 'src/app/Objetos/Comentario';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { Reaccion } from 'src/app/Objetos/Reaccion';
import { Revista } from 'src/app/Objetos/Revista';
import { Suscripcion } from 'src/app/Objetos/Suscripcion';
import { Usuario } from 'src/app/Objetos/Usuario';
import { ValorSistema } from 'src/app/Objetos/ValorSistema';
import { Base64Service } from '../ObtenerObjetos/Base64.service';

@Injectable({
  providedIn: 'root'
})
export class RegistrarService {

  constructor(
    private conexion: HttpClient,
    private convertir: Base64Service
  ) { }

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

  registrarRevista(revista: Revista, archivo: File): Observable<Info> {
    let formData: FormData = new FormData();
    formData.append("revista", JSON.stringify(revista));
    formData.append("archivo", archivo, archivo.name);
    return this.conexion.post<Info>(Backend.Path + "RegistrarRevista", formData);
  }

  public subirArchivo(fileToUpload: File, usuario: Usuario): Observable<Info> {
    const formData: FormData = new FormData();
    formData.append("Archivo", fileToUpload, fileToUpload.name);
    formData.append("Usuario", JSON.stringify(usuario));
    return this.conexion.post<Info>(Backend.Path + "SubirArchivo", formData);
  }

  public obtenerStringArchivo(file: File): any {
    this.convertir.extraerBase64(file).then((respuesta: any) => {
      return respuesta;
    });
  }

  public registrarReaccion(reaccion: Reaccion): Observable<Info> {
    let formData = new FormData();
    formData.append("Reaccion", JSON.stringify(reaccion));
    return this.conexion.post<Info>(Backend.Path + "RegistrarInteraccion", formData);
  }

  public registrarComentario(comentario: Comentario): Observable<Info> {
    let formData = new FormData();
    formData.append("Comentario", JSON.stringify(comentario));
    return this.conexion.post<Info>(Backend.Path + "RegistrarComentario", formData);
  }

  public registrarSuscripcion(suscripcion: Suscripcion): Observable<Info> {
    let formData = new FormData();
    formData.append("Suscripcion", JSON.stringify(suscripcion));
    return this.conexion.post<Info>(Backend.Path + "Suscripciones", formData);
  }

  public registrarEstadoRevista(revista: Revista): Observable<Info> {
    let formData = new FormData();
    formData.append("Revista", JSON.stringify(revista));
    return this.conexion.post<Info>(Backend.Path + "EstadoRevista", formData);
  }

  public registrarCambioPrecioCosto(revista: Revista): Observable<Info> {
    let formData = new FormData();
    formData.append("Revista", JSON.stringify(revista));
    return this.conexion.post<Info>(Backend.Path + "CambioRevista", formData);
  }

  public registrarComision(valor: ValorSistema): Observable<Info> {
    let formData = new FormData();
    formData.append("Comision", JSON.stringify(valor));
    return this.conexion.post<Info>(Backend.Path + "ObtenerComision", formData);
  }

  public registrarAnuncio(anuncio:Anuncio, dias: number): Observable<Info>{
    let formData = new FormData();
    formData.append("Anuncio", JSON.stringify(anuncio));
    formData.append("Dias", JSON.stringify(dias));
    return this.conexion.post<Info>(Backend.Path + "RegistrarAnuncio", formData);
  }

  public registrarCambioAnuncio(anuncio:Anuncio): Observable<Info>{
    let formData = new FormData();
    formData.append("Anuncio", JSON.stringify(anuncio));
    return this.conexion.post<Info>(Backend.Path + "CambioAnuncio", formData);
  }

  public registrarCambioRevista(revista: Revista, opcion: number): Observable<Info>{
    let formData = new FormData();
    formData.append("Revista", JSON.stringify(revista));
    formData.append("Opcion", JSON.stringify(opcion));
    return this.conexion.post<Info>(Backend.Path + "CambioRevista", formData);
  }
}
