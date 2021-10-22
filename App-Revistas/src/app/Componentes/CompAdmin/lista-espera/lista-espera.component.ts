import { Component, OnInit } from '@angular/core';
import { Revista } from 'src/app/Objetos/Revista';

@Component({
  selector: 'app-lista-espera',
  templateUrl: './lista-espera.component.html',
  styleUrls: ['./lista-espera.component.css']
})
export class ListaEsperaComponent implements OnInit {

  revistas: Revista[];
  
  constructor() { }

  ngOnInit(): void {
  }

}
