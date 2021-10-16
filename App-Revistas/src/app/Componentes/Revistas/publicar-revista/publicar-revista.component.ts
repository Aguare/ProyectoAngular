import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-publicar-revista',
  templateUrl: './publicar-revista.component.html',
  styleUrls: ['./publicar-revista.component.css']
})
export class PublicarRevistaComponent implements OnInit {

  crearEtiqueta: boolean = true;
  constructor() { }

  ngOnInit(): void {
  }

}
