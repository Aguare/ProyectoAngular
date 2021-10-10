import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { ObtenerEtiquetasService } from 'src/app/Servicios/ObtenerObjetos/ObtenerEtiquetas.service';

@Component({
  selector: 'app-seleccion-etiquetas',
  templateUrl: './seleccion-etiquetas.component.html',
  styleUrls: ['./seleccion-etiquetas.component.css']
})
export class SeleccionEtiquetasComponent implements OnInit {

  etiquetasExistentes: Etiqueta[];
  etiquetasSeleccionadas: Etiqueta[];
  @Output() enviarEtiquetas = new EventEmitter<Etiqueta[]>();

  constructor(public obtenerEtiquetas: ObtenerEtiquetasService) {
    obtenerEtiquetas.obtenerEtiquetas().subscribe((nuevasEtiquetas: Etiqueta[]) => {
      if (nuevasEtiquetas != null) {
        this.etiquetasExistentes = nuevasEtiquetas;
      }else{
        alert("No hay conexión con el servidor");
      }
    });
    this.etiquetasSeleccionadas = [];
  }

  ngOnInit(): void {
  }

  agregarEtiqueta(etiqueta: string) {
    for (let i = 0; i < this.etiquetasExistentes.length; i++) {
      const element = this.etiquetasExistentes[i];
      if (element.nombre == etiqueta) {
        this.etiquetasExistentes.splice(i, 1);
        this.etiquetasSeleccionadas.push(new Etiqueta(etiqueta));
        this.etiquetasSU(this.etiquetasSeleccionadas);
        break;
      }
    }

  }

  quitarEtiqueta(etiqueta: string) {
    for (let i = 0; i < this.etiquetasSeleccionadas.length; i++) {
      const element = this.etiquetasSeleccionadas[i];
      if (element.nombre == etiqueta) {
        this.etiquetasSeleccionadas.splice(i, 1);
        this.etiquetasExistentes.push(new Etiqueta(etiqueta));
        break;
      }
    }
  }

  etiquetasSU(etiquetas: Etiqueta[]){
    this.enviarEtiquetas.emit(this.etiquetasSeleccionadas);
  }

}
