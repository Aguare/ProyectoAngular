import { Component, OnInit } from '@angular/core';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-publicaciones',
  templateUrl: './publicaciones.component.html',
  styleUrls: ['./publicaciones.component.css']
})
export class PublicacionesComponent implements OnInit {

  //Variables auxiliare al componente
  mensaje: Info = new Info(false, "Sin conexión", "No hay respuesta del servidor");
  mostrar: boolean = false;

  //Variables de funcionalidad
  revistas: Revista[];
  usuario: Usuario;

  constructor(
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService,
    private registrar: RegistrarService
  ) {
    this.usuario = this.almacenamiento.obtenerUsuario();
    this.obtener.obtenerRevistasPorEditor(this.usuario).subscribe((respuesta: Revista[]) => {
      if (respuesta != null) {
        this.revistas = respuesta;
        this.mostrar = false;
      }
    },
      (error: any) => {
        this.mensaje = error.error;
        this.mostrar = true;
      }
    );
  }

  ngOnInit(): void {
  }

  cambioComentarios(revista: Revista) {
    revista.tiene_comentarios = !revista.tiene_comentarios;
    this.registrarCambio(revista, 1);
  }

  cambioMeGusta(revista: Revista) {
    revista.tiene_reacciones = !revista.tiene_reacciones;
    this.registrarCambio(revista, 2);
  }

  cambioSuscripciones(revista: Revista) {
    revista.suscripciones = !revista.suscripciones;
    this.registrarCambio(revista, 3);
  }

  registrarCambio(revista: Revista, opcion: number) {
    this.registrar.registrarCambioRevista(revista, opcion).subscribe((respuesta: Info) => {
      this.mensaje = respuesta;
      this.mostrar = true;
    },
      (error: any) => {
        this.mensaje = error.error;
        this.mostrar = true;
      }
    );
  }
}
