import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, MinValidator, Validators } from '@angular/forms';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { Revista } from 'src/app/Objetos/Revista';
import { Usuario } from 'src/app/Objetos/Usuario';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-publicar-revista',
  templateUrl: './publicar-revista.component.html',
  styleUrls: ['./publicar-revista.component.css']
})
export class PublicarRevistaComponent implements OnInit {

  //Variables auxiliares
  crearEtiqueta: boolean = true;
  validarForm: FormGroup;
  mensaje: Info = new Info(true, "Registrado", "Correctamente");
  error: boolean = false;

  //Variables del formulario
  etiquetas: Etiqueta[] | null = null;
  titulo: string;
  revista: string;
  version: number;
  archivo: File;
  descripcion: string;
  esGratuita: boolean = false;
  precioSuscripcion: number = 0;
  tiene_comentarios: boolean = false;
  tiene_reaccion: boolean = false;
  usuario: Usuario;
  suscripciones: boolean = true;

  constructor(
    private registrar: RegistrarService,
    private almacenamiento: AlmacenamientoLocalService,
    private builder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.validarForm = this.builder.group({
      titulo: new FormControl('', Validators.required),
      version: new FormControl('', Validators.required),
      descripcion: new FormControl('', Validators.required),
      archivo: new FormControl('', Validators.required),
      suscripciones: new FormControl(),
      tiene_comentarios: new FormControl(),
      tiene_reaccion: new FormControl()
    });
  }

  recibirEtiquetas(etiqueta: Etiqueta[]) {
    this.etiquetas = etiqueta;
  }

  ingresoPrecioSus(evento: any) {
    this.precioSuscripcion = evento.target.value;
    alert(this.precioSuscripcion);
  }

  cambiarEsGratuita() {
    let input = document.getElementById("inputPrecioSuscripcion");
    if (this.esGratuita) {
      this.esGratuita = false;
      input?.removeAttribute("disabled");
      input?.setAttribute("required", "");
      input?.setAttribute("min", "1");
      input?.setAttribute("value", "1");
    } else {
      this.esGratuita = true;
      this.precioSuscripcion = 0;
      input?.removeAttribute("required");
      input?.removeAttribute("min");
      input?.setAttribute("disabled", "");
      input?.setAttribute("value", "0");
    }
  }

  obtenerPath(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    let item = files?.item(0);
    if (item != null) {
      this.archivo = item;
    }
  }

  crearPublicacion() {
    this.titulo = this.validarForm.value.titulo;
    this.version = this.validarForm.value.version;
    this.descripcion = this.validarForm.value.descripcion;
    this.suscripciones = this.validarForm.value.suscripciones;
    this.tiene_comentarios = this.validarForm.value.tiene_comentarios;
    this.tiene_reaccion = this.validarForm.value.tiene_reaccion;
    if (this.validarForm.valid) {
      if (this.etiquetas != null) {
        if (this.etiquetas.length >= 1) {
          this.usuario = this.almacenamiento.obtenerUsuario();
          let objeto = new Revista(1, "", this.titulo, this.descripcion,
            this.version, 0, false, this.suscripciones, this.precioSuscripcion, !this.esGratuita, this.tiene_comentarios,
            this.tiene_reaccion, this.usuario, this.etiquetas);
          this.registrar.registrarRevista(objeto, this.archivo).subscribe((respuesta: Info) => {
            this.mensaje = respuesta;
            this.error = true;
            if (this.mensaje.operacion) {
              this.validarForm.reset();
            }
          })
        } else {
          this.mensaje = new Info(false, "Etiquetas", "Debe seleccionar almenos 1 etiqueta");
          this.error = true;
        }
      } else {
        this.error = true;
        this.mensaje = new Info(false, "Etiquetas", "Debe seleccionar almenos 1 etiqueta");
      }
    } else {
      this.error = true;
      this.mensaje = new Info(false, "Campos Inválidos", "Por favor rellene todos los campos");
    }
  }
}
