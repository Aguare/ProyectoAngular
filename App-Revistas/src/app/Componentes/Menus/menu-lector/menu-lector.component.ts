import { Component, OnInit } from '@angular/core';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { Usuario} from 'src/app/Objetos/Usuario';

@Component({
  selector: 'app-menu-lector',
  templateUrl: './menu-lector.component.html',
  styleUrls: ['./menu-lector.component.css']
})
export class MenuLectorComponent implements OnInit {

  usuario: Usuario = new Usuario("2", "Lector", "");
  
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
