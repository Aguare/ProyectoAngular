import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { ObtenerEtiquetasService } from 'src/app/Servicios/ObtenerObjetos/ObtenerEtiquetas.service';

@Component({
  selector: 'app-editar-etiquetas',
  templateUrl: './editar-etiquetas.component.html',
  styleUrls: ['./editar-etiquetas.component.css']
})
export class EditarEtiquetasComponent implements OnInit {

  etiquetasExistentes: Etiqueta[];
  @Input() etiquetasSeleccionadas: Etiqueta[];
  @Output() enviarEtiquetas = new EventEmitter<Etiqueta[]>();
  @Input() editar: boolean;

  constructor(
    private obtenerEtiquetas: ObtenerEtiquetasService
  ) {
    this.etiquetasSeleccionadas = [];
    this.etiquetasExistentes = [];
    this.obtenerEtiquetas.obtenerEtiquetas().subscribe((nuevasEtiquetas: Etiqueta[]) => {
      if (nuevasEtiquetas != null) {
        this.etiquetasExistentes = nuevasEtiquetas;
        this.quitarSeleccionadas();
      }
    });
  }

  ngOnInit(): void {
  }

  agregarEtiqueta(etiqueta: string) {
    if (this.editar) {
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

  }

  quitarEtiqueta(etiqueta: string) {
    if (this.editar) {
      for (let i = 0; i < this.etiquetasSeleccionadas.length; i++) {
        const element = this.etiquetasSeleccionadas[i];
        if (element.nombre == etiqueta) {
          this.etiquetasSeleccionadas.splice(i, 1);
          this.etiquetasExistentes.push(new Etiqueta(etiqueta));
          break;
        }
      }
    }
  }

  etiquetasSU(etiquetas: Etiqueta[]) {
    this.enviarEtiquetas.emit(this.etiquetasSeleccionadas);
  }

  quitarSeleccionadas() {
    for (let i = 0; i < this.etiquetasSeleccionadas.length; i++) {
      const selec = this.etiquetasSeleccionadas[i];
      for (let j = 0; j < this.etiquetasExistentes.length; j++) {
        const existente = this.etiquetasExistentes[j];
        if (existente.nombre == selec.nombre) {
          this.etiquetasExistentes.splice(j, 1);
          break;
        }
      }
    }
  }
}
