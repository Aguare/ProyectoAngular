import { Component, EventEmitter, OnInit, Output } from '@angular/core';


@Component({
  selector: 'app-solicitar-fecha',
  templateUrl: './solicitar-fecha.component.html',
  styleUrls: ['./solicitar-fecha.component.css']
})
export class SolicitarFechaComponent implements OnInit {

  fecha: Date = new Date();
  @Output() cambioFecha = new EventEmitter<string>();
  constructor() {
  }

  ngOnInit(): void {
    this.enviarFechaActual();
  }

  enviarFechaActual() {
    let year = this.fecha.getFullYear();
    let month = this.fecha.getMonth();
    let day = this.fecha.getDay();
    let newDate = year + "-" + month + "-" + day;
    if (month < 10 && day < 10) {
      newDate = year + "-0" + month + "-0" + day;
    } else if (month < 10) {
      newDate = year + "-0" + month + "-" + day;
    } else if (day < 10) {
      newDate = year + "-" + month + "-0" + day;
    }
    this.usuarioCambiaFecha(newDate);
  }

  usuarioCambiaFecha(valor: string) {
    this.cambioFecha.emit(valor);
  }
}
