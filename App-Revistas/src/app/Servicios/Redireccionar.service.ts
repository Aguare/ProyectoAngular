import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../Objetos/Usuario';
import { AlmacenamientoLocalService } from './Almacenamiento/AlmacenamientoLocal.service';

@Injectable({
  providedIn: 'root'
})
export class RedireccionarService {

  usuario: Usuario | null = null;
  constructor(public redireccionar: Router, public almacenamiento: AlmacenamientoLocalService) { }

  enviarPagina(direccion: string) {
    this.redireccionar.navigate([direccion]);
  }

  redireccionarUsuario() {
    this.usuario = JSON.parse(`${this.almacenamiento.obtener("Usuario")}`);
    switch (this.usuario?.tipoUsuario) {
      case "Administrador":
        this.enviarPagina("InicioAdmin");
        break;
      case "Lector":
        this.enviarPagina("InicioLector");
        break;
      case "Editor":
        this.enviarPagina("InicioEditor");
        break;
      default:
        this.enviarPagina("Error");
        break;
    }
  }
}
