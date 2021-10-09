import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu-editor',
  templateUrl: './menu-editor.component.html',
  styleUrls: ['./menu-editor.component.css']
})
export class MenuEditorComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  cerrarSesion(){
    alert("cerrar sesión");
  }
}
