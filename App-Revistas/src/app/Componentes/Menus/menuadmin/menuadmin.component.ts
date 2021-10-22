import { Component, OnInit } from '@angular/core';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';

@Component({
  selector: 'app-menuadmin',
  templateUrl: './menuadmin.component.html',
  styleUrls: ['./menuadmin.component.css']
})
export class MenuadminComponent implements OnInit {

  usuario: Usuario;
  constructor(
    private almacenamiento: AlmacenamientoLocalService
  ) {
    this.usuario = this.almacenamiento.obtenerUsuario();
   }

  ngOnInit(): void {
  }
}
