import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-publicar-revista',
  templateUrl: './publicar-revista.component.html',
  styleUrls: ['./publicar-revista.component.css']
})
export class PublicarRevistaComponent implements OnInit {

  crearEtiqueta: boolean = true;
  esGratuita: boolean = false;
  archivo: File | null = null;

  constructor() { }

  ngOnInit(): void {
  }

  cambiarEsGratuita() {
    let input = document.getElementById("inputPrecioSuscripcion");
    if (this.esGratuita) {
      this.esGratuita = false;
      input?.removeAttribute("disabled");
      input?.setAttribute("required", "");
    } else {
      this.esGratuita = true;
      input?.removeAttribute("required");
      input?.setAttribute("disabled", "");
    }
  }

  obtenerPath(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    let input = document.getElementById("inputFile");
    if (files != null) {
      this.archivo = files.item(0);
    }
  }
}
