import { Component, Input, OnInit } from '@angular/core';
import { Revista } from 'src/app/Objetos/Revista';

@Component({
  selector: 'app-tarjeta-revista',
  templateUrl: './tarjeta-revista.component.html',
  styleUrls: ['./tarjeta-revista.component.css']
})
export class TarjetaRevistaComponent implements OnInit {

  @Input() revista: Revista;
  tieneMG: boolean = false;
  cantidadMG: number = 0;
  cantidadCom: number = 0;

  constructor() { }

  ngOnInit(): void {
  }

  darMeGusta() {
    let meg = document.getElementById("botonMG");
    if (this.tieneMG) {
      meg?.classList.remove("btn-primary");
      meg?.classList.add("btn-outline-primary");
      this.tieneMG = false;
      this.cantidadMG--;
    } else {
      meg?.classList.remove("btn-outline-primary");
      meg?.classList.add("btn-primary");
      this.tieneMG = true;
      this.cantidadMG++;
    }
  }
}
