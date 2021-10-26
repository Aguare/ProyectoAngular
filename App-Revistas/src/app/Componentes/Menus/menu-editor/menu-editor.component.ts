import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';

@Component({
  selector: 'app-menu-editor',
  templateUrl: './menu-editor.component.html',
  styleUrls: ['./menu-editor.component.css']
})
export class MenuEditorComponent implements OnInit {

  usuario: Usuario = new Usuario("3", "Editor", "");

  constructor(
    private almacenamiento: AlmacenamientoLocalService
  ) {
    let nuevo = JSON.parse(`${this.almacenamiento.obtener("Usuario")}`);
    if (nuevo != null) {
      this.usuario = nuevo;
    }
  }

  ngOnInit(): void {
  }
}
