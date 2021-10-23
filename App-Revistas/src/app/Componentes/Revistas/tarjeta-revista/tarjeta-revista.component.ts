import { Component, Input, OnInit } from '@angular/core';
import { Comentario } from 'src/app/Objetos/Comentario';
import { Info } from 'src/app/Objetos/Info';
import { Reaccion } from 'src/app/Objetos/Reaccion';
import { Revista } from 'src/app/Objetos/Revista';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-tarjeta-revista',
  templateUrl: './tarjeta-revista.component.html',
  styleUrls: ['./tarjeta-revista.component.css']
})
export class TarjetaRevistaComponent implements OnInit {

  @Input() revista: Revista;
  tieneMG: boolean = false;
  cantidadMG: number = 0;
  cantidadCom: number = 0;
  reacciones: Reaccion[] = [];
  comentarios: Comentario[] = [];
  fecha: string;

  constructor(
    private registrar: RegistrarService,
    private almacenamiento: AlmacenamientoLocalService,
    private obtener: ObtenerObjetosService
  ) {

  }

  ngOnInit(): void {
    if (this.revista.idRevista != null) {
      this.obtener.obtenerReacciones(this.revista.idRevista).subscribe((respuesta: Reaccion[]) => {
        this.reacciones = respuesta;
        this.verificarMegustaUsuario();
      },
        error => alert("error")
      );
      this.obtener.obtenerComentarios(this.revista.idRevista).subscribe((respuesta: Comentario[]) => {
        this.comentarios = respuesta;
        this.cantidadCom = this.comentarios.length;
      },
        error => alert("error")
      );
    }
  }

  recibirFecha(f: string){
    this.fecha = f;
  }

  verificarMegustaUsuario() {
    let nombre = this.almacenamiento.obtenerUsuario().nombreUsuario;
    for (let i = 0; i < this.reacciones.length; i++) {
      const el = this.reacciones[i];
      if (el.nombreUsuario == nombre) {
        if (el.reaccion) {
          this.cambiarBoton();
          this.cantidadMG--;
        }
      }
      if (el.reaccion) {
        this.cantidadMG++;
      }
    }
  }

  darMeGusta() {
    this.cambiarBoton();
    this.registrarReaccion();
  }

  cambiarBoton() {
    let meg = document.getElementById("botonMG" + this.revista.idRevista);
    if (this.tieneMG) {
      meg?.classList.remove("btn-primary");
      meg?.classList.add("btn-outline-primary");
      this.tieneMG = false;
      this.cantidadMG--;
    } else {
      meg?.classList.remove("btn-outline-primary");
      meg?.classList.add("btn-primary");
      this.tieneMG = true;
      this.cantidadMG++;
    }
  }

  registrarReaccion() {
    let fecha = prompt("Ingrese la fecha para el comentario Ejemplo: 2021-09-24");
    if (fecha != null) {
      let reaccion = new Reaccion(this.revista.idRevista,
        this.tieneMG, fecha, this.almacenamiento.obtenerUsuario().nombreUsuario, this.revista.idRevista);
      this.registrar.registrarReaccion(reaccion).subscribe((respuesta: Info) => {
      });
    }else{
     this.cambiarBoton(); 
    }
  }
}
