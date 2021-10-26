import { Component, OnInit } from '@angular/core';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-lista-revistas',
  templateUrl: './lista-revistas.component.html',
  styleUrls: ['./lista-revistas.component.css']
})
export class ListaRevistasComponent implements OnInit {

  //Variables auxiliares
  espera: boolean = true;
  mensaje: Info = new Info(true, "Vacío", "No hay revistas para mostrar");

  //Variables lógicas
  revistas: Revista[];

  constructor(
    private obtener: ObtenerObjetosService
  ) {
    this.obtener.obtenerRevistasAceptadas().subscribe((respuesta: Revista[]) => {
      this.revistas = respuesta;
      this.espera = false;
      if (this.revistas.length == 0) {
        this.espera = true;
      }
    },
      (error: any) => {
        console.log(error)
        this.mensaje = error.error;
        this.espera = true;
      });
  }

  ngOnInit(): void {
  }
}
