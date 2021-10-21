import { Component, OnInit } from '@angular/core';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-ver-suscripciones',
  templateUrl: './ver-suscripciones.component.html',
  styleUrls: ['./ver-suscripciones.component.css']
})
export class VerSuscripcionesComponent implements OnInit {

  revistas: Revista[];
  usuario: Usuario;
  mensaje: Info = new Info(false, "Sin Suscripciones", "Suscribete ahora por un bajo costo");
  espera: boolean = false;

  constructor(
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService
  ) {
    this.usuario = this.almacenamiento.obtenerUsuario();
  }

  ngOnInit(): void {
    this.obtener.obtenerRevistaSuscripciones(this.usuario.nombreUsuario).subscribe((revistas: Revista[]) => {
      this.revistas = revistas;
      if (this.revistas.length == 0) {
        this.espera = true;
      }
    },
      (error: Info) => {
        this.mensaje = error;
        this.espera = true;
      }
    );
  }

}
