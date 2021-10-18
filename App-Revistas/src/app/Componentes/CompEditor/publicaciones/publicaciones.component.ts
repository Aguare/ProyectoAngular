import { Component, OnInit } from '@angular/core';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';

@Component({
  selector: 'app-publicaciones',
  templateUrl: './publicaciones.component.html',
  styleUrls: ['./publicaciones.component.css']
})
export class PublicacionesComponent implements OnInit {

  //Variables auxiliare al componente
  mensaje: Info = new Info(false, "Sin conexión", "No hay respuesta del servidor");
  mostrar: boolean = false;

  //Variables de funcionalidad
  revistas: Revista[];
  usuario: Usuario;

  constructor(
    private obtener: ObtenerObjetosService,
    private almacenamiento: AlmacenamientoLocalService
  ) { 
    this.usuario = almacenamiento.obtenerUsuario();
    obtener.obtenerRevistasPorEditor(this.usuario).subscribe((respuesta: Revista[]) =>{
      if (respuesta != null) {
        this.revistas = respuesta;
      }
    },
      (error: Info) => {
        this.mostrar = true;
        this.mensaje = error;
      }
    );
  }

  ngOnInit(): void {
  }

}
