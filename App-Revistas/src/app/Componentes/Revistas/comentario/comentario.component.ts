import { Component, Input, OnInit } from '@angular/core';
import { Comentario } from 'src/app/Objetos/Comentario';

@Component({
  selector: 'app-comentario',
  templateUrl: './comentario.component.html',
  styleUrls: ['./comentario.component.css']
})
export class ComentarioComponent implements OnInit {
  
  @Input() comentario: Comentario;
  constructor() { }

  ngOnInit(): void {
  }

}
