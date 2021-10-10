import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';

@Component({
  selector: 'app-etiqueta',
  templateUrl: './etiqueta.component.html',
  styleUrls: ['./etiqueta.component.css']
})
export class EtiquetaComponent implements OnInit {

  @Output() agregar = new EventEmitter<string>();
  @Output() quitar = new EventEmitter<string>();
  @Input() etiquetaIn: Etiqueta;
  @Input() tipoBoton: boolean;

  constructor() {
  }

  ngOnInit(): void {
  }

  agregarEtiqueta(dato: string) {
    this.agregar.emit(dato);
  }

  quitarEtiqueta(dato: string) {
    this.quitar.emit(dato);
  }

}
