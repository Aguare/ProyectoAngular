import { Injectable } from '@angular/core';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoPrincipalService } from './AlmacenamientoPrincipal.service';

@Injectable({
  providedIn: 'root'
})
export class AlmacenamientoLocalService {

  private almacenamientoLocal: Storage;

  constructor(private almacenamientoL: AlmacenamientoPrincipalService) {
    this.almacenamientoLocal = this.almacenamientoL.getAlmacenamientoLocal();
  }

  agregar(dato: any, llave: string) {
    this.almacenamientoLocal.setItem(llave, JSON.stringify(dato));
  }

  limpiar() {
    this.almacenamientoLocal.clear();
  }

  obtener(llave: string) {
    return this.almacenamientoLocal.getItem(llave);
  }

  quitar(llave: string) {
    this.almacenamientoLocal.removeItem(llave);
  }

  obtenerUsuario(): Usuario{
    return JSON.parse(`${this.obtener("Usuario")}`);
  }
}
