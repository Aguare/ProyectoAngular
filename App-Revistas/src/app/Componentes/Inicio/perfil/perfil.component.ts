import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from 'src/app/Objetos/Cliente';
import { Info } from 'src/app/Objetos/Info';
import { Perfil } from 'src/app/Objetos/Perfil';
import { Usuario } from 'src/app/Objetos/Usuario';
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
  mensaje: Info;
  usuario: Usuario;
  perfil: Perfil;

  constructor(
    private ruta: ActivatedRoute,
    private obtener: ObtenerObjetosService
  ) {
    this.mensaje = new Info(false, "Error", "El usuario no existe");
  }

  ngOnInit(): void {
    this.cargarCliente();
  }

  cargarCliente() {
    let temporal = this.ruta.snapshot.paramMap.get("nombreUsuario");
    if (temporal != null) {
      this.nombreUsuario = temporal;
    }
    if (this.nombreUsuario != null) {
      this.obtener.obtenerCliente(this.nombreUsuario).subscribe((c: Cliente) => {
        this.cliente = c;
        this.usuario = this.cliente.usuario;
        this.perfil = this.cliente.perfil;
        this.error = false;
      },
        (er: any) => {

          this.error = true;
        }
      );
    }
  }
}
