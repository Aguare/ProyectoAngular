import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { SafeResourceUrl } from '@angular/platform-browser';
import { Anunciante } from 'src/app/Objetos/Anunciante';
import { Anuncio } from 'src/app/Objetos/Anuncio';
import { Etiqueta } from 'src/app/Objetos/Etiqueta';
import { Info } from 'src/app/Objetos/Info';
import { AlmacenamientoLocalService } from 'src/app/Servicios/Almacenamiento/AlmacenamientoLocal.service';
import { Base64Service } from 'src/app/Servicios/ObtenerObjetos/Base64.service';
import { ObtenerObjetosService } from 'src/app/Servicios/ObtenerObjetos/ObtenerObjetos.service';
import { RegistrarService } from 'src/app/Servicios/Registros/Registrar.service';

@Component({
  selector: 'app-crear-anuncio',
  templateUrl: './crear-anuncio.component.html',
  styleUrls: ['./crear-anuncio.component.css']
})
export class CrearAnuncioComponent implements OnInit {

  //Variables auxiliares
  mensaje: Info = new Info(false, "Error", "No hay anunciantes registrados");
  espera: boolean = false;
  validarForm: FormGroup;

  //Variables logicas
  anunciantes: Anunciante[];
  path: string = "/assets/img/cheems.jpg";
  foto: File | null = null;

  //Variables para formulario
  anunciante: string;
  tipoAnuncio: number = 1;
  texto: string;
  video_url: string;
  costo: number;
  dias: number;
  imagen: string;
  fecha: string;
  etiquetas: Etiqueta[];

  constructor(
    private obtener: ObtenerObjetosService,
    private registrar: RegistrarService,
    private base: Base64Service,
    private almacenamiento: AlmacenamientoLocalService
  ) {
    this.obtener.obtenerAnunciantes().subscribe((respuesta: Anunciante[]) => {
      this.anunciantes = respuesta;
      if (this.anunciantes.length == 0) {
        this.espera = true;
      }
    }, (error: any) => {
      this.mensaje = new Info(false, "Error", "No hay conexión con el servidor");
      this.espera = true;
    }
    );
  }

  ngOnInit(): void {
    this.validarForm = new FormGroup({
      anunciante: new FormControl(),
      tipo: new FormControl(1, Validators.required),
      texto: new FormControl('', [Validators.required, Validators.minLength(5)]),
      costo: new FormControl(1, Validators.required),
      video: new FormControl(),
      fecha: new FormControl(Validators.required),
      dias: new FormControl(1, Validators.required)
    });
  }

  obtenerImagen(event: Event) {
    const files = (event.target as HTMLInputElement).files;
    if (files != null) {
      this.foto = files.item(0);
      this.base.extraerBase64(this.foto).then((foto: any) => {
        this.path = foto.base;
      });
    }
  }

  cambioTipoAnuncio() {
    this.tipoAnuncio = this.validarForm.get('tipo')?.value;
    let inputVideo = document.getElementById('video');
    if (this.tipoAnuncio == 3) {
      inputVideo?.removeAttribute('readonly');
      inputVideo?.removeAttribute('disabled');
      inputVideo?.setAttribute('required', '');
    } else {
      inputVideo?.setAttribute('disabled', '');
      inputVideo?.removeAttribute('required');
    }
  }

  recibirEtiquetas(etiquetas: Etiqueta[]) {
    this.etiquetas = etiquetas;
  }

  registrarAnuncio() {
    this.anunciante = this.validarForm.value.anunciante;
    this.texto = this.validarForm.value.texto;
    this.video_url = this.validarForm.value.video;
    this.costo = this.validarForm.value.costo;
    this.dias = this.validarForm.value.dias;
    this.fecha = this.validarForm.value.fecha;
    if (this.tipoAnuncio == 3) {
      this.video_url+="?controls=0&autoplay=1&mute=0"  
    }
    let anuncio = new Anuncio(1, this.tipoAnuncio, this.texto,
      this.video_url, this.imagen, true, this.fecha, this.fecha,
      this.costo, this.anunciante, this.etiquetas);
    console.log(anuncio);
    if (this.tipoAnuncio == 2) {
      if (this.foto != null) {
        this.registrar.subirArchivo(this.foto, this.almacenamiento.obtenerUsuario()).subscribe((path: Info) => {
          anuncio.imagen_path = path.mensaje;
          this.registrar.registrarAnuncio(anuncio, this.dias).subscribe((res: Info) => {
            this.mensaje = res;
            this.espera = true;
            this.validarForm.reset();
          },
            (error: any) => {
              this.mensaje = error.error;
              this.espera = true;
            }
          );
        },
          (error: any) => {
            this.mensaje = error.error;
            this.espera = true;
          }
        );
      }
    } else {
      this.registrar.registrarAnuncio(anuncio, this.dias).subscribe((res: Info) => {
        this.mensaje = res;
        this.espera = true;
        this.validarForm.reset();
      },
        (error: any) => {
          this.mensaje = error.error;
          this.espera = true;
        }
      );
    }
  }

  hayError() {
    this.mensaje = new Info(false, "Error", "Complete los campos necesarios");
    this.espera = true;
  }
}
