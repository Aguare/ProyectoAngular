import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from 'src/app/Objetos/Cliente';
import { Info } from 'src/app/Objetos/Info';
import { Perfil } from 'src/app/Objetos/Perfil';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  pathFoto: string = "https://portal.staralliance.com/cms/aux-pictures/prototype-images/avatar-default.png/@@images/image.png";
  nombreUsuario: string = "";
  cliente: Cliente;
  error: boolean = true;
  mensaje: Info = new Info(false, "Error", "El usuario no existe");
  usuario: Usuario;
  perfil: Perfil;
  puedeEditar: boolean = false;

  constructor(
    private ruta: ActivatedRoute,
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService
  ) {
    let temporal = this.ruta.snapshot.paramMap.get("nombreUsuario");
    if (temporal != null) {
      this.nombreUsuario = temporal;
      if (this.nombreUsuario != null) {
        this.obtener.obtenerCliente(this.nombreUsuario).subscribe((c: Cliente) => {
          if (c.usuario != null && c.perfil != null) {
            this.cliente = c;
            this.usuario = this.cliente.usuario;
            this.perfil = this.cliente.perfil;
            this.error = false;
          } else {
            this.error = true;
          }
        },
          (er: any) => {
            this.mensaje = er;
            this.error = true;
          }
        );
      }
    } else {
      this.error = true;
    }
    this.verificarEditar();
  }

  ngOnInit(): void {
  }

  verificarEditar() {
    let nombreSesion = this.almacenamiento.obtenerUsuario();
    if (nombreSesion.nombreUsuario == this.nombreUsuario) {
      this.puedeEditar = true;
    }
  }
}
