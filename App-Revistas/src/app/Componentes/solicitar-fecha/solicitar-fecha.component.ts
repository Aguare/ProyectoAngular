import { Component, EventEmitter, OnInit, Output } from '@angular/core';


@Component({
  selector: 'app-solicitar-fecha',
  templateUrl: './solicitar-fecha.component.html',
  styleUrls: ['./solicitar-fecha.component.css']
})
export class SolicitarFechaComponent implements OnInit {

  fecha: Date = new Date();
  @Output() cambioFecha = new EventEmitter<string>();
  constructor() { }

  ngOnInit(): void {
  }

  usuarioCambiaFecha(valor: string) {
    this.cambioFecha.emit(valor);
  }
}
