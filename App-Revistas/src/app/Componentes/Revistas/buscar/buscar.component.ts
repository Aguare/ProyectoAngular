import { Component, OnInit } from '@angular/core';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
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
  fecha: Date = new Date();
  etiquetas: Etiqueta[];

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

  recibirEtiquetas(etiquetas: Etiqueta[]) {
    this.etiquetas = etiquetas;
  }

  actualizarRevistas() {
    if (this.etiquetas.length == 0) {
      this.ngOnInit();
    } else {
      this.obtener.obtenerRevistasBusqueda(this.etiquetas).subscribe((respuesta: Revista[]) => {
        this.revistas = respuesta;
      },
        (error: any) => {
          alert("Error al obtener las revistas");
        }
      );
    }

  }
}
