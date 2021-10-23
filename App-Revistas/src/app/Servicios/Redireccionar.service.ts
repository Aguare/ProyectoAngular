import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Info } from '../Objetos/Info';
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

  enviarPaginaMensaje(direccion: string, mensaje: Info){
    this.redireccionar.navigate([direccion, mensaje]);
  }

  redireccionarUsuario() {
    this.usuario = JSON.parse(`${this.almacenamiento.obtener("Usuario")}`);
    switch (this.usuario?.tipoUsuario) {
      case "Administrador":
        this.enviarPagina("InicioAdmin/ListaEspera");
        break;
      case "Lector":
        this.enviarPagina("InicioLector/VerRevistas");
        break;
      case "Editor":
        this.enviarPagina("InicioEditor");
        break;
      default:
        this.enviarPagina("ErrorConexion");
        break;
    }
  }
}
