import { Component, OnInit } from '@angular/core';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RedireccionarService } from 'src/app/Servicios/Redireccionar.service';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  revistas: Revista[];
  usuario: Usuario;

  constructor(
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService,
    private redireccionar: RedireccionarService
  ) {
  }

  ngOnInit(): void {
    this.usuario = this.almacenamiento.obtenerUsuario();
    this.obtener.obtenerRevistasLector(this.usuario).subscribe((respuesta: Revista[]) => {
      this.revistas = respuesta;
    },
      error => this.redireccionar.enviarPagina("ErrorConexion")
    );
  }

}
