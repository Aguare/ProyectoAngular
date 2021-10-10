import { Component, OnInit } from '@angular/core';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Info } from 'src/app/Objetos/Info';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-ver-anunciantes',
  templateUrl: './ver-anunciantes.component.html',
  styleUrls: ['./ver-anunciantes.component.css']
})
export class VerAnunciantesComponent implements OnInit {

  anunciantes: Anunciante[] = [];
  verMensaje: boolean = true;
  mensaje: Info = new Info(true, "Vacío", "No hay anunciantes para mostrar");

  constructor(public obtener: ObtenerObjetosService) {
    obtener.obtenerEtiquetas().subscribe((respuesta: Anunciante[]) => {
      if (respuesta != null) {
        this.anunciantes = respuesta;
        this.verMensaje = false;
      } else {
        this.verMensaje = true;
        this.mensaje = new Info(false, "ERROR", "El Servidor no respondió la solicitud");
      }
    });
  }

  ngOnInit(): void {
  }

}
