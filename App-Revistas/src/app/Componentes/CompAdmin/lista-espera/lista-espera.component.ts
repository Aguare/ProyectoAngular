import { Component, OnInit } from '@angular/core';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-lista-espera',
  templateUrl: './lista-espera.component.html',
  styleUrls: ['./lista-espera.component.css']
})
export class ListaEsperaComponent implements OnInit {

  //Variables auxiliares
  espera: boolean = true;
  mensaje: Info = new Info(true, "Vacío", "No hay revistas para mostrar");

  //Variables lógicas
  revistas: Revista[];

  constructor(
    private obtener: ObtenerObjetosService
  ) {
    this.obtener.obtenerRevistasPendientes().subscribe((respuesta: Revista[]) => {
      this.revistas = respuesta;
      this.espera = false;
    },
      (error: any) => {
        this.mensaje = error.error;
        this.espera = true;
      });
  }

  ngOnInit(): void {
  }

}
