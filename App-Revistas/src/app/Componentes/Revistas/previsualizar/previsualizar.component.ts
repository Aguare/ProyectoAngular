import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Comentario } from 'src/app/Objetos/Comentario';
import { Info } from 'src/app/Objetos/Info';
import { Reaccion } from 'src/app/Objetos/Reaccion';
import { Revista } from 'src/app/Objetos/Revista';
import { Suscripcion } from 'src/app/Objetos/Suscripcion';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-previsualizar',
  templateUrl: './previsualizar.component.html',
  styleUrls: ['./previsualizar.component.css']
})
export class PrevisualizarComponent implements OnInit {

  revista: Revista;
  idRevista: string;
  tieneMG: boolean = false;
  cantidadMG: number = 0;
  cantidadCom: number = 0;
  reacciones: Reaccion[] = [];
  comentarios: Comentario[] = [];
  error: boolean = true;
  mensaje: Info = new Info(false, "Error", "La revista no existe");
  comentarioNuevo: string;
  fecha: string;
  nombreUsuario: string;
  suscripciones: Suscripcion[];
  tieneSub: boolean = false;

  constructor(
    private registrar: RegistrarService,
    private almacenamiento: AlmacenamientoLocalService,
    private obtener: ObtenerObjetosService,
    private ruta: ActivatedRoute
  ) {
    let temporal = this.ruta.snapshot.paramMap.get("idRevista");
    if (temporal != null) {
      this.idRevista = temporal;
    }
  }

  ngOnInit(): void {
    if (this.idRevista != null) {
      this.obtener.obtenerRevista(this.idRevista).subscribe((revista: Revista) => {
        this.revista = revista;
        this.error = false;
        this.obtener.obtenerReacciones(this.revista.idRevista).subscribe((respuesta: Reaccion[]) => {
          this.reacciones = respuesta;
          this.verificarMegustaUsuario();
          this.obtener.obtenerComentarios(this.revista.idRevista).subscribe((respuesta: Comentario[]) => {
            this.comentarios = respuesta;
            this.cantidadCom = this.comentarios.length;
            this.nombreUsuario = this.almacenamiento.obtenerUsuario().nombreUsuario;
            this.obtener.obtenerSuscripciones(this.revista.idRevista + "").subscribe((r: Suscripcion[]) => {
              this.suscripciones = r;
              this.verificarSuscripcion();
            },
              (error: any) => {
                this.mensaje = error.error;
                this.error = true;
              }
            );
          },
            (error: any) => {
              this.mensaje = error.error;
              this.error = true;
            }
          );
        },
          (error: any) => {
            this.mensaje = error.error;
            this.error = true;
          }
        );
      },
        (error: any) => {
          this.mensaje = error.error;
          this.error = true;
        }
      );
    }
  }

  verificarSuscripcion(){
    const usuario = this.almacenamiento.obtenerUsuario().nombreUsuario;
    for (let i = 0; i < this.suscripciones.length; i++) {
      const element = this.suscripciones[i];
      if (element.lector.nombreUsuario == usuario) {
        this.tieneSub = true;
        break;
      }
    }
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
    if (this.fecha != null) {
      let reaccion = new Reaccion(this.revista.idRevista,
        this.tieneMG, this.fecha, this.almacenamiento.obtenerUsuario().nombreUsuario, this.revista.idRevista);
      this.registrar.registrarReaccion(reaccion).subscribe((respuesta: Info) => {
      });
    }
  }

  hacerComentario() {
    if (this.fecha != null) {
      let coment = new Comentario(1, this.comentarioNuevo, this.fecha,
        this.almacenamiento.obtenerUsuario().nombreUsuario, this.revista.idRevista);
      this.registrar.registrarComentario(coment).subscribe((respuesta: Info) => {
        this.mensaje = respuesta;
        this.error = true;
        this.comentarios.push(coment);
      },
        (error: Info) => {
          this.mensaje = error;
          this.error = true;
        }
      );
    }
  }

  recibirFecha(fecha: string){
    this.fecha = fecha;
  }

}
