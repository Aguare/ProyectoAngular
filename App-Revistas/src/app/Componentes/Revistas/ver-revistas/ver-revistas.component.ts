import { Component, Input, OnInit } from '@angular/core';
import { Revista } from 'src/app/Objetos/Revista';

@Component({
  selector: 'app-ver-revistas',
  templateUrl: './ver-revistas.component.html',
  styleUrls: ['./ver-revistas.component.css']
})
export class VerRevistasComponent implements OnInit {

  @Input() revistas: Revista[];
  constructor() {
   }

  ngOnInit(): void {
  }

}
