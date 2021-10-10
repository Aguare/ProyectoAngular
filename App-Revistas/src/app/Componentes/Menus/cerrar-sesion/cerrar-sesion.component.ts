import { Component, OnInit } from '@angular/core';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { RedireccionarService } from 'src/app/Servicios/Redireccionar.service';

@Component({
  selector: 'app-cerrar-sesion',
  templateUrl: './cerrar-sesion.component.html',
  styleUrls: ['./cerrar-sesion.component.css']
})
export class CerrarSesionComponent implements OnInit {

  constructor(
    public sesion: AlmacenamientoLocalService,
    public redireccionar: RedireccionarService
    ) { }

  ngOnInit(): void {
  }

  cerrarSesion(){
    this.sesion.limpiar();
    this.redireccionar.enviarPagina("Inicio");
  }
}
