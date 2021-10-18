import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Backend } from 'src/app/Objetos/Backend';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
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

  public subirArchivo(fileToUpload: File): Observable<Info> {
    const formData: FormData = new FormData();
    formData.append("datafile", fileToUpload, fileToUpload.name);
    return this.conexion.post<Info>(Backend.Path + "SubirArchivo", formData);
  }

  public obtenerStringArchivo(file: File): any {
    this.convertir.extraerBase64(file).then((respuesta: any) => {
      return respuesta;
    });
  }
}
